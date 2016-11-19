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

/**
 * Created by lramaswamy on 11/15/16.
 */

public class SecurityCheckinFragment extends Fragment {

    FragmentSecurityCheckinBinding securityCheckinBinding;
    TravelPrepFragment.OnButtonsClicks listener;

    public static SecurityCheckinFragment newInstance(boolean fromFragment) {
            SecurityCheckinFragment fragmentSecurity = new SecurityCheckinFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("fromFragment", fromFragment);
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
        boolean fromFragment = getArguments().getBoolean("fromFragment");
        if(!fromFragment)
            securityCheckinBinding.btnNext.setVisibility(View.GONE);
        else
            securityCheckinBinding.btnNext.setVisibility(View.VISIBLE);
        securityCheckinBinding.btnSecurityVideo.setOnClickListener(v -> {
            handleLaunchSecurityVidoeHelpPage();
        });

        securityCheckinBinding.btnNext.setOnClickListener(v -> {
            handleLaunchSecurityActivity();
        });

        return securityCheckinBinding.getRoot();
    }

    private void handleLaunchSecurityVidoeHelpPage() {
        listener.handleButtonsClicks("LaunchSecurityCheckinVideoHelpPage");
    }

    private void handleLaunchSecurityHelpPage() {
        listener.handleButtonsClicks("LaunchSecurityCheckinHelpPage");
    }

    private void handleLaunchSecurityActivity() {
        listener.handleButtonsClicks("LaunchSecurityCheckinActivity");
    }


}
