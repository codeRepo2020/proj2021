package com.projects.proj2021.controllers;

import com.projects.proj2021.modal.AmfiResponse;
import com.projects.proj2021.modal.AmfiResponseDuration;
import com.projects.proj2021.modal.RequestByDuration;
import com.projects.proj2021.service.InvestmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/investment")
public class InvestmentController {

    @Autowired
    private InvestmentInterface service;


    @GetMapping("/ci")
    public double getCI(@RequestParam double principal,
                        @RequestParam double rate, @RequestParam double time) {
        return service.calculateCI(principal, rate, time);
    }

    @GetMapping("/sip")
    public double getSIP(@RequestParam double principal,
                         @RequestParam double rate, @RequestParam double time) {
        return service.calculateSIP(principal, rate, time);
    }

    @GetMapping("/si")
    public double getSI(@RequestParam double principal,
                        @RequestParam double rate, @RequestParam double time) {
        return service.calculateSI(principal, rate, time);
    }

    @GetMapping("/fetchAllMfs")
    public List<AmfiResponse> calculateTime(){
        return service.fetchAllMfs();
    }

    @PostMapping("/fetchByDuration")
    public List<AmfiResponseDuration> getDetailsByDate(@RequestBody RequestByDuration requestByDuration){
        System.out.println("days"+requestByDuration.getDays());
        System.out.println("year"+requestByDuration.getYear());
        return service.getDetailsByDate(requestByDuration);
    }

}
