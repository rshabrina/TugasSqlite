package com.example.ridhashabrina.tugassqlite;

/**
 * Created by ridhashabrina on 12/19/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "biodatadiri.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public static final String TABLE_NAME = "TB_Login";
    public static final String COL_1 = "Username";
    public static final String COL_2 = "Password";

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table biodata(nim text primary key, nama text null, tgl text null, jk text null, alamat text null, jurusan text null, angkatan text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO biodata (nim, nama, tgl, jk, alamat, jurusan, angkatan) VALUES " +
                "('113705129', 'M Irfan Dwi Ramdhani', '13 February 1994', 'Laki-laki','Cianjur','informatika','2013');";
        db.execSQL(sql);
        db.execSQL("create table " + TABLE_NAME + " (Username STRING PRIMARY KEY, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void SimpanData(String Username, String Password) {
        ContentValues values = new ContentValues();
        values.put(COL_1, Username);
        values.put(COL_2, Password);
        db.insert(TABLE_NAME, null, values);
    }
    public Cursor LihatData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }
    public void HapusData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }
}
