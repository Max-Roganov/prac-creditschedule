package ru.neoflex.roganov.model;

import java.util.Calendar;

public class CreditPayment {
    private int number;
    private Calendar date;
    private double paymentAmount;
    private double principalAmount;
    private double percentAmount;
    private double balance;

    public CreditPayment() {

    }

    public CreditPayment(int number, Calendar date, double paymentAmount, double principalAmount, double percentAmount, double balance) {
        this.number = number;
        this.date = date;
        this.paymentAmount = paymentAmount;
        this.principalAmount = principalAmount;
        this.percentAmount = percentAmount;
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getPercentAmount() {
        return percentAmount;
    }

    public void setPercentAmount(double percentAmount) {
        this.percentAmount = percentAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
