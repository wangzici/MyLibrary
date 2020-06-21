package com.wzt.hilog;

public class HiLogManager {
    private static volatile HiLogManager instance;
    private HiLogConfig hiLogConfig;

    private HiLogManager() {
    }

    public void init(HiLogConfig hiLogConfig) {
        this.hiLogConfig = hiLogConfig;
    }

    public static HiLogManager getInstance() {
        if (instance == null) {
            synchronized (HiLogManager.class) {
                if (instance == null) {
                    instance = new HiLogManager();
                }
            }
        }
        return instance;
    }

    public HiLogConfig getHiLogConfig() {
        return hiLogConfig;
    }
}
