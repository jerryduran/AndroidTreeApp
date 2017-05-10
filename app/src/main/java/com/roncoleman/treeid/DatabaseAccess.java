package com.roncoleman.treeid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;

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
        //No need to protect against SQL injections, it's a local app.
        Cursor cursor = database.rawQuery("SELECT * FROM SpeciesTable WHERE (speciesName LIKE '%" + query +
                                            "%' OR commonName LIKE '%" + query + "%')", null);
        if(!(cursor.moveToFirst()) || cursor.getCount() == 0){
            list.add(null);
        }else{
            do{
                list.add(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getSpeciesByNameFull(String query) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SpeciesTable WHERE (speciesName = '" + query +
                                            "' OR commonName = '" + query + "')", null);
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
            do{
                list.add(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public ArrayList<String> getTreeOfMonth() {
        ArrayList<String> list = new ArrayList<>();;

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH); //0 indexed.

        Cursor cursor = database.rawQuery("SELECT * FROM MonthTable WHERE monthID = " + (currentMonth + 1), null);
        if(!(cursor.moveToFirst()) || cursor.getCount() == 0){
            list.add(null);
        }else {
            list.add(cursor.getString(1));//TreeID
            list.add(cursor.getString(2));//Description
        }

        cursor.close();
        return list;

    }
}