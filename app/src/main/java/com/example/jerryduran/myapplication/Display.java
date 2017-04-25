package com.example.jerryduran.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Display extends AppCompatActivity{
    private TextView textView;
    private ImageView imageView;
    private ArrayList<String> quotes;
    private ArrayList<String> quotes2;
    private ActionMenuView myMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        myMenu = (ActionMenuView) toolbar.findViewById(R.id.myMenu);
        myMenu.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }
        });
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
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

    }

    public void onMoreButtonClicked(View v){
        Intent i = new Intent(Display.this, Details.class);
        i.putStringArrayListExtra("quotes2", quotes2);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_main, menu);

        //inflater.inflate(R.menu.menu_main, myMenu.getMenu());
        //MenuItem item = myMenu.getMenu().findItem(R.id.Fave);
        //item.setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Do someting
        return true;
    }


}