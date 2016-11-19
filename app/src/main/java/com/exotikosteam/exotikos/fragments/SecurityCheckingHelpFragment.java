package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;

/**
 * Created by lramaswamy on 11/16/16.
 */

public class SecurityCheckingHelpFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_security_help, container, false);
        return view;
    }

    public static SecurityCheckingHelpFragment newInstance() {
        SecurityCheckingHelpFragment fragmentSecurityHelp = new SecurityCheckingHelpFragment();
        Bundle bundle = new Bundle();
        //bundle.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        fragmentSecurityHelp.setArguments(bundle);
        return fragmentSecurityHelp;
    }
}
