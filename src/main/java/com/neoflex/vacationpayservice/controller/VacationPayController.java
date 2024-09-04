package com.neoflex.vacationpayservice.controller;

import com.neoflex.vacationpayservice.dto.VacationPayResponse;
import com.neoflex.vacationpayservice.service.VacationPayService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@RestController
@Validated
public class VacationPayController {

    private final VacationPayService service;

    public VacationPayController(VacationPayService service) {
        this.service = service;
    }

    @GetMapping("/calculate")
    public VacationPayResponse getVacationPay(@RequestParam @NotNull @Min(value = 1) BigDecimal averageSalary,
                                              @RequestParam @NotNull @Min(value = 1) Integer vacationDays,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDateVacation) {

        if (startDateVacation == null) {
            return service.getVacationPay(averageSalary, vacationDays);
        }
        return service.getVacationPayForHolidayDays(averageSalary, vacationDays, startDateVacation);
    }

}
