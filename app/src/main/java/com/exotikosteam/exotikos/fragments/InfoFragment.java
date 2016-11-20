package com.exotikosteam.exotikos.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by ada on 11/19/16.
 */

public class InfoFragment extends DialogFragment {

    private static final String BUNDLE_INFO = "bundleInfo";
    private static final String BUNDLE_TITLE = "bundleTitle";

        public InfoFragment() {
            // Empty constructor required for DialogFragment
        }

        public static InfoFragment newInstance(String title, String info) {
            InfoFragment frag = new InfoFragment();
            Bundle args = new Bundle();
            args.putString(BUNDLE_TITLE, title);
            args.putString(BUNDLE_INFO, info);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String title = getArguments().getString(BUNDLE_TITLE);
            String info = getArguments().getString(BUNDLE_INFO);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder.setMessage(info);
            alertDialogBuilder.setPositiveButton("Done",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            /*
            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            */
            return alertDialogBuilder.create();
        }
}
