package ru.neoflex.roganov.model;

import java.util.List;

public class CreditPayments {
    private List<CreditPayment> schedule;

    public CreditPayments() {
    }

    public CreditPayments(List<CreditPayment> schedule) {
        this.schedule = schedule;
    }

    public List<CreditPayment> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<CreditPayment> schedule) {
        this.schedule = schedule;
    }
}
