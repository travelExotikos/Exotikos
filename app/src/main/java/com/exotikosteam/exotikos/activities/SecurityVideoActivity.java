package com.exotikosteam.exotikos.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.exotikosteam.exotikos.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by lramaswamy on 11/14/16.
 */

public class SecurityVideoActivity extends YouTubeBaseActivity {

    private static String YOUTUBE_API_KEY = "AIzaSyARjvpGgXBMz86vY_j8UH0MKYuQdEiqWls";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_checkin_video);

        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.videoView);

        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("CEdHJi8DAxc");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(SecurityVideoActivity.this, "Youtube Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
