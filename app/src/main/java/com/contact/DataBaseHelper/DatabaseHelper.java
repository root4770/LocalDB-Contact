package com.contact.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "contact_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "contactId";
    public static final String COL_3 = "stagingId";
    public static final String COL_4 = "context";
    public static final String COL_5 = "phoneContactId";
    public static final String COL_6 = "status";
    public static final String COL_7 = "userID";


    //ID, contactId, stagingId,context,phoneContactId,status,userID

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,contactId TEXT,stagingId TEXT,context TEXT,phoneContactId TEXT,status TEXT,userID TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String contactId, String stagingId, String context, String phoneContactId, String status, String userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, contactId);
        contentValues.put(COL_3, stagingId);
        contentValues.put(COL_4, context);
        contentValues.put(COL_5, phoneContactId);
        contentValues.put(COL_6, status);
        contentValues.put(COL_7, userID);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getsearchdata(String value) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = null;

        //stagingId, context, status, userId

        res = db.rawQuery("SELECT " + "*" + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " =  '" + value + "' ", null);

        return res;
    }

    public boolean updateData(String id, String contactId, String stagingId, String context, String phoneContactId, String status, String userID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, contactId);
        contentValues.put(COL_3, stagingId);
        contentValues.put(COL_4, context);
        contentValues.put(COL_5, phoneContactId);
        contentValues.put(COL_6, status);
        contentValues.put(COL_7, userID);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}