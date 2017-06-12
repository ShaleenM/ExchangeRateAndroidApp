package com.shaleen.exchangerate;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;

/**
 * Created by Aditi Asthana, Shaleen Mathur on 6/11/17.
 */
public class ForexRateWorker extends AsyncTask{

    public AsyncResponse response = null;

    public interface AsyncResponse {
        void ratesFetchingComplete();
    }

    public ForexRateWorker(AsyncResponse response) {
        this.response = response;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        ArrayList<String> ratesList = (ArrayList<String>) params[1];
        try {
            StringBuilder dataStr = null;
            URL website = new URL("http://api.fixer.io/latest?base=" + params[0]);

            HttpURLConnection connection = (HttpURLConnection) website.openConnection();
            connection.setRequestProperty("charset", "utf-8");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            dataStr = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                dataStr.append(inputLine);
                Log.d("JSON File ", inputLine);
            }

            in.close();

            JSONObject json = new JSONObject(dataStr.toString());
            JSONObject jsonRates = (JSONObject) json.get("rates");
            Log.d("Testing", "Length of Json Response ::" + jsonRates.length());
            ratesList.clear();

            Iterator<String> keys = jsonRates.keys();


            while(keys.hasNext()){
                String currency = keys.next();
                ratesList.add(currency + "::" + jsonRates.get(currency));
            }

            response.ratesFetchingComplete();

        } catch (Exception e) {
            Log.e("Download Data", "In Exception...");
            e.printStackTrace();
        }

        return null;
    }
}