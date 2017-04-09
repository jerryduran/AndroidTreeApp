package com.example.jerryduran.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getTree(int query) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM TreeTable", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getInt(0) == query){
                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));
                list.add(cursor.getString(3));
                list.add(cursor.getString(4));
                cursor.close();
                return list;
            }
            cursor.moveToNext();
        }
        list.add(null); //add null to list to indicate that query was not found.
        cursor.close();
        return list;
    }

    public List<String> getSpecies(int query) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SpeciesTable", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getInt(0) == query){
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
                cursor.close();
                return list;
            }
            cursor.moveToNext();
        }
        list.add(null); //add null to list to indicate that query was not found.
        cursor.close();
        return list;
    }

}