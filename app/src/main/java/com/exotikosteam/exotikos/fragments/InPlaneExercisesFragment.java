package com.exotikosteam.exotikos.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentInPlaneExercisesBinding;

import java.util.Locale;

public class InPlaneExercisesFragment extends Fragment {
    private final static String STEP_ARG_KEY = "step";

    private FragmentInPlaneExercisesBinding mBinding;

    public InPlaneExercisesFragment() {
        // Required empty public constructor
    }

    public static InPlaneExercisesFragment newInstance(int step) {
        InPlaneExercisesFragment fragment = new InPlaneExercisesFragment();
        Bundle args = new Bundle();
        args.putInt(STEP_ARG_KEY, step);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_in_plane_exercises, container, false);
        populateData();
        return mBinding.getRoot();
    }

    private void populateData() {
        int[][] data = {
                {R.string.in_plane_hint_exercise1_info, R.drawable.exercise1},
                {R.string.in_plane_hint_exercise2_info, R.drawable.exercise2},
                {R.string.in_plane_hint_exercise3_info, R.drawable.exercise3},
                {R.string.in_plane_hint_exercise4_info, R.drawable.exercise4},
                {R.string.in_plane_hint_exercise5_info, R.drawable.exercise5},
                {R.string.in_plane_hint_exercise6_info, R.drawable.exercise6},
                {R.string.in_plane_hint_exercise7_info, R.drawable.exercise7}};

        int text = data[getStep()][0];
        int image = data[getStep()][1];
        if (text >= 0 && !"en".equals(Locale.getDefault().getLanguage())) {
            mBinding.tvExerciseInfo.setText(text);
        } else {
            mBinding.tvExerciseInfo.setVisibility(View.GONE);
        }
        if (image >= 0) {
            mBinding.imageView.setImageResource(image);
        } else {
            mBinding.imageView.setVisibility(View.GONE);
        }
    }

    private int getStep() {
        return getArguments().getInt(STEP_ARG_KEY);
    }
}
