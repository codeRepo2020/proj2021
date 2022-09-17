package com.projects.proj2021.modal;

import java.util.Objects;

public class RequestByDuration {

    String mf;
    int year;
    int days;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getMf() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf = mf;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestByDuration)) return false;
        RequestByDuration that = (RequestByDuration) o;
        return getYear() == that.getYear() && getDays() == that.getDays() && Objects.equals(getMf(), that.getMf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMf(), getYear(), getDays());
    }

    @Override
    public String toString() {
        return "RequestByDuration{" +
                "mf='" + mf + '\'' +
                ", year=" + year +
                ", date=" + days +
                '}';
    }
}
