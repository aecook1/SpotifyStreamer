package com.example.spotifystreamer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Image;

/**
 * Created by a on 9/29/2015.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;
    private final ArrayList<String> images;
    //  private final Image artistImage;

    public MySimpleArrayAdapter(Context context, ArrayList<String> values, ArrayList<String> images) {

        super(context, R.layout.row_layout, values);
        this.context = context;
        this.values = values;
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values.get(position));
        String s = values.get(position);
        //imageView.setImageResource(R.drawable.ok);
        if (images.get(position).compareTo("") != 0) {
            Picasso.with(context).load(images.get(position)).into(imageView);
        }

        return rowView;
    }
}
