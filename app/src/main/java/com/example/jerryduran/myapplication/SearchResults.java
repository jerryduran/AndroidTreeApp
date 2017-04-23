package com.example.jerryduran.myapplication;


import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import android.app.ListActivity;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.jerryduran.myapplication.R.id.number;
import static com.example.jerryduran.myapplication.R.id.searchTree;

/**
 * Created by Chris Chan on 4/10/2017.
 */

public class SearchResults extends ListActivity {
    private ListView listView;
    private TextView content;
    private ArrayList<String> quotes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresults);

        content = (TextView) findViewById(R.id.output);

        quotes2 = getIntent().getStringArrayListExtra("quotes2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes2);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        String query;
        Intent i;

        query = (String) l.getItemAtPosition(position);

        quotes2 = databaseAccess.getSpeciesByNameFull(query);

        i = new Intent(SearchResults.this, TreeSpecies.class);
        i.putStringArrayListExtra("quotes2", quotes2);
        startActivity(i);
    }

}



