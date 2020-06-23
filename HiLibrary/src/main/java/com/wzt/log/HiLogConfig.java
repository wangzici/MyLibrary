package com.wzt.log;

import com.wzt.log.format.HiStackTraceFormatter;
import com.wzt.log.format.HiThreadFormatter;
import com.wzt.log.printer.HiLogPrinter;

public abstract class HiLogConfig {
    private static final int DEFAULT_MAX_LEN = 512;
    private static final int DEFAULT_DEPTH = 5;
    private static final String DEFAULT_TAG = "HiLog";
    public HiThreadFormatter HI_THREAD_FORMATTER = new HiThreadFormatter();
    public HiStackTraceFormatter HI_STACK_TRACE_FORMATTER = new HiStackTraceFormatter();

    protected JsonParser injectJsonParser() {
        return null;
    }

    protected boolean enable() {
        return true;
    }

    protected boolean includeThread() {
        return false;
    }

    protected int stackTraceDepth() {
        return DEFAULT_DEPTH;
    }

    protected String getGlobalTag() {
        return DEFAULT_TAG;
    }

    public int getMaxLength() {
        return DEFAULT_MAX_LEN;
    }

    protected HiLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object[] src);
    }
}
