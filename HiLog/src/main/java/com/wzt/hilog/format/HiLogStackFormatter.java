package com.wzt.hilog.format;

public class HiLogStackFormatter extends HiLogFormatter<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement element : input
        ) {
            stringBuilder.append(element.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
