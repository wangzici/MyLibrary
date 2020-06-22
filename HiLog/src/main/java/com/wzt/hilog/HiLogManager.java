package com.wzt.hilog;

import androidx.annotation.NonNull;

import com.wzt.hilog.printer.HiLogPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HiLogManager {
    private static HiLogManager instance;
    private HiLogConfig config;
    private List<HiLogPrinter> printers = new ArrayList<>();

    private HiLogManager(HiLogConfig config, HiLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static void init(@NonNull HiLogConfig config,HiLogPrinter... printers) {
        instance = new HiLogManager(config, printers);
    }

    public static HiLogManager getInstance() {
        return instance;
    }

    public HiLogConfig getConfig() {
        return config;
    }

    public void addPrinter(HiLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(HiLogPrinter printer) {
        printers.remove(printer);
    }

    public List<HiLogPrinter> getPrinters() {
        return printers;
    }
}
