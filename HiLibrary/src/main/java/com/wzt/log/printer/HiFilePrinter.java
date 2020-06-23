package com.wzt.log.printer;

import androidx.annotation.NonNull;

import com.wzt.log.HiLogConfig;
import com.wzt.log.model.HiLogMo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 日志文件打印功能
 */
public class HiFilePrinter implements HiLogPrinter {
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private final String logPath;
    private final long retentionTime;
    private LogWriter writer;


    /**
     *
     * @param logPath log保存路径，如果是外部路径需要确保已经有外部存储的读写权限
     * @param retentionTime log文件的有效时长，单位毫秒，<=0表示一直有效
     */
    public HiFilePrinter(String logPath, long retentionTime) {
        this.logPath = logPath;
        this.retentionTime = retentionTime;
        writer = new LogWriter();
        cleanExpiredLog();
    }

    @Override
    public void print(@NonNull HiLogConfig config, int type, String tag, String printString) {
        final HiLogMo logMo = new HiLogMo(System.currentTimeMillis(), type, tag, printString);
        EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                doPrint(logMo);
            }
        });
    }

    /**
     * 清除过期log
     */
    private void cleanExpiredLog() {
        if (retentionTime <= 0) {
            return;
        }
        File logDir = new File(logPath);
        File[] files = logDir.listFiles();
        long currentTimeMillis = System.currentTimeMillis();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (currentTimeMillis - file.lastModified() > retentionTime) {
                file.delete();
            }
        }
    }

    private void doPrint(HiLogMo logMo) {
        if (writer.getPreFileName() == null) {
            if (writer.isReady()) {
                writer.close();
            }
            if (!writer.ready(genFileName())) {
                return;
            }
        }
        writer.append(logMo.flattenedLog());
    }

    /**
     * 生成日志文件名
     * @return
     */
    private String genFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd", Locale.CHINA);
        return sdf.format(System.currentTimeMillis());
    }

    /**
     * 打印器
     */
    private class LogWriter {
        private BufferedWriter bufferedWriter;
        private String preFileName;

        public String getPreFileName() {
            return preFileName;
        }

        private boolean ready(String logFilePath) {
            File logFile = new File(logPath, logFilePath);
            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            preFileName = logFilePath;
            return true;
        }

        private boolean isReady() {
            return bufferedWriter != null;
        }

        /**
         * 将log写入文件
         *
         * @param flattenedLog 格式化后的log
         */
        private void append(String flattenedLog) {
            try {
                bufferedWriter.append(flattenedLog);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 关闭bufferedWriter
         */
        private void close() {
            if (bufferedWriter == null) {
                return;
            }
            try {
                bufferedWriter.close();
                bufferedWriter = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
