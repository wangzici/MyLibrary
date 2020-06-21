package com.wzt.hilog.printer;

import android.util.Log;

import com.wzt.hilog.HiLogType;

public class ConsolePrinter implements IPrinter{
    @Override
    public void print(@HiLogType.LogType int type, String tag, String content) {
        //TODO: 如何判断换行
        Log.println(Log.DEBUG, tag, content);

    }
}
