package com.wzt.library;

import android.app.Application;

import com.google.gson.Gson;
import com.wzt.log.HiLogConfig;
import com.wzt.log.HiLogManager;
import com.wzt.log.printer.HiConsolePrinter;
import com.wzt.log.printer.HiFilePrinter;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HiLogManager.init(new HiLogConfig() {
            @Override
            protected int stackTraceDepth() {
                return super.stackTraceDepth();
            }

            @Override
            protected JsonParser injectJsonParser() {
                return new JsonParser() {
                    @Override
                    public String toJson(Object[] src) {
                        return new Gson().toJson(src);
                    }
                };
            }
        }, new HiConsolePrinter(), new HiFilePrinter(this));
    }
}
