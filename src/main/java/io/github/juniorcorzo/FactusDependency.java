package io.github.juniorcorzo;

import io.github.juniorcorzo.dto.billing.request.BillingRequestDTO;
import io.github.juniorcorzo.dto.billing.request.CustomerRequestDTO;
import io.github.juniorcorzo.dto.billing.response.BillingResponseDTO;
import io.github.juniorcorzo.enums.DocumentIdentification;
import io.github.juniorcorzo.services.TasksManagerService;
import io.github.juniorcorzo.tasks.CreateBillTask;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class FactusDependency {
    public static void main(String[] args) {
        BillingRequestDTO request = new BillingRequestDTO(
                8,
                UUID.randomUUID().toString(),
                "",
                "1",
                LocalDate.now().toString(),
                new CustomerRequestDTO(
                        "1234567890",
                        "3",
                        "",
                        "",
                        "Angel Corzo",
                        "Calle 5 avenida 6",
                        "Josedanielmmf@gmail.com",
                        "1234567890",
                        "1",
                        "1",
                        String.valueOf(DocumentIdentification.CITIZENSHIP_ID.getId()),
                        "846"
                ),
                List.of()
        );

        try {
            TasksManagerService tasksManagerService = new TasksManagerService();
            BillingResponseDTO billResponse = tasksManagerService.obtainNow(new CreateBillTask(request));
            System.out.println(billResponse);
            tasksManagerService.shutDown();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}