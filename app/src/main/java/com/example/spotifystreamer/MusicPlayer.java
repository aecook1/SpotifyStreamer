package com.example.spotifystreamer;

import android.app.ActionBar;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;


public class MusicPlayer extends Activity {
    public MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ActionBar aBar = getActionBar();
        if (aBar != null) {
            aBar.setTitle("Tracks");
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setIcon(R.drawable.ic_launcher);
        }

        String url = "";
        Bundle bum = getIntent().getExtras();
        if (bum != null) {
            url = bum.getString("mySearch");
        }


        if( !mediaPlayer.isPlaying() ) {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(url);
            } catch (IOException e) {
            }
            try {
                mediaPlayer.prepare();
            } catch (IOException e) {
            }
            mediaPlayer.start();
        }
        else
        {
            Toast toast2 = Toast.makeText(getApplicationContext(), "Already playing a track", Toast.LENGTH_LONG);
            toast2.show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music_player, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               // mediaPlayer.release();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
