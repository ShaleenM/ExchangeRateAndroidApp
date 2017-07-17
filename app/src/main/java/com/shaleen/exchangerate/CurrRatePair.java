package com.shaleen.exchangerate;

/**
 * Created by Mathurs on 7/16/17.
 */
public class CurrRatePair implements Comparable<CurrRatePair>{
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

    @Override
    public int compareTo(CurrRatePair another) {
        if(this.currency.compareTo(another.currency) > 0)
            return 1;
        else if(this.currency.compareTo(another.currency) < 0)
            return -1;
        else
            return 0;
    }
}