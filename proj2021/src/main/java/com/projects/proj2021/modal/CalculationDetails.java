package com.projects.proj2021.modal;

import java.util.Objects;

public class CalculationDetails {

    double principal;
    double amount;
    double rate;
    double time;

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "modal{" +
                "principal=" + principal +
                ", amount=" + amount +
                ", rate=" + rate +
                ", time=" + time +
                '}';
    }
}
