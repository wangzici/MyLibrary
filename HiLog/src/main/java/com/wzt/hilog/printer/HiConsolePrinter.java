package com.wzt.hilog.printer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.wzt.hilog.HiLogConfig;
import com.wzt.hilog.HiLogType;

public class HiConsolePrinter implements HiLogPrinter {
    @Override
    public void print(@NonNull HiLogConfig config, @HiLogType.TYPE int type, String tag, String printString) {
        final int maxLength = printString.length();
        final int maxLengthPerLine = config.getMaxLength();
        final int countOfSub = maxLength / maxLengthPerLine;
        if (countOfSub > 0) {
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                Log.println(type, tag, printString.substring(index, index + maxLengthPerLine));
                index += maxLengthPerLine;
            }
            if (index != maxLength) {
                Log.println(type, tag, printString.substring(index, maxLength));
            }
        } else {
            Log.println(type, tag, printString);
        }
    }
}
