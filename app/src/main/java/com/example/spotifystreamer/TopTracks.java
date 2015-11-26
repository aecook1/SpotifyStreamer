package com.example.spotifystreamer;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import kaaes.spotify.webapi.android.models.TracksPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class TopTracks extends Activity  {

    private MediaPlayer mediaPlayer = null;
    private String url = "";
    static final String musicName = "musicName";
    static final String musicIndex = "musicIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tracks);
        if (savedInstanceState != null) {
            Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show();
        }

        ActionBar aBar = getActionBar();
        if (aBar != null) {
            aBar.setTitle("Tracks");
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setIcon(R.drawable.ic_launcher);
        }

        final ArrayList<String> listOfTracks = new ArrayList<String>();
        final ArrayList<String> listOfTrackImages = new ArrayList<String>();
        final ListView myListView2 = (ListView) findViewById(R.id.myListView2);
        final String[] myTracks = new String[10];
        final String[] myTrackImages = new String[10];

        final String searchValue;
        Bundle bum = getIntent().getExtras();
        if (bum != null) {
            searchValue = bum.getString("mySearch");
        }
        else
        {
            searchValue ="";
        }

        SpotifyApi api = new SpotifyApi();
        final SpotifyService spotify = api.getService();
        spotify.searchTracks(searchValue, new Callback<TracksPager>() {
            @Override
            public void success(TracksPager tracksPager, Response response) {

                for (int u = 0; u < tracksPager.tracks.items.size(); u++) {
                    spotify.getTracks(tracksPager.tracks.items.get(u).id, new Callback<Tracks>() {

                        @Override
                        public void success(Tracks tracks, Response response) {

                            for (int j = 0; j < tracks.tracks.size(); j++)
                            {
                                if (listOfTracks.contains(tracks.tracks.get(j).name))
                                {
                                }
                                else
                                {
                                    for ( int i = 0; i < 10; i++)
                                    {
                                        if (myTracks[i] == null)
                                        {
                                            String temp = tracks.tracks.get(j).preview_url;
                                            myTracks[i] = temp;

                                            listOfTracks.add(tracks.tracks.get(j).name);
                                            if (tracks.tracks.get(j).album.images.size() < 1) {
                                                listOfTrackImages.add("@drawable/ic_launcher");
                                                myTrackImages[i] = "@drawable/ic_launcher";
                                            }
                                            else
                                            {
                                                listOfTrackImages.add(tracks.tracks.get(j).album.images.get(0).url);
                                                myTrackImages[i] = tracks.tracks.get(j).album.images.get(0).url;
                                            }

                                            MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(TopTracks.this, listOfTracks, listOfTrackImages);
                                            myListView2.setAdapter(adapter);
                                            myListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                                                {
                                                    url = myTracks[position]; // your URL here
                                                    if (mediaPlayer != null)
                                                    {
                                                        mediaPlayer.release();
                                                        mediaPlayer = null;
                                                    }
                                                    mediaPlayer = new MediaPlayer();
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
                                                  //  int me =  mediaPlayer.getCurrentPosition();
                                                 //   mediaPlayer.seekTo(me);
                                                }
                                            });
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast toast2 = Toast.makeText(getApplicationContext(), "FAILED TO GET TRACKS: ", Toast.LENGTH_SHORT);
                            toast2.show();
                        }
                    });
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Couldn't Find Any Tracks", Toast.LENGTH_SHORT);
                toast1.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_tracks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mediaPlayer != null)
                {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.release();
                    }
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mediaPlayer != null)
        {
            if (mediaPlayer.isPlaying()) {
           //     mediaPlayer.release();

            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state

        if (mediaPlayer != null)
        {
            if (mediaPlayer.isPlaying()) {
                Toast.makeText(this, "saving", Toast.LENGTH_SHORT).show();
                savedInstanceState.putString(musicName, url);
                savedInstanceState.putInt(musicIndex, mediaPlayer.getCurrentPosition());
                mediaPlayer.release();

            }
        }
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

}