package com.exotikosteam.exotikos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exotikosteam.exotikos.R;

/**
 * Created by lramaswamy on 11/20/16.
 */

public class RestrictedItemsFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restricted_items, container, false);
    }

    public static RestrictedItemsFragment newInstance() {
        RestrictedItemsFragment restrictedItemsFragment = new RestrictedItemsFragment();
        Bundle bundle = new Bundle();
        restrictedItemsFragment.setArguments(bundle);
        return restrictedItemsFragment;
    }
}
