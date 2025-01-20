package io.github.juniorcorzo;

import io.github.juniorcorzo.dto.billing.request.BillingRequestDTO;
import io.github.juniorcorzo.dto.billing.request.CustomerRequestDTO;
import io.github.juniorcorzo.dto.billing.request.ItemsRequestDTO;
import io.github.juniorcorzo.dto.billing.request.WithholdingTaxesRequestDTO;
import io.github.juniorcorzo.dto.billing.response.BillingResponseDTO;
import io.github.juniorcorzo.enums.DocumentIdentification;
import io.github.juniorcorzo.services.TasksManagerService;
import io.github.juniorcorzo.tasks.CreateBillTask;
import io.github.juniorcorzo.tasks.DownloadPDFTask;
import io.github.juniorcorzo.tasks.ViewBillTask;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class FactusDependency {
    public static void main(String[] args) {
        BillingRequestDTO request = new BillingRequestDTO(
                8,
                "EZ9867EZ",
                "",
                "1",
                LocalDate.now().toString(),
                new CustomerRequestDTO(
                        "1007196987",
                        "",
                        "",
                        "",
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
//            System.out.println(tasksManagerService.obtainNow(new CreateBillTask(request)));
            tasksManagerService.submitTaskNow(new DownloadPDFTask("SETP990008561"));
            tasksManagerService.shutDown();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}