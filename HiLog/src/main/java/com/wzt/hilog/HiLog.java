package com.wzt.hilog;

import com.wzt.hilog.printer.IPrinter;
import com.wzt.hilog.utils.HiTrackUtils;

public class HiLog {
    private static String DEFAULT_PACKAGE_NAME = "com.wzt";

    public static void v(Object... objects) {
        v(null, objects);
    }

    public static void v(String tag, Object... objects) {
        log(null, 8, tag, objects);
    }

    public static void log(HiLogConfig hiLogConfig, @HiLogType.LogType int type, String tag, Object... objects) {
        if (hiLogConfig == null) {
            hiLogConfig = HiLogManager.getInstance().getHiLogConfig();
        }
        if (tag == null) {
            tag = hiLogConfig.getGlobalTag();
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (hiLogConfig.needThreadPrint()) {
            stringBuilder.append(hiLogConfig.FORMATTER_THREAD.format(Thread.currentThread()));
        }
        if (hiLogConfig.maxDepth() > 0) {
            stringBuilder.append(hiLogConfig.FORMATTER_STACK.format(HiTrackUtils.cropTracks(new Throwable().getStackTrace(), hiLogConfig.maxDepth(), DEFAULT_PACKAGE_NAME)));
        }
        stringBuilder.append(hiLogConfig.FORMATTER_CONTENT.format(objects));
        if (hiLogConfig.listOfPrinter() != null) {
            for (IPrinter printer :
                    hiLogConfig.listOfPrinter()) {
                printer.print(type, tag, stringBuilder.toString());
            }
        }
    }
}
