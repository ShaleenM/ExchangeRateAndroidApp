package com.shaleen.exchangerate;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Mathurs on 6/26/17.
 */
public class ForexRateData {

    // TODO: 7/16/17 Hardcode Currency List to Fix the issue of spinner not showing the correct base rate. 
    private HashMap<String, CurrRatePair> rateMap;
    public ArrayList<CurrRatePair> currRatePairArrayList;
    public ArrayList<String> currList;

    public ForexRateData() {
        this.rateMap = new HashMap<>();
        this.currList = new ArrayList<>();
        this.currRatePairArrayList = new ArrayList<>();
    }

    public HashMap<String, CurrRatePair> getRateMap() {
        return rateMap;
    }

    public ArrayList<String> getCurrList(){
        return currList;
    }

    public void addRate(String curr, CurrRatePair currRatePair){
        if(curr == null || curr.length() == 0){
            Log.d("Warning", "Curr / Rate is null / empty");
            return;
        }
        rateMap.put(curr, currRatePair);
        currList.add(curr);
        currRatePairArrayList.add(currRatePair);
    }

    public void cleanObject(){
        rateMap.clear();
        currList.clear();
        currRatePairArrayList.clear();
    }

    public void sortData(){
        Collections.sort(currList);
        ;
    }

    public ArrayList<CurrRatePair> getCurrRatePairArrayList() {
        return currRatePairArrayList;
    }

    public void setCurrRatePairArrayList(ArrayList<CurrRatePair> currRatePairArrayList) {
        this.currRatePairArrayList = currRatePairArrayList;
    }

    public void setCurrList(ArrayList<String> currList) {
        this.currList = currList;
    }
}