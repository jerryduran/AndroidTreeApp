package com.roncoleman.treeid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

/**
 * Created by CthulhuInACan on 2/22/2017.
 */

public class Details2 extends Activity {
    private TextView textView;
    private ArrayList<String> quotes2;
    private String query;
    private GoogleMap mGoogleMap;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<String> gps;
    private ArrayList<String> gps2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details2);

        quotes2 = getIntent().getStringArrayListExtra("quotes2");

        this.textView = (TextView) findViewById(R.id.treeName);
        String test = quotes2.get(1);
        textView.setText(test);

        this.textView = (TextView) findViewById(R.id.Desc1);
        test = quotes2.get(5);
        textView.setText(test);

        this.textView = (TextView) findViewById(R.id.Desc2);
        test = quotes2.get(6);
        textView.setText(test);

        this.textView = (TextView) findViewById(R.id.Desc3);
        test = quotes2.get(7);
        textView.setText(test);

        this.textView = (TextView) findViewById(R.id.Desc4);
        test = quotes2.get(8);
        textView.setText(test);

        this.textView = (TextView) findViewById(R.id.Desc5);
        test = quotes2.get(9);
        textView.setText(test);
    }

}