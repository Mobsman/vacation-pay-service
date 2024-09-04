package com.neoflex.vacationpayservice.controller;

import com.neoflex.vacationpayservice.dto.VacationPayResponse;
import com.neoflex.vacationpayservice.service.VacationPayService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationPayController.class)
public class VacationPayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacationPayService service;

    @Test
    void testGetVacationPayWithoutStartDate() throws Exception {
        BigDecimal averageSalary = BigDecimal.valueOf(50000);
        int vacationDays = 14;
        VacationPayResponse expectedResponse = new VacationPayResponse(BigDecimal.valueOf(20784.72));

        Mockito.when(service.getVacationPay(averageSalary, vacationDays)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", averageSalary.toString())
                        .param("vacationDays", String.valueOf(vacationDays)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vacationPay").value(expectedResponse.getVacationPay()));
    }

    @Test
    void testGetVacationPayWithStartDate() throws Exception {
        BigDecimal averageSalary = BigDecimal.valueOf(50000);
        int vacationDays = 14;
        LocalDate startDateVacation = LocalDate.of(2024, 1, 1);
        VacationPayResponse expectedResponse = new VacationPayResponse(BigDecimal.valueOf(14846.80));

        Mockito.when(service.getVacationPayForHolidayDays(averageSalary, vacationDays, startDateVacation)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", averageSalary.toString())
                        .param("vacationDays", String.valueOf(vacationDays))
                        .param("startDateVacation", startDateVacation.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vacationPay").value(expectedResponse.getVacationPay()));
    }
}
