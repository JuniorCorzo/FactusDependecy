package io.github.juniorcorzo.tasks;

import io.github.juniorcorzo.dto.billing.request.BillingRequestDTO;
import io.github.juniorcorzo.dto.billing.response.BillingResponseDTO;
import io.github.juniorcorzo.services.BillingService;

import java.util.concurrent.Callable;

public class CreateBillTask implements Callable<BillingResponseDTO> {
    private final BillingService billingService;
    private final BillingRequestDTO request;

    public CreateBillTask(BillingRequestDTO request) {
        billingService = new BillingService();
        this.request = request;
    }

    @Override
    public BillingResponseDTO call() throws Exception {
        return billingService.createAndValidateBilling(request);
    }
}
