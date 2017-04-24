package com.example.jerryduran.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Location;

import java.util.List;

/**
 * Created by CthulhuInACan on 2/22/2017.
 */

public class Details extends Activity implements  OnMapReadyCallback{
    private TextView textView;
    private ArrayList<String> quotes2;
    private String query;
    private GoogleMap mGoogleMap;

    private float xCoordinate;
    private float yCoordinate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        this.textView = (TextView) findViewById(R.id.number);
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

        //String xTemp = quotes.get(3);
       // String yTemp = quotes.get(4);

        //xCoordinate = Float.valueOf(xTemp);
        //yCoordinate = Float.valueOf(yTemp);

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

        LatLng ll = new LatLng(38.5593836, -121.4234791);
        //LatLng ll = new LatLng(xCoordinate, yCoordinate); //Use th
        //TEST

        // Sets the map type to be "hybrid"
        //Add map marker here
        googleMap.addMarker(new MarkerOptions().position(ll).title(((TextView) findViewById(R.id.treeName)).getText().toString()));

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        CameraUpdateFactory.zoomBy(10.0f);

        mGoogleMap.moveCamera(update);
    }

/*
    public void onBackButtonClicked(View v){
        Intent i = new Intent(Details.this, Display.class);
        i.putExtra("query", query);
        startActivity(i);
    }
*/

}