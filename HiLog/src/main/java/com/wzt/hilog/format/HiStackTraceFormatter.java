package com.wzt.hilog.format;

public class HiStackTraceFormatter implements HiLogFormatter<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] data) {
        if (data == null || data.length == 0) {
            return null;
        } else if (data.length == 1) {
            return "\t- " + data[0].toString();
        } else {
            StringBuilder sb = new StringBuilder(128);
            for (int i = 0; i < data.length; i++) {
                if (i == 0) {
                    sb.append("stackTrack: \n");
                }
                if (i != data.length - 1) {
                    sb
                            .append("\t├ ")
                            .append(data[i].toString())
                            .append("\n");
                } else {
                    sb
                            .append("\t└ ")
                            .append(data[i].toString());
                }
            }
            return sb.toString();
        }
    }
}
