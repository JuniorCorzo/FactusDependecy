package io.github.juniorcorzo;

import io.github.juniorcorzo.dto.billing.request.BillingRequestDTO;
import io.github.juniorcorzo.dto.billing.request.CustomerRequestDTO;
import io.github.juniorcorzo.dto.billing.request.ItemsRequestDTO;
import io.github.juniorcorzo.dto.billing.request.WithholdingTaxesRequestDTO;
import io.github.juniorcorzo.dto.billing.response.BillingResponseDTO;
import io.github.juniorcorzo.services.TasksManagerService;
import io.github.juniorcorzo.tasks.CreateBillTask;
import io.github.juniorcorzo.tasks.DownloadPDFTask;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FactusDependency {
    public static void main(String[] args) {
        BillingRequestDTO request = new BillingRequestDTO(
                8,
                null,
                "EZ9860EZ",
                "",
                "1",
                LocalDate.now().toString(),
                null,
                null,
                null,
                new CustomerRequestDTO(
                        "1007196987",
                        null,
                        null,
                        null,
                        "Angel Corzo",
                        "Calle 5 avenida 6",
                        "Josedanielmmf@gmail.com",
                        "1234567890",
                        "1",
                        "21",
                        "3",
                        "846"
                ),
                List.of(
                        new ItemsRequestDTO(
                                "1234",
                                "producto prueba",
                                3,
                                "0",
                                210000.00,
                                "19.00",
                                70,
                                1,
                                0,
                                1,
                                new WithholdingTaxesRequestDTO[0]
                        )
                )
        );

        try {
            TasksManagerService tasksManagerService = new TasksManagerService();
            BillingResponseDTO response = tasksManagerService.submitTaskNow(new CreateBillTask(request));
            System.out.println(response);
            tasksManagerService.submitTaskNow(new DownloadPDFTask(response.bill().number()));
            tasksManagerService.shutdownNow();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}