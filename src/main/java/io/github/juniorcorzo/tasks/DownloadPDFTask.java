package io.github.juniorcorzo.tasks;

import io.github.juniorcorzo.services.BillingService;

public class DownloadPDFTask implements Runnable {
    private final String number;

    public DownloadPDFTask(String number) {
        this.number = number;
    }

    @Override
    public void run() {
        new BillingService().downloadBillPdf(number);
    }
}
