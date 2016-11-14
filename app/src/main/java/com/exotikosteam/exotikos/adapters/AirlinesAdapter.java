package com.exotikosteam.exotikos.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.ItemAirlineBinding;
import com.exotikosteam.exotikos.models.airline.Airline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesusft on 11/13/16.
 */

public class AirlinesAdapter extends ArrayAdapter<Airline> implements Filterable {

    private List<Airline> mAirlines;
    private ArrayList<Airline> mOriginalValues;
    private AirlineFilter mFilter;

    public AirlinesAdapter(Context context, List<Airline> airlines) {
        super(context, 0, airlines);
        mAirlines = airlines;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Airline airline = getItem(position);

        ItemAirlineBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.
                    inflate(LayoutInflater.from(getContext()), R.layout.item_airline, null, false);
            convertView = binding.getRoot();
        } else {
            binding = (ItemAirlineBinding)convertView.getTag();
        }

        binding.setAirline(airline);
        convertView.setTag(binding);
        return convertView;
    }

    @Override
    public int getCount() {
        return mAirlines.size();
    }

    @Nullable
    @Override
    public Airline getItem(int position) {
        return mAirlines.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new AirlineFilter();
        }
        return mFilter;
    }

    private class AirlineFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Airline> filtered = new ArrayList<Airline>();


            if (mOriginalValues == null) {
                mOriginalValues = new ArrayList<Airline>(mAirlines);
            }

            if (constraint == null || constraint.length() == 0) {
                results.values = mOriginalValues;
                results.count = mOriginalValues.size();
            } else {
                constraint = constraint.toString().toLowerCase();
                for (Airline a : mOriginalValues) {
                    if (a.getName() != null && a.getName().toLowerCase().contains(constraint.toString())) {
                        filtered.add(a);
                    } else if (a.getIata() != null && a.getIata().toLowerCase().contains(constraint.toString())) {
                        filtered.add(a);
                    } else if (a.getIcao() != null && a.getIcao().toLowerCase().contains(constraint.toString())) {
                        filtered.add(a);
                    }
                }

                results.count = filtered.size();
                results.values = filtered;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mAirlines = (List<Airline>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
