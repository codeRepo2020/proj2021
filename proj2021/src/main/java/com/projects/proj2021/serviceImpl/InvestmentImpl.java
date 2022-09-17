package com.projects.proj2021.serviceImpl;

import com.projects.proj2021.modal.AmfiResponse;
import com.projects.proj2021.modal.AmfiResponseDuration;
import com.projects.proj2021.modal.RequestByDuration;
import com.projects.proj2021.service.InvestmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class InvestmentImpl implements InvestmentInterface {

    @Autowired
    RestTemplate restTemplate;

    /**
     * This method calculates compound interest
     *
     * @param principal
     * @param rate
     * @param time
     * @return amount
     */
    public double calculateCI(double principal, double rate, double time) {
        double amount = principal * Math.pow((1 + (rate / 100)), time);
        DecimalFormat df = new DecimalFormat("#.##");
        amount = Double.parseDouble(df.format(amount));
        return amount;
    }

    /**
     * This method calculates SIP
     *
     * @param principal
     * @param rate
     * @param timeInYear
     * @return amount
     */
    public double calculateSIP(double principal, double rate, double timeInYear) {
        double time = timeInYear * 12;
        double i = rate / 1200;
        double amount = principal * (Math.pow(1 + i, time) - 1) * (1 + i) / i;
        DecimalFormat df = new DecimalFormat("#.##");
        amount = Double.parseDouble(df.format(amount));
        return amount;
    }

    @Override
    public double calculateTime(double principal, double amount) {
        return 0;
    }

    @Override
    public List<AmfiResponse> fetchAllMfs() {
        String result =
                restTemplate.getForObject(
                        "https://www.amfiindia.com/spages/NAVAll.txt",
                        String.class
                );
        List<AmfiResponse> amfiResponseList = parseData(result);
        return amfiResponseList;
    }

    List<AmfiResponse> parseData(String result) {
        String response = result.replace("\r", "\n");
        String[] arr = response.split("\n");
        List<AmfiResponse> amfiResponseList = new ArrayList<>();
        String provider = null;
        for (int i = 0; i <= arr.length - 1; i++) {
            AmfiResponse obj = new AmfiResponse();
            if (arr[i].split(";").length > 1) {
                obj.setSchemeCode(arr[i].split(";")[0]);
                obj.setIsin(arr[i].split(";")[1]);
                obj.setIsin2(arr[i].split(";")[2]);
                obj.setSchemeName(arr[i].split(";")[3]);
                obj.setNav(arr[i].split(";")[4]);
                obj.setDate(arr[i].split(";")[5]);
                obj.setProvider(provider);
                amfiResponseList.add(obj);
            }
        }
        return amfiResponseList;
    }

    List<AmfiResponseDuration> parseDataDuration (String result){

        String response = result.replace("\r", "\n");
        String[] arr = response.split("\n");
        List<AmfiResponseDuration> amfiResponseList = new ArrayList<>();
        for (int i = 0; i <= arr.length - 1; i++) {
            AmfiResponseDuration obj = new AmfiResponseDuration();
            if (arr[i].split(";").length > 1) {
                obj.setSchemeCode(arr[i].split(";")[0]);
                obj.setSchemeName(arr[i].split(";")[1]);
                obj.setIsin(arr[i].split(";")[2]);
                obj.setIsin2(arr[i].split(";")[3]);
                obj.setNav(arr[i].split(";")[4]);
                obj.setDate(arr[i].split(";")[7]);
                amfiResponseList.add(obj);
            }
        }
        return amfiResponseList;

    }

    /**
     * This method calculates SI
     *
     * @param principal
     * @param rate
     * @param time
     * @return amount
     */
    public double calculateSI(double principal, double rate, double time) {
        double si = (principal * rate * time) / 100;
        double amount = principal + si;
        DecimalFormat df = new DecimalFormat("#.##");
        amount = Double.parseDouble(df.format(amount));
        return amount;
    }

    public void getFundDetails(String name){
        String referenceNumber = getReferenceNumber(name);
        String result =
                restTemplate.getForObject(
                        "https://portal.amfiindia.com/DownloadNAVHistoryReport_Po.aspx?mf={mf}&tp=1&frmdt=11-Sep-2022&todt=13-Sep-2022",
                        String.class,referenceNumber
                );

    }

    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDateTime now = LocalDateTime.now();
       return dtf.format(now);
    }

    public String getfromDate(RequestByDuration requestByDuration){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDateTime date = LocalDateTime.now();
        if(requestByDuration.getYear()!=0) {
            date = date.plus(-requestByDuration.getYear(), ChronoUnit.YEARS);
        }
        if(requestByDuration.getDays()!=0){
            date = date.plus(-requestByDuration.getDays(), ChronoUnit.DAYS);
        }
        return dtf.format(date);
    }

    public List<AmfiResponseDuration> getDetailsByDate(RequestByDuration requestByDuration){
        String fromDate = getfromDate(requestByDuration);
        String toDate = getCurrentDate();
        String result =
                restTemplate.getForObject(
                        "https://portal.amfiindia.com/DownloadNAVHistoryReport_Po.aspx?mf={mf}&tp=1&frmdt={fromDate}&todt={toDate}",
                        String.class,requestByDuration.getMf(),fromDate,toDate
                );
        List<AmfiResponseDuration> resultByDuration = parseDataDuration(result);
        return resultByDuration;
    }

    public String getReferenceNumber(String name) {
        Map<String, String> referenceStore = new HashMap<>();
        referenceStore.put("-1", "All");
        referenceStore.put("39", "ABN  AMRO Mutual Fund");
        referenceStore.put("3", "Aditya Birla Sun Life Mutual Fund");
        referenceStore.put("50", "AEGON Mutual Fund");
        referenceStore.put("1", "Alliance Capital Mutual Fund");
        referenceStore.put("53", "Axis Mutual Fund");
        referenceStore.put("46", "Bank of India Mutual Fund");
        referenceStore.put("4", "Baroda BNP Paribas Mutual Fund");
        referenceStore.put("36", "Benchmark Mutual Fund");
        referenceStore.put("59", "BNP Paribas Mutual Fund");
        referenceStore.put("32", "Canara Robeco Mutual Fund");
        referenceStore.put("60", "Daiwa Mutual Fund");
        referenceStore.put("31", "DBS Chola Mutual Fund");
        referenceStore.put("38", "Deutsche Mutual Fund");
        referenceStore.put("6", "DSP Mutual Fund");
        referenceStore.put("47", "Edelweiss Mutual Fund");
        referenceStore.put("40", "Fidelity Mutual Fund");
        referenceStore.put("51", "Fortis Mutual Fund");
        referenceStore.put("27", "Franklin Templeton Mutual Fund");
        referenceStore.put("8", "GIC Mutual Fund");
        referenceStore.put("49", "Goldman Sachs Mutual Fund");
        referenceStore.put("9", "HDFC Mutual Fund");
        referenceStore.put("37", "HSBC Mutual Fund");
        referenceStore.put("20", "ICICI Prudential Mutual Fund");
        referenceStore.put("57", "IDBI Mutual Fund");
        referenceStore.put("48", "IDFC Mutual Fund");
        referenceStore.put("68", "IIFCL Mutual Fund (IDF)");
        referenceStore.put("62", "IIFL Mutual Fund");
        referenceStore.put("11", "IL&amp;F S Mutual Fund");
        referenceStore.put("65", "IL&amp;FS Mutual Fund (IDF)");
        referenceStore.put("63", "Indiabulls Mutual Fund");
        referenceStore.put("14", "ING Mutual Fund");
        referenceStore.put("42", "Invesco Mutual Fund");
        referenceStore.put("70", "ITI Mutual Fund");
        referenceStore.put("16", "JM Financial Mutual Fund");
        referenceStore.put("43", "JPMorgan Mutual Fund");
        referenceStore.put("17", "Kotak Mahindra Mutual Fund");
        referenceStore.put("56", "L&amp;T Mutual Fund");
        referenceStore.put("18", "LIC Mutual Fund");
        referenceStore.put("69", "Mahindra Manulife Mutual Fund");
        referenceStore.put("45", "Mirae Asset Mutual Fund");
        referenceStore.put("19", "Morgan Stanley Mutual Fund");
        referenceStore.put("55", "Motilal Oswal Mutual Fund");
        referenceStore.put("54", "Navi Mutual Fund");
        referenceStore.put("21", "Nippon India Mutual Fund");
        referenceStore.put("73", "NJ Mutual Fund");
        referenceStore.put("58", "PGIM India Mutual Fund");
        referenceStore.put("44", "PineBridge Mutual Fund");
        referenceStore.put("34", "PNB Mutual Fund");
        referenceStore.put("10", "Principal Mutual Fund");
        referenceStore.put("13", "quant Mutual Fund");
        referenceStore.put("41", "Quantum Mutual Fund");
        referenceStore.put("74", "Samco Mutual Fund");
        referenceStore.put("22", "SBI Mutual Fund");
        referenceStore.put("64", "PPFAS Mutual Fund");
        referenceStore.put("52", "Shinsei Mutual Fund");
        referenceStore.put("67", "Shriram Mutual Fund");
        referenceStore.put("2", "Standard Chartered Mutual Fund");
        referenceStore.put("24", "SUN F&amp;C Mutual Fund");
        referenceStore.put("33", "Sundaram Mutual Fund");
        referenceStore.put("25", "Tata Mutual Fund");
        referenceStore.put("26", "Taurus Mutual Fund");
        referenceStore.put("72", "Trust Mutual Fund");
        referenceStore.put("61", "Union Mutual Fund");
        referenceStore.put("28", "UTI Mutual Fund");
        referenceStore.put("71", "WhiteOak Capital Mutual Fund");
        referenceStore.put("29", "Zurich India Mutual Fund");

        return referenceStore.get(name);
    };

}
