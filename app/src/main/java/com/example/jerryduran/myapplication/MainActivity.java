package com.example.jerryduran.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.content.Context;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.jerryduran.myapplication.R.id.number;
import static com.example.jerryduran.myapplication.R.id.searchTree;

public class MainActivity extends AppCompatActivity {
    private SearchView mySearchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();


        mySearchView = (SearchView) findViewById(R.id.searchTree);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query){
                //TODO: Add failure message if nothing found. (Either move actual search to main and fail here, or add failure message to Display.java)
                boolean isID = true;
                Intent i;
                for(int j = 0; j < query.length(); j++){
                    if(Character.isDigit(query.charAt(j))){
                    }else{
                        isID = false;
                    }
                }
                if(isID == true){
                    ArrayList<String> quotes = databaseAccess.getTree(Integer.parseInt(query));
                    if(quotes.get(0) != null){
                        ArrayList<String> quotes2 = databaseAccess.getSpecies(Integer.parseInt(quotes.get(1)));

                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        i = new Intent(MainActivity.this, Display.class);
                        i.putStringArrayListExtra("quotes", quotes);
                        i.putStringArrayListExtra("quotes2", quotes2);

                        i.setAction(Intent.ACTION_SEARCH);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }else{
                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        Toast.makeText(getApplicationContext(), "Tree ID \"" + query + " \" not found.", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    ArrayList<String> quotes2 = databaseAccess.getSpeciesByName(query);
                    if(quotes2.get(0) != null){
                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        i = new Intent(MainActivity.this, TreeSpecies.class);
                        i.putStringArrayListExtra("quotes2", quotes2);

                        i.setAction(Intent.ACTION_SEARCH);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }else{
                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        Toast.makeText(getApplicationContext(), "No tree found with \""+ query + "\" in its name.", Toast.LENGTH_SHORT).show();
                    }

                }
                //finish();
                return false;
            }
        });
    }

/*
    public void onSearchClick(View v) {
        if (v.getId() == searchTree) {
            Intent i = new Intent(MainActivity.this, Display.class);
            String query = "13"; //TODO: Replace with input from search bar.
            i.putExtra("query", query);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();

        }
    }


}






