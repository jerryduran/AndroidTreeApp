package com.roncoleman.treeid;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by CthulhuInACan on 2/22/2017.
 */

public class Details extends Activity implements  OnMapReadyCallback{
    private TextView textView;
    private ArrayList<String> quotes2;
    private ArrayList<String> quotes;
    private String query;
    private GoogleMap mGoogleMap;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<String> gps;
    private ArrayList<String> gps2;

    private float xCoordinate;
    private float yCoordinate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        // Locks screen to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.textView = (TextView) findViewById(R.id.number);
        quotes2 = getIntent().getStringArrayListExtra("quotes2");
        quotes = getIntent().getStringArrayListExtra("quotes");


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

        String xTemp = quotes.get(3);
        String yTemp = quotes.get(4);

        xCoordinate = Float.valueOf(xTemp);
        yCoordinate = Float.valueOf(yTemp);

        // Google Map
        initMap();
    }


    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap = googleMap;
        float zoom = 27;

        LatLng ll = new LatLng(xCoordinate, yCoordinate); //Use th


        // Sets the map type to be "hybrid"
        //Add map marker here
        googleMap.addMarker(new MarkerOptions().position(ll).title(((TextView) findViewById(R.id.treeName)).getText().toString()));

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        CameraUpdateFactory.zoomBy(10.0f);

        mGoogleMap.moveCamera(update);
    }

}