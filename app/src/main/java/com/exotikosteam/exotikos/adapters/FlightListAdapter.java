package com.exotikosteam.exotikos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.ItemFlightBinding;
import com.exotikosteam.exotikos.models.trip.Flight;

import java.util.List;

import rx.subjects.PublishSubject;

public class FlightListAdapter extends RecyclerSwipeAdapter<FlightListAdapter.ViewHolder> {

    public static final String TAG = FlightListAdapter.class.getSimpleName();

    private List<Flight> mFlights;
    private Context mContext;
    private View tweetView;

    private final PublishSubject<Flight> itemClickSubject = PublishSubject.create();
    private final PublishSubject<Flight> deleteSubject = PublishSubject.create();

    public FlightListAdapter(Context mContext, List<Flight> mFlights) {
        this.mFlights = mFlights;
        this.mContext = mContext;
    }

    public PublishSubject<Flight> getItemClickSubject() {
        return itemClickSubject;
    }

    public PublishSubject<Flight> getDeleteSubject() {
        return deleteSubject;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        tweetView = inflater.inflate(R.layout.item_flight, parent, false);
        ViewHolder vh = new ViewHolder(tweetView);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Flight flight = mFlights.get(position);

        holder.binding.rlContainer.setOnClickListener(v -> {
            itemClickSubject.onNext(flight);
        });

        holder.binding.swipe.setShowMode(SwipeLayout.ShowMode.LayDown);

        holder.binding.delete.setOnClickListener(v -> {
            mItemManger.removeShownLayouts(holder.binding.swipe);
            deleteSubject.onNext(flight);
            mItemManger.closeAllItems();
        });

        holder.binding.setFlight(flight);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mFlights.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemFlightBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = ItemFlightBinding.bind(itemView);
        }
    }
}
