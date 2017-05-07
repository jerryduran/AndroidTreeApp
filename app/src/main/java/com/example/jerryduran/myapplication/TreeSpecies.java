package com.example.jerryduran.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TreeSpecies extends Activity {
    private TextView textView;
    private ImageView imageView;
    private ImageButton favoriteButton;;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private ArrayList<String> quotes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treespecies);
        // Locks screen to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        quotes2 = getIntent().getStringArrayListExtra("quotes2");

        this.imageView = (ImageView) findViewById(R.id.treeImage);
        String test2 = quotes2.get(3);
        Picasso.with(this)
                .load(test2)
                .into(imageView);

        this.textView = (TextView) findViewById(R.id.treeDesc);
        String test3 = quotes2.get(4);
        textView.setText(test3);

        this.textView = (TextView) findViewById(R.id.treeName2);
        String test4 = quotes2.get(2);
        textView.setText(test4);

        favoriteButton = (ImageButton) findViewById(R.id.image_Favorite_Button);

        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        if(sharedPref.getInt( (((TextView) findViewById(R.id.treeName2)).getText().toString()),0) == 1)
            favoriteButton.setBackgroundResource(R.mipmap.ic_favorite_white_24dp);
        else
            favoriteButton.setBackgroundResource(R.mipmap.ic_favorite_border_white_24dp);


        favoriteButton.setOnClickListener(new View.OnClickListener() {
            public void  onClick(View v) {

                int tmp;
                tmp = sharedPref.getInt( (((TextView) findViewById(R.id.treeName2)).getText().toString()),0) == 0?1:0;

                if(tmp == 1)
                {
                    editor.putInt((((TextView) findViewById(R.id.treeName2)).getText().toString()) , tmp);
                    Toast.makeText(TreeSpecies.this, "Faved", Toast.LENGTH_LONG).show();
                    favoriteButton.setBackgroundResource(R.mipmap.ic_favorite_white_24dp);

                }
                else
                {
                    editor.remove( (((TextView) findViewById(R.id.treeName2)).getText().toString()));
                    Toast.makeText(TreeSpecies.this, "Unfaved", Toast.LENGTH_LONG).show();
                    favoriteButton.setBackgroundResource(R.mipmap.ic_favorite_border_white_24dp);
                }

                editor.apply();

            }
        });

    }

    public void onMoreButtonClicked(View v){
        Intent i = new Intent(TreeSpecies.this, Details2.class);
        i.putStringArrayListExtra("quotes2", quotes2);
        startActivity(i);
    }
}