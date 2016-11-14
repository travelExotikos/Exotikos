package com.exotikosteam.exotikos.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.ActivityHelpBinding;
import com.exotikosteam.exotikos.utils.Constants;

/**
 * Created by ada on 11/13/16.
 */

public class HelpActivity extends AppCompatActivity {

    public static final String TAG = HelpActivity.class.getSimpleName();

    ActivityHelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help);
        setOnClickListener();
    }


    private void setOnClickListener() {
        binding.btnPhone.setOnClickListener(v -> {
            handlePhoneAction();
        });
        binding.btnMap.setOnClickListener(v -> {
            handleMapAction();
        });
        binding.btnTranslator.setOnClickListener(v -> {
            handleTranslatorAction();
        });
    }

    private void handleMapAction() {

    }

    private void handleTranslatorAction() {

    }

    private void handlePhoneAction() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(Phone.CONTENT_TYPE);
        startActivityForResult(intent, Constants.REQUEST_CODE_CONTACT);
    }


    private void call(String number) {
        Log.d(TAG, "try calling #" + number);
        if (number == null) {
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, PackageManager.PERMISSION_GRANTED);
        }
        //Maybe now we have permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_CONTACT) {
            if (Activity.RESULT_OK != resultCode) {
                Log.e(TAG, "Cannot call");
                return;
            }
            call(getPhoneFromConact(data));
        }
    }

    private String getPhoneFromConact(Intent contact) {
        Uri uri = contact.getData();
        String[] projection = new String[]{Phone.NUMBER};
        Cursor cursor = getContentResolver().query(uri, projection,
                null, null, null);
        // If the cursor returned is valid, get the phone number
        if (cursor != null && cursor.moveToFirst()) {
            int numberIndex = cursor.getColumnIndex(Phone.NUMBER);
            String number = "tel:" + cursor.getString(numberIndex);
            Log.d(TAG, "number : " + number);
            return number;
        }
        Log.e(TAG, "cannot read phone #");
        return null;
    }
}
