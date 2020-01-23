package com.nishan.mysquilelite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class squliteDB extends SQLiteOpenHelper {
    private static int DB_VERSION=1;
    private static String DB_Name="mydb.db";
    private static String DB_Table="Info";
    private static String COLUMN1="id";
    private static String COLUMN2="Name";
    private static String COLUMN3="Cell";


    public squliteDB( Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+DB_Table+" (id INTEGER PRIMARY KEY,name TEXT,cell TEXT) ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_Table);
        onCreate(db);

    }

    public boolean insetData(String id,String name,String cell){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,cell);
        long check=db.insert(DB_Table,null,values);
        if(check==-1){
            return false;
        }
        else{
            return true;
        }

    }
    public Cursor ViewData(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor result=db.rawQuery(" SELECT * FROM "+DB_Table,null);
        return result;
    }
}
