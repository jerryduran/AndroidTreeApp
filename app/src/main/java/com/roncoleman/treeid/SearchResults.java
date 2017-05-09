package com.roncoleman.treeid;


import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

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
        // Locks screen to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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



