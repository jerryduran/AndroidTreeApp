package com.example.jerryduran.myapplication;


import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.jerryduran.myapplication.R.id.number;
import static com.example.jerryduran.myapplication.R.id.searchTree;

/**
 * Created by Chris Chan on 4/10/2017.
 */

public class SearchResults extends Activity {
    private ListView listView;
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);

        this.listView = (ListView)findViewById(R.id.results);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        query = getIntent().getStringExtra("query");
        List<String> quotes = databaseAccess.getTree(Integer.parseInt(query));
        List<String> quotes2 = databaseAccess.getSpecies(Integer.parseInt(quotes.get(1)));
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes2);
        this.listView.setAdapter(adapter);
    }
}
