package com.exotikosteam.exotikos.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.ItemFlightStatusBinding;
import com.exotikosteam.exotikos.models.flightstatus.ScheduledFlight;

import java.util.List;

/**
 * Created by jesusft on 11/13/16.
 */

public class FlightResultsAdapter extends ArrayAdapter<ScheduledFlight> {

    public FlightResultsAdapter(Context context, List<ScheduledFlight> flights) {
        super(context, 0, flights);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScheduledFlight flight = getItem(position);

        ItemFlightStatusBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.
                    inflate(LayoutInflater.from(getContext()), R.layout.item_flight_status, null, false);
            convertView = binding.getRoot();
        } else {
            binding = (ItemFlightStatusBinding)convertView.getTag();
        }

        binding.setFlight(flight);
        convertView.setTag(binding);
        return convertView;
    }
}
