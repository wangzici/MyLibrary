package com.wzt.hilog.utils;

public class HiTrackUtils {
    public static StackTraceElement[] cropTracks(StackTraceElement[] stackTraceElements, int maxDepth, String packageName) {
        return cropTracks(getRealTracks(stackTraceElements, packageName), maxDepth);
    }

    private static StackTraceElement[] cropTracks(StackTraceElement[] stackTraceElements,int maxDepth) {
        maxDepth = Math.min(stackTraceElements.length, maxDepth);
        StackTraceElement[] result = new StackTraceElement[maxDepth];
        System.arraycopy(stackTraceElements, 0, result, 0, maxDepth);
        return result;
    }

    private static StackTraceElement[] getRealTracks(StackTraceElement[] stackTraceElements, String packageName) {
        for (int i = 0; i < stackTraceElements.length; i++) {
            String content = stackTraceElements[i].toString();
            if (!content.startsWith(packageName)) {
                StackTraceElement[] result = new StackTraceElement[i];
                System.arraycopy(stackTraceElements, 0, result, 0, i);
                return result;
            }
        }
        return stackTraceElements;
    }
}
