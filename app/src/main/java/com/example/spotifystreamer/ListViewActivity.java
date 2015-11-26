package com.example.spotifystreamer;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Player;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Image;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;

public class ListViewActivity extends Activity {
    private static final String CLIENT_ID = "009a06ba7f1e42d1b237f72c12b2d8a7";
    private static final String REDIRECT_URI = "myStreamer://callback";
    private static final int REQUEST_CODE = 1337;
    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
/*
        final ListView myListView = (ListView) findViewById(R.id.myListView);
        final ArrayList<String> listOfArtist = new ArrayList<String>();
        final ArrayList<String> listOfArtistImages = new ArrayList<String>();
        final String searchValue;
        Bundle bum = getIntent().getExtras();
        if (bum != null) {
            searchValue = bum.getString("mySearch");
        }
        else
        {
            searchValue ="";
        }



        ActionBar aBar = getActionBar();
        if( aBar != null ){
            aBar.setTitle("Artists");
            aBar.setDisplayHomeAsUpEnabled(true);
            aBar.setIcon(R.drawable.ic_launcher);
        }



        final Button searchArtist = (Button) findViewById(R.id.searchArtist);
        final EditText artistText = (EditText) findViewById(R.id.artistText);
        artistText.setText(searchValue);

        searchArtist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent makeList = new Intent(ListViewActivity.this, ListViewActivity.class);
                makeList.putExtra("mySearch", artistText.getText().toString());
                startActivity(makeList);
                finish();
            }
        });


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });


        SpotifyApi api = new SpotifyApi();
        final SpotifyService spotify = api.getService();
            spotify.searchArtists(searchValue, new Callback<ArtistsPager>() {
                @Override
                public void success(ArtistsPager artistsPager, Response response) {
                   // Toast toast1 = Toast.makeText(getApplicationContext(), "GOT ARTISTS PAGER: " + response.getReason(), Toast.LENGTH_LONG);
                  //  toast1.show();
                    int numberOfArtists1 = 10;
                    if (artistsPager.artists.items.size() < numberOfArtists1)
                    {
                        Toast toast2 = Toast.makeText(getApplicationContext(), "Couldn't Find 10 Artists", Toast.LENGTH_LONG);
                        toast2.show();
                        numberOfArtists1 = artistsPager.artists.items.size();
                    }

                    for (int u = 0; u < numberOfArtists1; u++)
                    {
                        spotify.getArtists(artistsPager.artists.items.get(u).id, new Callback<Artists>() {

                            @Override
                            public void success(Artists artists, Response response) {

                                Artists ap = artists;
                                //  Toast toast2 = Toast.makeText(getApplicationContext(), "GOT ARTISTS: " + artists.artists.size(), Toast.LENGTH_LONG);
                                //  toast2.show();
                                int numberOfArtists2 = 10;
                                if ( artists.artists.size() < numberOfArtists2)
                                {
                                    numberOfArtists2 = artists.artists.size();
                                }
                                for (int j = 0; j < numberOfArtists2; j++) {
                                    listOfArtist.add(artists.artists.get(j).name);
                                    if ( artists.artists.get(j).images.size() < 1 )
                                    {
                                        listOfArtistImages.add("@drawable/ic_launcher");
                                        continue;
                                    }
                                    listOfArtistImages.add(artists.artists.get(j).images.get(0).url);
                                   //   Toast toast2 = Toast.makeText(getApplicationContext(), "num of images: " + artists.artists.get(j).images.size(), Toast.LENGTH_LONG);
                                   //   toast2.show();
                                }

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast toast2 = Toast.makeText(getApplicationContext(), "FAILED TO GET ARTISTS: ", Toast.LENGTH_LONG);
                                toast2.show();
                            }
                        });

                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Couldn't Find Any Artist", Toast.LENGTH_LONG);
                    toast1.show();
                }
            });



        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, listOfArtist, listOfArtistImages);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String item = (String) myListView.getAdapter().getItem(position);
                Toast toast2 = Toast.makeText(getApplicationContext(), "You Clicked: " + item, Toast.LENGTH_LONG);
                toast2.show();

                Intent makeList = new Intent(ListViewActivity.this, TopTracks.class);
                makeList.putExtra("mySearch", "worked");
                startActivity(makeList);
                finish();
            }
        });


    */
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               // Searches s= ((Searches)getApplicationContext());
               // int b = s.getIndex();
               // Toast toast2 = Toast.makeText(getApplicationContext(), b, Toast.LENGTH_LONG);
               // toast2.show();

                Intent makeList = new Intent(ListViewActivity.this, ListViewActivity.class);
                makeList.putExtra("mySearch", "");
                startActivity(makeList);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
