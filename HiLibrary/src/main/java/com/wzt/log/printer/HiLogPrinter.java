package com.wzt.log.printer;

import androidx.annotation.NonNull;

import com.wzt.log.HiLogConfig;
import com.wzt.log.HiLogType;

public interface HiLogPrinter {
    void print(@NonNull HiLogConfig config, @HiLogType.TYPE int type, String tag, String printString);
}
