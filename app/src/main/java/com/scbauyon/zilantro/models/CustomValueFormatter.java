package com.scbauyon.zilantro.models;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by rygalang on 1/28/16.
 */
public class CustomValueFormatter implements ValueFormatter {
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        Point point = (Point) entry.getData();
        return point.getPoint() + "";
    }
}
