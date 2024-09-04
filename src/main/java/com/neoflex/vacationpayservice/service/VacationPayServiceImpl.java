package com.neoflex.vacationpayservice.service;

import com.neoflex.vacationpayservice.dto.VacationPayResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class VacationPayServiceImpl implements VacationPayService {

    private final VacationDaysService service;
    private static final double AVERAGE_DAYS_IN_MOUNT = 29.3;
    private static final double NDFL_PERCENT = 0.13;

    public VacationPayServiceImpl(VacationDaysService service) {
        this.service = service;
    }

    @Override
    public VacationPayResponse getVacationPay(BigDecimal averageSalary, int vacationDays) {
        BigDecimal averageDailySalary = averageSalary.divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MOUNT), 2, RoundingMode.HALF_UP);
        BigDecimal vacationPayWithNdfl = averageDailySalary.multiply(BigDecimal.valueOf(vacationDays)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal ndflTax = vacationPayWithNdfl.multiply(BigDecimal.valueOf(NDFL_PERCENT)).setScale(0, RoundingMode.HALF_UP);
        return new VacationPayResponse(vacationPayWithNdfl.subtract(ndflTax).setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public VacationPayResponse getVacationPayForHolidayDays(BigDecimal averageSalary, int vacationDays, LocalDate startDateVacation) {
        int days = vacationDays - service.getVacationDays(startDateVacation, vacationDays);
        BigDecimal averageDailySalary = averageSalary.divide(BigDecimal.valueOf(AVERAGE_DAYS_IN_MOUNT), 2, RoundingMode.HALF_UP);
        BigDecimal vacationPayWithNdfl = averageDailySalary.multiply(BigDecimal.valueOf(vacationDays).subtract(BigDecimal.valueOf(days))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal ndflTax = vacationPayWithNdfl.multiply(BigDecimal.valueOf(NDFL_PERCENT)).setScale(0, RoundingMode.HALF_UP);
        return new VacationPayResponse(vacationPayWithNdfl.subtract(ndflTax).setScale(2, RoundingMode.HALF_UP));
    }

}
