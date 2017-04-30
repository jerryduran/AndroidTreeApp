package com.example.jerryduran.myapplication;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
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
import android.support.v7.widget.Toolbar;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.ActionBar;
import android.support.design.widget.NavigationView;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.jerryduran.myapplication.R.id.number;
import static com.example.jerryduran.myapplication.R.id.searchTree;

public class MainActivity extends AppCompatActivity {
    private SearchView mySearchView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

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
        nv = (NavigationView) findViewById(R.id.nav_menu);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                 @Override
                                                 public boolean onNavigationItemSelected(final MenuItem menuItem) {

                                                     int id = menuItem.getItemId();
                                                     switch (id) {
                                                         case R.id.all_trees:
                                                             // Do something
                                                             Toast.makeText(MainActivity.this, "ALL Trees", Toast.LENGTH_SHORT).show();
                                                             return true;

                                                         case R.id.favorites:
                                                             // Do something
                                                             Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                                                         return true;
                                                         case R.id.tree_of_the_month:
                                                             // Do something
                                                             Toast.makeText(MainActivity.this, "Tree of the month", Toast.LENGTH_SHORT).show();
                                                         default:
                                                             return true;
                                                     }


                                                 }
                                             });


        mySearchView = (SearchView) findViewById(R.id.searchTree);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO: Add failure message if nothing found. (Either move actual search to main and fail here, or add failure message to Display.java)
                boolean isID = true;
                Intent i;
                for (int j = 0; j < query.length(); j++) {
                    if (Character.isDigit(query.charAt(j))) {
                    } else {
                        isID = false;
                    }
                }
                if (isID == true) {
                    ArrayList<String> quotes = databaseAccess.getTree(Integer.parseInt(query));
                    if (quotes.get(0) != null) {
                        ArrayList<String> quotes2 = databaseAccess.getSpecies(Integer.parseInt(quotes.get(1)));

                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        i = new Intent(MainActivity.this, Display.class);
                        i.putStringArrayListExtra("quotes", quotes);
                        i.putStringArrayListExtra("quotes2", quotes2);

                        i.setAction(Intent.ACTION_SEARCH);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    } else {
                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        Toast.makeText(getApplicationContext(), "Tree ID \"" + query + " \" not found.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    ArrayList<String> quotes2 = databaseAccess.getSpeciesByName(query);
                    if (quotes2.get(0) != null) {
                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        i = new Intent(MainActivity.this, TreeSpecies.class);
                        i.putStringArrayListExtra("quotes2", quotes2);

                        i.setAction(Intent.ACTION_SEARCH);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    } else {
                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        Toast.makeText(getApplicationContext(), "No tree found with \"" + query + "\" in its name.", Toast.LENGTH_SHORT).show();
                    }

                }
                //finish();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Activate the navigation drawer toggle();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}













