package com.projects.proj2021.modal;

import java.util.Objects;

public class AmfiResponseDuration {

    private String schemeCode;
    private String isin;
    private String isin2;
    private String schemeName;
    private String nav;
    private String date;
    private String purchasePrice;
    private String salePrice;

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getIsin2() {
        return isin2;
    }

    public void setIsin2(String isin2) {
        this.isin2 = isin2;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AmfiResponseDuration)) return false;
        AmfiResponseDuration that = (AmfiResponseDuration) o;
        return Objects.equals(getSchemeCode(), that.getSchemeCode()) && Objects.equals(getIsin(), that.getIsin()) && Objects.equals(getIsin2(), that.getIsin2()) && Objects.equals(getSchemeName(), that.getSchemeName()) && Objects.equals(getNav(), that.getNav()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getPurchasePrice(), that.getPurchasePrice()) && Objects.equals(getSalePrice(), that.getSalePrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSchemeCode(), getIsin(), getIsin2(), getSchemeName(), getNav(), getDate(), getPurchasePrice(), getSalePrice());
    }

    @Override
    public String toString() {
        return "AmfiResponseDuration{" +
                "schemeCode='" + schemeCode + '\'' +
                ", isin='" + isin + '\'' +
                ", isin2='" + isin2 + '\'' +
                ", schemeName='" + schemeName + '\'' +
                ", nav='" + nav + '\'' +
                ", date='" + date + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", salePrice='" + salePrice + '\'' +
                '}';
    }
}
