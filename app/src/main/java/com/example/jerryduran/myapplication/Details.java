package com.example.jerryduran.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by CthulhuInACan on 2/22/2017.
 */

public class Details extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
    }

    public void onBackButtonClicked(View v){
        Intent i = new Intent(Details.this, Display.class);
        startActivity(i);
    }


}