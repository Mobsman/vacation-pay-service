package com.neoflex.vacationpayservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VacationDayServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private VacationDaysServiceImpl vacationDaysService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetVacationDays() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        int totalVacationDays = 5;

        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-01", String.class)).thenReturn("1");
        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-02", String.class)).thenReturn("1");
        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-03", String.class)).thenReturn("1");
        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-04", String.class)).thenReturn("1");
        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-05", String.class)).thenReturn("1");

        int result = vacationDaysService.getVacationDays(startDate, totalVacationDays);

        assertEquals(5, result);
    }

    @Test
    void testGetPublicHolidayDays() {
        List<LocalDate> vacationDaysList = Arrays.asList(
                LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 1, 3),
                LocalDate.of(2024, 1, 4),
                LocalDate.of(2024, 1, 5)
        );

        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-01", String.class)).thenReturn("1");
        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-02", String.class)).thenReturn("1");
        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-03", String.class)).thenReturn("1");
        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-04", String.class)).thenReturn("1");
        when(restTemplate.getForObject("https://isdayoff.ru/2024-01-05", String.class)).thenReturn("1");

        int result = vacationDaysService.getPublicHolidayDays(vacationDaysList);

        assertEquals(5, result);
    }
}
