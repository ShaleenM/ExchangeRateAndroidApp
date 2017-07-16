package com.shaleen.exchangerate;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mathurs on 6/26/17.
 */
public class ForexRateData {

    private HashMap<String, String> rateMap;
    public ArrayList<String> currList;

    public ForexRateData() {
        this.rateMap = new HashMap<>();
        this.currList = new ArrayList<>();
    }

    public HashMap<String, String> getRateMap() {
        return rateMap;
    }

    public ArrayList<String> getCurrList(){
        return currList;
    }

    public void addRate(String curr, String rate){
        if(curr == null || curr.length() == 0 || rate == null || rate.length() == 0){
            Log.d("Warning", "Curr / Rate is null / empty");
            return;
        }
        rateMap.put(curr, rate);
        currList.add(curr);
    }
}
