package com.example.jerryduran.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

import static com.example.jerryduran.myapplication.R.id.searchTree;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void onSearchClick(View v) {
      if(v.getId() == searchTree) {
           Intent i = new Intent(MainActivity.this, Display.class);
          i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(i);
           finish();

       }
    }
}

