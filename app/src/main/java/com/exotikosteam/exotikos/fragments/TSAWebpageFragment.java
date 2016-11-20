package com.exotikosteam.exotikos.fragments;

import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.exotikosteam.exotikos.R;
import com.exotikosteam.exotikos.databinding.FragmentTsaWebpageBinding;

/**
 * Created by lramaswamy on 11/19/16.
 */

public class TSAWebpageFragment extends Fragment {

    FragmentTsaWebpageBinding fragmentTsaWebpageBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configure related browser settings

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTsaWebpageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tsa_webpage, container, false);

        fragmentTsaWebpageBinding.webview.getSettings().setLoadsImagesAutomatically(true);
        fragmentTsaWebpageBinding.webview.getSettings().setJavaScriptEnabled(true);
        fragmentTsaWebpageBinding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        fragmentTsaWebpageBinding.webview.setWebViewClient(new TsaWebpageBrowser());
        // Load the initial URL
        fragmentTsaWebpageBinding.webview.loadUrl("https://www.tsa.gov/travel/security-screening");
        return fragmentTsaWebpageBinding.getRoot();
    }

    // Manages the behavior when URLs are loaded
    private class TsaWebpageBrowser extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

    public static TSAWebpageFragment newInstance() {
        TSAWebpageFragment tsaWebpageFragment = new TSAWebpageFragment();
        Bundle bundle = new Bundle();
        //bundle.putParcelable(Constants.PARAM_TRIP, Parcels.wrap(trip));
        tsaWebpageFragment.setArguments(bundle);
        return tsaWebpageFragment;
    }
}
