package com.neoflex.vacationpayservice.dto;

import java.math.BigDecimal;

public class VacationPayResponse {

    private BigDecimal vacationPay;

    public VacationPayResponse(BigDecimal vacationPay) {
        this.vacationPay = vacationPay;
    }

    public BigDecimal getVacationPay() {
        return vacationPay;
    }

    public void setVacationPay(BigDecimal vacationPay) {
        this.vacationPay = vacationPay;
    }
}
