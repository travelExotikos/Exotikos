package com.exotikosteam.exotikos.utils;

import android.databinding.BindingAdapter;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jesusft on 11/13/16.
 */

public class BindingUtils {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter({"bind:asShortTime"})
    public static void convertToShortTime(TextView view, String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
        try {
            Date d = format.parse(date);
            view.setText(DateFormat.format("hh:mma", d));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"bind:asLongDate"})
    public static void convertToLongDate(TextView view, String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
        java.text.DateFormat outputFormat = DateFormat.getLongDateFormat(view.getContext());
        try {
            Date d = format.parse(date);
            view.setText(outputFormat.format(d));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
