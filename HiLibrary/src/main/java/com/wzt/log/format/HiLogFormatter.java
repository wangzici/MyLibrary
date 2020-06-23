package com.wzt.log.format;

public interface HiLogFormatter<T> {
    String format(T data);
}
