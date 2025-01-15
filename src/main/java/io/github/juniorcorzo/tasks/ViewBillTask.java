package io.github.juniorcorzo.tasks;

import io.github.juniorcorzo.dto.billing.response.BillingResponseDTO;
import io.github.juniorcorzo.services.BillingService;

import java.util.concurrent.Callable;

public class ViewBillTask implements Callable<BillingResponseDTO> {
    private final BillingService billingService;
    private final String numberBill;

    public ViewBillTask(String numberBill) {
        this.billingService = new BillingService();
        this.numberBill = numberBill;
    }

    @Override
    public BillingResponseDTO call() {
        return this.billingService.getBill(numberBill);
    }
}
