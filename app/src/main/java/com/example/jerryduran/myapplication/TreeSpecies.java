package com.example.jerryduran.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TreeSpecies extends Activity{
    private TextView textView;
    private ImageView imageView;
    private ArrayList<String> quotes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treespecies);

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

    }

    public void onMoreButtonClicked(View v){
        Intent i = new Intent(TreeSpecies.this, Details.class);
        i.putStringArrayListExtra("quotes2", quotes2);
        startActivity(i);
    }
/*
    public void onBackButtonClicked(View v){
        Intent i = new Intent(TreeSpecies.this, MainActivity.class);
        startActivity(i);
    }
*/
}