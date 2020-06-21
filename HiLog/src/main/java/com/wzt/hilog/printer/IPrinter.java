package com.wzt.hilog.printer;

import com.wzt.hilog.HiLogType;

public interface IPrinter {
    void print(@HiLogType.TYPE int type, String tag, String content);
}
