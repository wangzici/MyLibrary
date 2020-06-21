package com.wzt.hilog;

import com.wzt.hilog.format.HiLogContentFormatter;
import com.wzt.hilog.format.HiLogStackFormatter;
import com.wzt.hilog.format.HiLogThreadFormatter;
import com.wzt.hilog.printer.IPrinter;

import java.util.List;

public abstract class HiLogConfig {
    private static final int DEFAULT_DEPTH = 5;
    private static final String DEFAULT_TAG = "HiLog";
    public HiLogContentFormatter FORMATTER_CONTENT = new HiLogContentFormatter();
    public HiLogThreadFormatter FORMATTER_THREAD = new HiLogThreadFormatter();
    public HiLogStackFormatter FORMATTER_STACK = new HiLogStackFormatter();

    protected boolean enable() {
        return true;
    }

    protected boolean needThreadPrint() {
        return false;
    }

    protected int maxDepth() {
        return DEFAULT_DEPTH;
    }

    protected String getGlobalTag() {
        return DEFAULT_TAG;
    }

    protected List<IPrinter> listOfPrinter() {
        return null;
    }
}
