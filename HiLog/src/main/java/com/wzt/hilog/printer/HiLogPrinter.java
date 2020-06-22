package com.wzt.hilog.printer;

import androidx.annotation.NonNull;

import com.wzt.hilog.HiLogConfig;
import com.wzt.hilog.HiLogType;

public interface HiLogPrinter {
    void print(@NonNull HiLogConfig config, @HiLogType.TYPE int type, String tag, String printString);
}
