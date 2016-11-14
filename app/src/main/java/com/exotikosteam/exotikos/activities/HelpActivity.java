package com.exotikosteam.exotikos.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.ActivityHelpBinding;

/**
 * Created by ada on 11/13/16.
 */

public class HelpActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    ActivityHelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help);
    }
}
