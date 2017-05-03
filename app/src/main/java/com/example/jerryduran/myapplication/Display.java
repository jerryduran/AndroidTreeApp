package com.example.jerryduran.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.design.widget.NavigationView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Display extends Activity{
    private TextView textView;
    private ImageView imageView;
    private ImageButton favoriteButton;
    private ArrayList<String> quotes;
    private ArrayList<String> quotes2;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private SearchView mySearchView;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

/*
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // method invoked only when the actionBar is not null.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);

        }
*/
        this.textView = (TextView) findViewById(R.id.number);

        quotes = getIntent().getStringArrayListExtra("quotes");
        quotes2 = getIntent().getStringArrayListExtra("quotes2");
        String test = quotes.get(0);
        textView.setText("#" + test);

        this.imageView = (ImageView) findViewById(R.id.treeImage);
        String test2 = quotes2.get(3);
        Picasso.with(this)
                .load(test2)
                .into(imageView);

        this.textView = (TextView) findViewById(R.id.Gender);
        test2 = quotes.get(2);
        textView.setText(test2);

        this.textView = (TextView) findViewById(R.id.treeDesc);
        String test3 = quotes2.get(4);
        textView.setText(test3);

        this.textView = (TextView) findViewById(R.id.treeName2);
        String test4 = quotes2.get(2);
        textView.setText(test4);

        this.textView = (TextView) findViewById(R.id.gpsCoor);
        test = quotes.get(3);
        test2 = quotes.get(4);
        textView.setText("(" + test + ", " + test2 + ")");

        favoriteButton = (ImageButton) findViewById(R.id.image_Favorite_Button);

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            public void  onClick(View v) {
                Toast.makeText(Display.this, "Liked", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onMoreButtonClicked(View v){
        Intent i = new Intent(Display.this, Details.class);
        i.putStringArrayListExtra("quotes2", quotes2);
        i.putStringArrayListExtra("quotes", quotes);
        startActivity(i);
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */



}