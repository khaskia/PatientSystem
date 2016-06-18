package com.example.moham.patientsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Muhammed on 5/17/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "patient.db";
    public static final String TABLE_NAME ="patient_table";
    public static final String COl_1 ="NAME";
    public static final String COl_2 ="Phone";
    public static final String COl_3 ="Email";
    public static final String COl_4 ="Date";
    public static final String COl_5 ="Time";
    public static final String COl_6 ="Disease";
    public static final String COl_7 ="Medication";
    public static final String COl_8 ="Cost";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(NAME TEXT PRIMARY KEY ,Phone TEXT,Email TEXT ,Date TEXT,Time TEXT,Disease TEXT,Medication TEXT,Cost TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

// **********************  Insert The Data   **************************************//

    public boolean insertData(String NAME,String Phone,String Email,String Date,String Time,String Disease,String Medication,String Cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl_1,NAME);
        contentValues.put(COl_2,Phone);
        contentValues.put(COl_3, Email);
        contentValues.put(COl_4, Date);
        contentValues.put(COl_5, Time);
        contentValues.put(COl_6, Disease);
        contentValues.put(COl_7, Medication);
        contentValues.put(COl_8, Cost);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        }else {
            return  true;
        }
    }

// **********************  Get ALL DATA   **************************************//
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return  res;
    }
// **********************  Get Data By User Name to view his details   **************************************//

    public Cursor getbyUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where NAME ="+"\""+username+"\"", null);
        return  res;
    }

// **********************  ((Like)) user name used in Search by name   **************************************//

    public Cursor getbyLikeUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where NAME Like "+"\"%"+username+"%\"", null);
        return  res;
    }

// **********************  ((Like)) user name used in Search by Disease   **************************************//

    public Cursor getbyLikeDisease(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where Disease Like "+"\"%"+username+"%\"", null);
        return  res;
    }

// **********************  ((Like)) user name used in Search by Disease   **************************************//

    public Cursor getbyLikeMedication(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where Medication Like "+"\"%"+username+"%\"", null);
        return  res;
    }

// **********************  ((DATE)) using between x and y   **************************************//


    public Cursor getbyfromtodate(String from,String to){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME+" where Date Between "+"\""+from+"\""+" and " +"\""+to+"\"" , null);
        return  res;
    }

// **********************  (( Update data ))  **************************************//


    public boolean update(String NAME,String Phone,String Email,String Date,String Time,String Disease,String Medication,String Cost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl_1,NAME);
        contentValues.put(COl_2,Phone);
        contentValues.put(COl_3, Email);
        contentValues.put(COl_4, Date);
        contentValues.put(COl_5, Time);
        contentValues.put(COl_6, Disease);
        contentValues.put(COl_7, Medication);
        contentValues.put(COl_8, Cost);
        db.update(TABLE_NAME,contentValues,"NAME = ?",new String[]{NAME});
        return true;
    }


// **********************  (( Delete data ))  **************************************//
    public Integer delete(String NAME){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"NAME=?",new String[]{NAME});
    }




}
