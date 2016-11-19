package com.exotikosteam.exotikos.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.utils.Constants;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import static android.content.Intent.ACTION_CALL;

/**
 * Created by ada on 11/17/16.
 */

public class ExotikosBaseActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final String TAG = ExotikosBaseActivity.class.getSimpleName();
    private static final int REQUEST_CALL = 0;

    DrawerLayout mDrawer;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar mToolbar;
    NavigationView mnvDrawer;

    String mPhone;

    protected void prepareDrawableMenu() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(mDrawerToggle);
        mnvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(mnvDrawer);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.bagRules,  R.string.airline);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_CODE_CONTACT) {
            if (Activity.RESULT_OK != resultCode) {
                Log.e(TAG, "Cannot call");
                return;
            }
            setPhoneFromConact(data);
            call();
        }
    }

    //================ DRAWABLE =================================
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.miCall:
                handlePhoneAction();
                break;
            case R.id.miMap:
                handleMapAction();
                break;
            case R.id.miTranslator:
                handleTranslatorAction();
                break;
        }
        mDrawer.closeDrawers();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (REQUEST_CALL == requestCode) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            } else {
                Toast.makeText(this, "You need to grant contact and call phone permissions to call", Toast.LENGTH_LONG).show();
            }
        }
    }

    //============== MAP =============

    private void handleMapAction() {
        //TODO read current GPS
        String uriString ="geo:0,0";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        intent.setPackage(Constants.GOOGLE_MAP_PACKAGE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //=================== TANSLATE ==========

    private void handleTranslatorAction() {
        String defaultLanguage = Locale.getDefault().getLanguage();
        Uri uri = uri = new Uri.Builder()
                .scheme("http")
                .authority("translate.google.com")
                .path("/m/translate")
                //.appendQueryParameter("q", "c'est l'meunier Mathurin qui caresse les filles au tic-tac du moulin")
                .appendQueryParameter("tl", (Locale.getDefault().getLanguage() == "en" ? "pl" : "en")) // target language
                .appendQueryParameter("sl", defaultLanguage) // source language
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage(Constants.GOOGLE_TRANSLATE_PACKAGE);

        if (intent.resolveActivity(getPackageManager()) == null) {
            //Go to gogole play store and install google translate
            uri = Uri.parse("market://details?id=" + Constants.GOOGLE_TRANSLATE_PACKAGE);
            intent = new Intent(Intent.ACTION_VIEW, uri);
        }
        startActivity(intent);

    }

    //================ CALL ================

    private void handlePhoneAction() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, Constants.REQUEST_CODE_CONTACT);
    }


    private void call() {
        Log.d(TAG, "try calling #" + mPhone);
        if (mPhone == null) {
            return;
        }
        Intent callIntent = new Intent(ACTION_CALL, Uri.parse(mPhone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            startActivity(callIntent);
        }
    }

    private void setPhoneFromConact(Intent contact) {
        Uri uri = contact.getData();
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = getContentResolver().query(uri, projection,
                null, null, null);
        // If the cursor returned is valid, get the phone number
        if (cursor != null && cursor.moveToFirst()) {
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = "tel:" + cursor.getString(numberIndex);
            mPhone = number;
            //return number;
        }
        Log.e(TAG, "cannot read phone #");
        //return null;
    }
}
