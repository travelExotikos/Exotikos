package com.exotikosteam.exotikos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.ItemFlightBinding;
import com.exotikosteam.exotikos.models.trip.Flight;

import java.util.List;

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.ViewHolder> {

    public static final String TAG = FlightListAdapter.class.getSimpleName();

    private List<Flight> mFlights;
    private Context mContext;

    public FlightListAdapter(Context mContext, List<Flight> mFlights) {
        this.mFlights = mFlights;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_flight, parent, false);
        ViewHolder vh = new ViewHolder(tweetView);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Flight flight = mFlights.get(position);

        holder.binding.setFlight(flight);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mFlights.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemFlightBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = ItemFlightBinding.bind(itemView);
        }
    }
}
