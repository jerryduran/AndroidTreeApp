package com.example.jerryduran.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CthulhuInACan on 2/22/2017.
 */

public class Details extends Activity{
    private TextView textView;
    private ArrayList<String> quotes2;

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
    }
/*
    public void onBackButtonClicked(View v){
        Intent i = new Intent(Details.this, Display.class);
        i.putExtra("query", query);
        startActivity(i);
    }
*/

}