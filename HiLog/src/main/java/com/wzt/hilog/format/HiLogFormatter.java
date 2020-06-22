package com.wzt.hilog.format;

public interface HiLogFormatter<T> {
    String format(T data);
}
