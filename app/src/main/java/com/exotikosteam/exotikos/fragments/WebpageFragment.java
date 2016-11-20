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
import com.exotikosteam.exotikos.databinding.FragmentWebpageBinding;
import com.exotikosteam.exotikos.utils.Constants;

/**
 * Created by lramaswamy on 11/19/16.
 */

public class WebpageFragment extends Fragment {

    FragmentWebpageBinding fragmentWebpageBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configure related browser settings

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentWebpageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webpage, container, false);
        String webpageURL = getArguments().getString(Constants.WEBPAGE_URL);
        fragmentWebpageBinding.webview.getSettings().setLoadsImagesAutomatically(true);
        fragmentWebpageBinding.webview.getSettings().setJavaScriptEnabled(true);
        fragmentWebpageBinding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        fragmentWebpageBinding.webview.setWebViewClient(new TsaWebpageBrowser());
        // Load the initial URL
        fragmentWebpageBinding.webview.loadUrl(webpageURL);
        return fragmentWebpageBinding.getRoot();
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

    public static WebpageFragment newInstance(String webpageURL) {
        WebpageFragment webpageFragment = new WebpageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.WEBPAGE_URL, webpageURL);
        webpageFragment.setArguments(bundle);
        return webpageFragment;
    }
}
