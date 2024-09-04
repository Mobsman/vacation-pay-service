package com.neoflex.vacationpayservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacationDaysServiceImpl implements VacationDaysService {

    private final RestTemplate restTemplate;
    private final String HOLIDAY_API = "https://isdayoff.ru/";

    public VacationDaysServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Integer getVacationDays(LocalDate startDateVacation, Integer days) {
        List<LocalDate> vacationDays = new ArrayList<>();

        for (int i = 0; i < days; i++) {
            vacationDays.add(startDateVacation.plusDays(i));
        }

        return getPublicHolidayDays(vacationDays);
    }

    @Override
    public Integer getPublicHolidayDays(List<LocalDate> vacationDaysList) {
        int days = 0;

        for (LocalDate localDate : vacationDaysList) {
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            String response = restTemplate.getForObject(HOLIDAY_API + localDate.format(DateTimeFormatter.ISO_LOCAL_DATE), String.class);

            if (response != null && response.equals("1") && dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                days++;
            }

            if (response != null && response.equals("1") && dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                days++;
            }
        }

        return days;
    }
}
