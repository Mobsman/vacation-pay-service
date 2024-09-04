package com.neoflex.vacationpayservice.service;

import com.neoflex.vacationpayservice.dto.VacationPayResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class VacationPayServiceTest {

    @Mock
    private VacationDaysService vacationDaysService;

    @InjectMocks
    private VacationPayServiceImpl vacationPayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetVacationPay() {
        BigDecimal averageSalary = BigDecimal.valueOf(50000);
        int vacationDays = 14;

        VacationPayResponse result = vacationPayService.getVacationPay(averageSalary, vacationDays);

        BigDecimal expectedPay = BigDecimal.valueOf(20784.72);
        assertEquals(expectedPay, result.getVacationPay());
    }

    @Test
    void testGetVacationPayForHolidayDays() {
        BigDecimal averageSalary = BigDecimal.valueOf(50000);
        int vacationDays = 14;
        LocalDate startDateVacation = LocalDate.of(2024, 1, 1);

        Mockito.when(vacationDaysService.getVacationDays(startDateVacation, vacationDays)).thenReturn(10);

        VacationPayResponse result = vacationPayService.getVacationPayForHolidayDays(averageSalary, vacationDays, startDateVacation);

        BigDecimal expectedPay = BigDecimal.valueOf(14846.80);
        assertEquals(0, result.getVacationPay().compareTo(expectedPay));

        Mockito.verify(vacationDaysService).getVacationDays(startDateVacation, vacationDays);
    }
}
