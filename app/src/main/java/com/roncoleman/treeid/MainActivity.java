package com.roncoleman.treeid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SearchView mySearchView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView nv;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Delete database if the app has updated, then save current version.
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int oldVersion = prefs.getInt("oldVer", 0);
        if(BuildConfig.VERSION_CODE > oldVersion){

            this.deleteDatabase("csusTree.db");

            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("oldVer", BuildConfig.VERSION_CODE);
            editor.commit();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Locks screen to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

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
                                                     Intent i;
                                                     ArrayList<String> quotes;
                                                     ArrayList<String> quotes2;
                                                     switch (id) {
                                                         case R.id.all_trees:
                                                            quotes2 = databaseAccess.getSpeciesList();

                                                             mySearchView.setQuery("", false);
                                                             mySearchView.clearFocus();
                                                             i = new Intent(MainActivity.this, SearchResults.class);
                                                             i.putStringArrayListExtra("quotes2", quotes2);

                                                             i.setAction(Intent.ACTION_SEARCH);
                                                             i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                             startActivity(i);

                                                             return true;

                                                         case R.id.favorites:
                                                             // Do something

                                                             quotes2 = new ArrayList<String>();

                                                             quotes2.addAll(sharedPref.getAll().keySet());

                                                             if(!quotes2.isEmpty())
                                                             {
                                                                 i = new Intent(MainActivity.this, FavoriteList.class);
                                                                 i.putStringArrayListExtra("quotes2", quotes2);

                                                                 i.setAction(Intent.ACTION_SEARCH);
                                                                 i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                 startActivity(i);
                                                             }
                                                             else
                                                             {
                                                                 Toast.makeText(MainActivity.this, "No tree(s) saved", Toast.LENGTH_SHORT).show();
                                                             }

                                                             return true;

                                                         case R.id.tree_of_the_month:
                                                             quotes = databaseAccess.getTreeOfMonth();
                                                             quotes2 = databaseAccess.getSpecies(Integer.parseInt(quotes.get(0)));

                                                             Toast.makeText(getApplicationContext(), quotes.get(1), Toast.LENGTH_SHORT).show();


                                                             mySearchView.setQuery("", false);
                                                             mySearchView.clearFocus();
                                                             i = new Intent(MainActivity.this, TreeSpecies.class);
                                                             i.putStringArrayListExtra("quotes2", quotes2);

                                                             i.setAction(Intent.ACTION_SEARCH);
                                                             i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                             startActivity(i);
                                                             return true;

                                                         case R.id.about:
                                                             Toast.makeText(MainActivity.this, "Sacramento State Tree Identifier\r\nDeveloped for Prof. Ronald M. Coleman\r\nBy Chris Chan, Gerardo Duran,\r\nOleksandr Kabanets, and Victor Huba", Toast.LENGTH_LONG).show();
                                                             return true;

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
                boolean isID = true;
                boolean isValid = true;
                Intent i;
                char ch;
                for (int j = 0; j < query.length(); j++) {
                    ch = query.charAt(j);

                    if (!((ch >= 'a' && ch <= 'z')
                            || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9')
                            || (ch == ' ') || (ch == '-')))
                        isValid = false;

                    if ( !Character.isDigit(ch))
                        isID = false;

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

                }else if(isValid == true) {
                    //Try to make exact search.
                    ArrayList<String> quotes2 = databaseAccess.getSpeciesByNameFull(query);
                    if(quotes2.get(0) != null){
                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        i = new Intent(MainActivity.this, TreeSpecies.class);
                        i.putStringArrayListExtra("quotes2", quotes2);

                        i.setAction(Intent.ACTION_SEARCH);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                    }else{
                        //If not found, do partial match.
                        quotes2 = databaseAccess.getSpeciesByName(query);
                        if (quotes2.get(0) != null) {
                            mySearchView.setQuery("", false);
                            mySearchView.clearFocus();
                            i = new Intent(MainActivity.this, SearchResults.class);
                            i.putStringArrayListExtra("quotes2", quotes2);

                            i.setAction(Intent.ACTION_SEARCH);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                    }
                    if(quotes2.get(0) == null){
                        mySearchView.setQuery("", false);
                        mySearchView.clearFocus();
                        Toast.makeText(getApplicationContext(), "No tree found with \""+ query + "\" in its name.", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Search.", Toast.LENGTH_SHORT).show();
                }
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













