package com.example.spotifystreamer;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Viewer extends Fragment implements Button.OnClickListener{


    private TextView f;
    private Button b;
    private boolean vis = false;
    public Viewer() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view  = inflater.inflate(R.layout.fragment_viewer, container, false);
        f = (TextView) view.findViewById(R.id.view);
        b = (Button) view.findViewById(R.id.mybutton);
        if (vis) {
           // b.setVisibility(View.VISIBLE);
        }
        b.setOnClickListener(this);

        return view;

    }

    public void onClick(View v){
        //Nothing here yet
    }


    public void setVisibility(boolean visible) {
        b.setVisibility(View.VISIBLE);
    }
}
