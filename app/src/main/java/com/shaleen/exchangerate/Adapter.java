package com.shaleen.exchangerate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathurs on 7/16/17.
 */
public class Adapter extends ArrayAdapter<CurrRatePair> {

    private final Context context;
    private final List<CurrRatePair> objects;
    private final int resource;

    public Adapter(Context context, int resource, List<CurrRatePair> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View list_item = inflater.inflate(resource, parent, false);
        TextView currView = (TextView) list_item.findViewById(R.id.currency_text);
        TextView rateView = (TextView) list_item.findViewById(R.id.rate_text);
        CurrRatePair currRatePair = objects.get(position);
        currView.setText(currRatePair.getCurrency());
        rateView.setText(""+currRatePair.getRate());
        return list_item;
    }
}
