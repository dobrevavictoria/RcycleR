package com.example.pc.rcycler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
 private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            value = extras.getString("value");
        }

    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
           if(Integer.parseInt(value) == 1 || Integer.parseInt(value) == 9) { player.cueVideo("edXimuzIVhk");} // Plays https://www.youtube.com/watch?v=edXimuzIVhk
            if(Integer.parseInt(value) == 2 || Integer.parseInt(value) == 16) {player.cueVideo("UqGaRqjWICU");} //https://www.youtube.com/watch?v=UqGaRqjWICU
            if(Integer.parseInt(value) == 3 || Integer.parseInt(value) == 11) {player.cueVideo("de1e83OxOsg");}//https://www.youtube.com/watch?v=de1e83OxOsg
            if(Integer.parseInt(value) == 4 || Integer.parseInt(value) == 10) {player.cueVideo("C4CeQ8QHo08");}//https://www.youtube.com/watch?v=C4CeQ8QHo08
            if(Integer.parseInt(value) == 5 ||Integer.parseInt(value) == 12) {player.cueVideo("iWE1AIRsneo");}//https://www.youtube.com/watch?v=iWE1AIRsneo
            if(Integer.parseInt(value) == 6 ||Integer.parseInt(value) == 13) {player.cueVideo("tByCDBWmY5A");}//https://www.youtube.com/watch?v=tByCDBWmY5A
            if(Integer.parseInt(value) == 7 || Integer.parseInt(value) == 14) {player.cueVideo("JxQdpt09U3U");}//https://www.youtube.com/watch?v=JxQdpt09U3U
            if(Integer.parseInt(value) == 8 || Integer.parseInt(value) == 15) {player.cueVideo("BN1T7VVdgxg");}//https://www.youtube.com/watch?v=BN1T7VVdgxg
            if(Integer.parseInt(value) == 17) {player.cueVideo("XX3WEe5TaAU");}//https://www.youtube.com/watch?v=XX3WEe5TaAU
        }
    }



    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}