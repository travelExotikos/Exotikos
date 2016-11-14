package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.exotikosteam.exotikos.ExotikosApplication;
import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.adapters.AirlinesAdapter;
import com.exotikosteam.exotikos.models.airline.Airline;
import com.exotikosteam.exotikos.webservices.flightstats.AirlinesApiEndpoint;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by jesusft on 11/13/16.
 */

public class AirlinePickDialogFragment extends DialogFragment {

    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.lvAirlines) ListView lvAirlines;

    private List<Airline> airlines;
    private AirlinesAdapter adapter;
    private final PublishSubject<Airline> selectSubject = PublishSubject.create();
    private AirlinesApiEndpoint airlinesService;

    public PublishSubject<Airline> getSelectSubject() {
        return selectSubject;
    }

    public static AirlinePickDialogFragment newInstance() {

        Bundle args = new Bundle();

        AirlinePickDialogFragment fragment = new AirlinePickDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(R.string.pick_airline);

        final View v = inflater.inflate(R.layout.fragment_airline_pick, container);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        airlines = new ArrayList<>();
        ExotikosApplication app = ((ExotikosApplication)getContext().getApplicationContext());
        airlinesService = app.getAirlinesService();
        airlinesService.getActive(app.getFligthStatsAppID(), app.getFligthStatsAppKey())
                .concatMapIterable(airlinesResponse -> airlinesResponse.getAirlines())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(airline -> {
                    airlines.add(airline);
                    adapter.notifyDataSetChanged();
                });

        setupList();
        setupListeners();
    }

    private void setupList() {
        adapter = new AirlinesAdapter(getContext(), airlines);
        lvAirlines.setAdapter(adapter);
    }

    private void setupListeners() {
        lvAirlines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectSubject.onNext(adapter.getItem(i));
                selectSubject.onCompleted();
            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0 || charSequence.length() >= 2) {
                    adapter.getFilter().filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
