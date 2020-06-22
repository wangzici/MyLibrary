package com.wzt.hilog;

import androidx.annotation.NonNull;

import com.wzt.hilog.printer.HiLogPrinter;
import com.wzt.hilog.utils.HiStackTraceUtil;

import java.util.Arrays;
import java.util.List;

public class HiLog {
    private static final String HI_LOG_PACKAGE;

    static {
        String className = HiLog.class.getName();
        HI_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.'));
    }

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
        if (config.includeThread()) {
            final String threadInfo = config.HI_THREAD_FORMATTER.format(Thread.currentThread());
            sb
                    .append(threadInfo)
                    .append('\n');
        }
        if (config.stackTraceDepth() > 0) {
            final String stackTrace = config.HI_STACK_TRACE_FORMATTER.format(HiStackTraceUtil.cropTracks(new Throwable().getStackTrace(), config.stackTraceDepth(), HI_LOG_PACKAGE));
            sb
                    .append(stackTrace)
                    .append('\n');
        }
        String body = parseBody(objects, config);
        sb.append(body).append('\n');
        List<HiLogPrinter> printers = config.printers() != null ? Arrays.asList(config.printers()) : HiLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        for (HiLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }

    private static String parseBody(@NonNull Object[] contents, @NonNull HiLogConfig config) {
        StringBuilder sb = new StringBuilder();
        if (config.injectJsonParser() != null) {
            sb.append(config.injectJsonParser().toJson(contents)).append('\n');
        }else {
            for (Object object : contents) {
                sb.append(object.toString()).append('\n');
            }
        }
        return sb.toString();
    }
}
