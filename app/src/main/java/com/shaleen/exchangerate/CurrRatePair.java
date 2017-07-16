package com.shaleen.exchangerate;

/**
 * Created by Mathurs on 7/16/17.
 */
public class CurrRatePair {
    String currency;
    Double rate;

    public CurrRatePair(String currency, Double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return currency + " --> " + rate;
    }
}