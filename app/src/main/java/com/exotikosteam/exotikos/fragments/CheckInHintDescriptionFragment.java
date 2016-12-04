package com.exotikosteam.exotikos.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentCheckInHintDescriptionBinding;

public class CheckInHintDescriptionFragment extends Fragment {

    FragmentCheckInHintDescriptionBinding mBinding;

    public CheckInHintDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_in_hint_description, container, false);
        populateData();
        return mBinding.getRoot();
    }

    public static CheckInHintDescriptionFragment newInstance() {
        CheckInHintDescriptionFragment fragment = new CheckInHintDescriptionFragment();
        return fragment;
    }

    private void populateData() {
        mBinding.tvDescription.setText(Html.fromHtml(getString(R.string.check_in_hint1_description)));
    }

}
