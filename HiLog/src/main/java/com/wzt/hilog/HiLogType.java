package com.wzt.hilog;

import android.util.Log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class HiLogType {

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    public @interface LogType {

    }

    public static int TYPE_V = 0;
    public static int TYPE_D = 1;
    public static int TYPE_I = 2;
    public static int TYPE_W = 3;
    public static int TYPE_E = 3;
}
