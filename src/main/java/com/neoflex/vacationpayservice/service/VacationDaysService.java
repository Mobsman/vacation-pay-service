package com.neoflex.vacationpayservice.service;

import java.time.LocalDate;
import java.util.List;

public interface VacationDaysService {
    Integer getVacationDays(LocalDate startDateVacation, Integer days);

    Integer getPublicHolidayDays(List<LocalDate> vacationDaysList);
}
