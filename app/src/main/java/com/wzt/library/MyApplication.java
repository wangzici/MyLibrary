package com.wzt.library;

import android.app.Application;

import com.wzt.hilog.HiLogConfig;
import com.wzt.hilog.HiLogManager;
import com.wzt.hilog.printer.ConsolePrinter;
import com.wzt.hilog.printer.IPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HiLogManager.getInstance().init(new HiLogConfig(){
            @Override
            protected boolean needThreadPrint() {
                return true;
            }

            @Override
            protected int maxDepth() {
                return super.maxDepth();
            }

            @Override
            protected List<IPrinter> listOfPrinter() {
                List<IPrinter> list = new ArrayList<>();
                list.add(new ConsolePrinter());
                return list;
            }
        });
    }
}
