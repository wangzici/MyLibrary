package com.wzt.hilog;

import androidx.annotation.NonNull;

public class HiLogManager {
    private static HiLogManager instance;
    private HiLogConfig config;

    private HiLogManager(HiLogConfig config) {
        this.config = config;
    }

    public static void init(@NonNull HiLogConfig config) {
        instance = new HiLogManager(config);
    }

    public static HiLogManager getInstance() {
        return instance;
    }

    public HiLogConfig getConfig() {
        return config;
    }
}
