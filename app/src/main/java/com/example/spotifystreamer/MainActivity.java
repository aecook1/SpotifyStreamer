package com.example.spotifystreamer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artists;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    private String KEY = "key";
    private String searchValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar aBar = getActionBar();
        if (aBar != null) {
            aBar.setTitle("Artists");
        }
        final Button searchArtist = (Button) findViewById(R.id.searchArtist);
        final EditText artistText = (EditText) findViewById(R.id.artistText);


        if (savedInstanceState != null)
        {


            final ArrayList<String> listOfArtist = new ArrayList<String>();
            final ArrayList<String> listOfArtistImages = new ArrayList<String>();
            final ListView myListView = (ListView) findViewById(R.id.myListView);

            searchValue = savedInstanceState.get(KEY).toString();
            Toast.makeText(this, "loading: " + searchValue, Toast.LENGTH_SHORT).show();

            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });

            SpotifyApi api = new SpotifyApi();
            final SpotifyService spotify = api.getService();
            spotify.searchArtists(searchValue, new Callback<ArtistsPager>() {
                @Override
                public void success(ArtistsPager artistsPager, Response response)
                {
                    int numberOfArtists1 = 10;
                    if (artistsPager.artists.items.size() < numberOfArtists1) {
                     //   Toast toast2 = Toast.makeText(getApplicationContext(), "Couldn't Find 10 Artists", Toast.LENGTH_LONG);
                      //  toast2.show();
                        numberOfArtists1 = artistsPager.artists.items.size();
                    }

                    for (int u = 0; u < numberOfArtists1; u++) {
                        spotify.getArtists(artistsPager.artists.items.get(u).id, new Callback<Artists>() {
                            @Override
                            public void success(Artists artists, Response response)
                            {
                                int numberOfArtists2 = 10;
                                if (artists.artists.size() < numberOfArtists2) {
                                    numberOfArtists2 = artists.artists.size();
                                }
                                for (int j = 0; j < numberOfArtists2; j++) {
                                    listOfArtist.add(artists.artists.get(j).name);
                                    if (artists.artists.get(j).images.size() < 1) {
                                        listOfArtistImages.add("@drawable/ic_launcher");
                                        continue;
                                    }
                                    listOfArtistImages.add(artists.artists.get(j).images.get(0).url);
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

            MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(MainActivity.this, listOfArtist, listOfArtistImages);
            myListView.setAdapter(adapter);
            myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) myListView.getAdapter().getItem(position);
                    Toast toast2 = Toast.makeText(getApplicationContext(), "You Clicked: " + item, Toast.LENGTH_LONG);
                    toast2.show();

                    Intent makeList = new Intent(MainActivity.this, TopTracks.class);
                    makeList.putExtra("mySearch", item);
                    startActivity(makeList);
                }
            });

        }
        searchArtist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                final ArrayList<String> listOfArtist = new ArrayList<String>();
                final ArrayList<String> listOfArtistImages = new ArrayList<String>();
                final ListView myListView = (ListView) findViewById(R.id.myListView);
                searchValue = artistText.getText().toString();
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    }
                });

                SpotifyApi api = new SpotifyApi();
                final SpotifyService spotify = api.getService();
                spotify.searchArtists(searchValue, new Callback<ArtistsPager>() {
                    @Override
                    public void success(ArtistsPager artistsPager, Response response)
                    {
                        int numberOfArtists1 = 10;
                        if (artistsPager.artists.items.size() < numberOfArtists1) {
                            Toast toast2 = Toast.makeText(getApplicationContext(), "Couldn't Find 10 Artists", Toast.LENGTH_LONG);
                            toast2.show();
                            numberOfArtists1 = artistsPager.artists.items.size();
                        }

                        for (int u = 0; u < numberOfArtists1; u++) {
                            spotify.getArtists(artistsPager.artists.items.get(u).id, new Callback<Artists>() {
                                @Override
                                public void success(Artists artists, Response response)
                                {
                                    int numberOfArtists2 = 10;
                                    if (artists.artists.size() < numberOfArtists2) {
                                        numberOfArtists2 = artists.artists.size();
                                    }
                                    for (int j = 0; j < numberOfArtists2; j++) {
                                        listOfArtist.add(artists.artists.get(j).name);
                                        if (artists.artists.get(j).images.size() < 1) {
                                            listOfArtistImages.add("@drawable/ic_launcher");
                                            continue;
                                        }
                                        listOfArtistImages.add(artists.artists.get(j).images.get(0).url);
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

                MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(MainActivity.this, listOfArtist, listOfArtistImages);
                myListView.setAdapter(adapter);
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String item = (String) myListView.getAdapter().getItem(position);
                        Toast toast2 = Toast.makeText(getApplicationContext(), "You Clicked: " + item, Toast.LENGTH_LONG);
                        toast2.show();

                        Intent makeList = new Intent(MainActivity.this, TopTracks.class);
                        makeList.putExtra("mySearch", item);
                        startActivity(makeList);
                    }
                });
               /* return true;
                }
                return false; */
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Toast.makeText(this, "saving: " + searchValue, Toast.LENGTH_SHORT).show();
       // searchValue = artistText.getText().toString();
        savedInstanceState.putString(KEY,searchValue );
        super.onSaveInstanceState(savedInstanceState);
    }
}
