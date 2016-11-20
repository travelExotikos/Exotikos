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
import com.exotikosteam.exotikos.databinding.FragmentSecurityHelpBinding;
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.utils.Constants;

/**
 * Created by lramaswamy on 11/16/16.
 */

public class SecurityCheckingHelpFragment extends Fragment {

    OnButtonsClicks listener;
    FragmentSecurityHelpBinding fragmentSecurityHelpBinding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnButtonsClicks) {
            listener = (OnButtonsClicks) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentSecurityHelpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_help, container, false);

        fragmentSecurityHelpBinding.btnSecurityVideo.setOnClickListener(v -> {
            handleLaunchSecurityVidoeHelpPage();
        });
        return fragmentSecurityHelpBinding.getRoot();
    }

    public static SecurityCheckingHelpFragment newInstance() {
        SecurityCheckingHelpFragment fragmentSecurityHelp = new SecurityCheckingHelpFragment();
        Bundle bundle = new Bundle();
        fragmentSecurityHelp.setArguments(bundle);
        return fragmentSecurityHelp;
    }

    private void handleLaunchSecurityVidoeHelpPage() {
        listener.handleButtonsClicks(Constants.GO_TO_SECURITY_VIDEO_HINT);
    }
}
