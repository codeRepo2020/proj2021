package com.projects.proj2021.service;

import com.projects.proj2021.modal.AmfiResponse;
import com.projects.proj2021.modal.AmfiResponseDuration;
import com.projects.proj2021.modal.RequestByDuration;

import java.util.List;

public interface InvestmentInterface {

     double calculateCI (double principal, double rate, double time);
     double calculateSI(double principal, double rate, double time);
     double calculateSIP(double principal, double rate, double time);
     double calculateTime(double principal, double amount);
     List<AmfiResponseDuration> getDetailsByDate(RequestByDuration requestByDuration);
     List<AmfiResponse> fetchAllMfs ();
}
