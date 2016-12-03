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
import com.exotikosteam.exotikos.interfaces.OnButtonsClicks;
import com.exotikosteam.exotikos.utils.Constants;

/**
 * Created by lramaswamy on 11/15/16.
 */

public class SecurityCheckinFragment extends Fragment {

    FragmentSecurityCheckinBinding securityCheckinBinding;
    OnButtonsClicks listener;

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
        if(context instanceof OnButtonsClicks) {
            listener = (OnButtonsClicks) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        securityCheckinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_security_checkin, container, false);

        //hide the next button if it is in the activity, instead of the fragment
        boolean fromFragment = getArguments().getBoolean("fromFragment");
        if(!fromFragment) {
            securityCheckinBinding.btnNext.setVisibility(View.GONE);
            securityCheckinBinding.spacer.setVisibility(View.GONE);
        }
        else {
            securityCheckinBinding.btnNext.setVisibility(View.VISIBLE);
            securityCheckinBinding.spacer.setVisibility(View.VISIBLE);

        }

        securityCheckinBinding.btnNext.setOnClickListener(v -> {
            handleLaunchSecurityActivity();
        });

        return securityCheckinBinding.getRoot();
    }



    private void handleLaunchSecurityActivity() {
        listener.handleButtonsClicks(Constants.GO_TO_SECURITY_CHECKING);
    }


}
