package com.exotikosteam.exotikos.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentSecurityCheckinBinding;
import com.exotikosteam.exotikos.models.trip.TripStatus;
import com.exotikosteam.exotikos.utils.Constants;

import org.parceler.Parcels;

/**
 * Created by lramaswamy on 11/15/16.
 */

public class SecurityCheckinFragment extends Fragment {

    FragmentSecurityCheckinBinding securityCheckinBinding;
    TravelPrepFragment.OnButtonsClicks listener;

    public static SecurityCheckinFragment newInstance(TripStatus trip) {
            SecurityCheckinFragment fragmentSecurity = new SecurityCheckinFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
            fragmentSecurity.setArguments(bundle);
            return fragmentSecurity;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TravelPrepFragment.OnButtonsClicks) {
            listener = (TravelPrepFragment.OnButtonsClicks) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        securityCheckinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_checkin, container, false);

        securityCheckinBinding.btnSecurityHelp.setOnClickListener(v -> {
            handleLaunchSecurityHelpPage();
        });

        securityCheckinBinding.btnSecurityVideo.setOnClickListener(v -> {
            handleLaunchSecurityVidoeHelpPage();
        });

        return securityCheckinBinding.getRoot();
    }

    private void handleLaunchSecurityVidoeHelpPage() {
        listener.handleButtonsClicks("LaunchSecurityCheckinVideoHelpPage", null);
    }

    private void handleLaunchSecurityHelpPage() {
        listener.handleButtonsClicks("LaunchSecurityCheckinHelpPage", null);
    }
}
