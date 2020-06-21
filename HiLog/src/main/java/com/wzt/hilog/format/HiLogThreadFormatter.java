package com.wzt.hilog.format;

public class HiLogThreadFormatter extends HiLogFormatter<Thread> {

    @Override
    public String format(Thread input) {
        return String.format("Thread:%s\n", input.getName());
    }
}
