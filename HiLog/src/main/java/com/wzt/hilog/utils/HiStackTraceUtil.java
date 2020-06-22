package com.wzt.hilog.utils;

public class HiStackTraceUtil {
    public static StackTraceElement[] cropTracks(StackTraceElement[] stackTraceElements, int maxDepth, String ignorePackage) {
        return cropTracks(getRealStackTrack(stackTraceElements, ignorePackage), maxDepth);
    }

    /**
     * 裁剪堆栈信息
     */
    private static StackTraceElement[] cropTracks(StackTraceElement[] callStack,int maxDepth) {
        int realDepth = callStack.length;
        if (maxDepth > 0) {
            realDepth = Math.min(realDepth, maxDepth);
        }
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }

    private static StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTraceElements, String ignorePackage) {
        final int allDepth = stackTraceElements.length;
        for (int i = 0; i < allDepth; i++) {
            String content = stackTraceElements[i].toString();
            if (!content.startsWith(ignorePackage)) {
                final int length = allDepth - i;
                StackTraceElement[] result = new StackTraceElement[length];
                System.arraycopy(stackTraceElements, i, result, 0, length);
                return result;
            }
        }
        return stackTraceElements;
    }
}
