package com.wzt.hilog.format;

public class HiLogContentFormatter extends HiLogFormatter<Object[]> {
    @Override
    public String format(Object[] objects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : objects
        ) {
            stringBuilder.append(object.toString()).append('\n');
        }
        return stringBuilder.toString();
    }
}
