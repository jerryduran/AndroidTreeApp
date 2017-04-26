package com.example.jerryduran.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }



    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }


    public void open() {
        this.database = openHelper.getWritableDatabase();
    }


    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public ArrayList<String> getTree(int query) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM TreeTable WHERE treeID = " + query, null);
        if(!(cursor.moveToFirst()) || cursor.getCount() == 0){
            list.add(null);
        }else{
            list.add(cursor.getString(0));
            list.add(cursor.getString(1));
            list.add(cursor.getString(2));
            list.add(cursor.getString(3));
            list.add(cursor.getString(4));
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getSpecies(int query) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SpeciesTable WHERE speciesID = " + query, null);
        if(!(cursor.moveToFirst()) || cursor.getCount() == 0){
            list.add(null);
        }else{
            list.add(cursor.getString(0));
            list.add(cursor.getString(1));
            list.add(cursor.getString(2));
            list.add(cursor.getString(3));
            list.add(cursor.getString(4));
            list.add(cursor.getString(5));
            list.add(cursor.getString(6));
            list.add(cursor.getString(7));
            list.add(cursor.getString(8));
            list.add(cursor.getString(9));
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getSpeciesByName(String query) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SpeciesTable WHERE (speciesName LIKE '%" + query +
                                            "%' OR commonName LIKE '%" + query + "%')", null);
        if(!(cursor.moveToFirst()) || cursor.getCount() == 0){
            list.add(null);
        }else{
            list.add(cursor.getString(2));
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getSpeciesByNameFull(String query) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SpeciesTable WHERE commonName = '" + query + "'", null);
        if(!(cursor.moveToFirst()) || cursor.getCount() == 0){
            list.add(null);
        }else{
            list.add(cursor.getString(0));
            list.add(cursor.getString(1));
            list.add(cursor.getString(2));
            list.add(cursor.getString(3));
            list.add(cursor.getString(4));
            list.add(cursor.getString(5));
            list.add(cursor.getString(6));
            list.add(cursor.getString(7));
            list.add(cursor.getString(8));
            list.add(cursor.getString(9));
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getSpeciesList() {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SpeciesTable" , null);
        if(!(cursor.moveToFirst()) || cursor.getCount() == 0){
            list.add(null);
        }else{
            list.add(cursor.getString(2));
        }
        cursor.close();
        return list;
    }
}