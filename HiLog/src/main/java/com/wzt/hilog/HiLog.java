package com.wzt.hilog;

import androidx.annotation.NonNull;

import com.wzt.hilog.printer.IPrinter;
import com.wzt.hilog.utils.HiTrackUtils;

public class HiLog {
    private static String DEFAULT_PACKAGE_NAME = "com.wzt";

    public static void v(Object... objects) {
        log(HiLogType.V, objects);
    }

    public static void vt(String tag, Object... objects) {
        log(HiLogType.V, tag, objects);
    }

    public static void i(Object... objects) {
        log(HiLogType.I, objects);
    }

    public static void it(String tag, Object... objects) {
        log(HiLogType.I, tag, objects);
    }

    public static void d(Object... objects) {
        log(HiLogType.D, objects);
    }

    public static void dt(String tag, Object... objects) {
        log(HiLogType.D, tag, objects);
    }

    public static void w(Object... objects) {
        log(HiLogType.W, objects);
    }

    public static void wt(String tag, Object... objects) {
        log(HiLogType.W, tag, objects);
    }

    public static void e(Object... objects) {
        log(HiLogType.E, objects);
    }

    public static void et(String tag, Object... objects) {
        log(HiLogType.E, tag, objects);
    }

    public static void a(Object... objects) {
        log(HiLogType.A, objects);
    }

    public static void at(String tag, Object... objects) {
        log(HiLogType.A, tag, objects);
    }

    public static void log(@HiLogType.TYPE int type, Object... objects) {
        final String tag = HiLogManager.getInstance().getConfig().getGlobalTag();
        log(type, tag, objects);
    }

    public static void log(@HiLogType.TYPE int type, @NonNull String tag, Object... objects) {
        HiLogConfig config = HiLogManager.getInstance().getConfig();
        log(config, type, tag, objects);
    }

    public static void log(@NonNull HiLogConfig config, @HiLogType.TYPE int type, @NonNull String tag, Object... objects) {
        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (config.needThreadPrint()) {
            sb.append(config.FORMATTER_THREAD.format(Thread.currentThread()));
        }
        if (config.maxDepth() > 0) {
            sb.append(config.FORMATTER_STACK.format(HiTrackUtils.cropTracks(new Throwable().getStackTrace(), config.maxDepth(), DEFAULT_PACKAGE_NAME)));
        }
        sb.append(config.FORMATTER_CONTENT.format(objects));
        if (config.listOfPrinter() != null) {
            for (IPrinter printer :
                    config.listOfPrinter()) {
                printer.print(type, tag, sb.toString());
            }
        }
    }
}
