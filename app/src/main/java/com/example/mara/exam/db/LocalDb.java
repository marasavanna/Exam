package com.example.mara.exam.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mara on 1/26/2018.
 */

public class LocalDb extends SQLiteOpenHelper {

    public static final String DB_NAME = "Projects.db";

    public static  final String TABLE_IDEAS = "ideas_table";
    private static final String COL1_IDEAS = "ID";
    private static final String COL2_IDEAS = "Name";
    private static final String COL3_IDEAS = "Budget";
    private static final String COL4_IDEAS = "Type";
    private static final String COL5_IDEAS = "Status";

    public LocalDb(Context context) {
        super(context, DB_NAME, null, 3);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_IDEAS + "("
                + COL1_IDEAS + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL2_IDEAS + " TEXT,"
                +COL3_IDEAS +" NUMBER,"
                +COL4_IDEAS +" TEXT,"
                +COL5_IDEAS +" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IDEAS);
        onCreate(db);
    }

    public boolean insertIdea( String name, int budget, String type, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2_IDEAS, name);
        cv.put(COL3_IDEAS, budget);
        cv.put(COL4_IDEAS, type);
        cv.put(COL5_IDEAS, status);


        long res = db.insert(TABLE_IDEAS, null,cv);
        if(res == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllIdeas(){
        String[] list = { COL2_IDEAS, COL3_IDEAS, COL4_IDEAS, COL5_IDEAS};
        return getReadableDatabase().query(TABLE_IDEAS, list, null, null, null, null, null);
    }

    public void deleteCars(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM " + TABLE_IDEAS); //delete all rows in a table
        //db.delete(TABLE_CARS, null, null);
        db.close();
    }
}
