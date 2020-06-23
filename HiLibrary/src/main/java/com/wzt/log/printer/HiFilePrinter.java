package com.wzt.log.printer;

import android.app.Application;
import androidx.annotation.NonNull;

import com.wzt.log.HiLogConfig;
import com.wzt.log.model.HiLogMo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HiFilePrinter implements HiLogPrinter {
    private static File file;
    private Executor executor = Executors.newSingleThreadExecutor();

    public HiFilePrinter(Application application) {
        file = new File(application.getExternalFilesDir(""), "hi_log.log");
    }

    @Override
    public void print(@NonNull HiLogConfig config, int type, String tag, String printString) {
        final HiLogMo logMo = new HiLogMo(System.currentTimeMillis(), type, tag, printString);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                writeInFile(logMo);
            }
        });
    }

    private synchronized void writeInFile(HiLogMo logMo) {
        checkFileExists();

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.append(logMo.flattenedLog()).append("\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkFileExists() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
