package com.neoflex.vacationpayservice.service;

import com.neoflex.vacationpayservice.dto.VacationPayResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface VacationPayService {
    VacationPayResponse getVacationPay(BigDecimal averageSalary, int vacationDays);

    VacationPayResponse getVacationPayForHolidayDays(BigDecimal averageSalary, int vacationDays, LocalDate startDateVacation);
}
