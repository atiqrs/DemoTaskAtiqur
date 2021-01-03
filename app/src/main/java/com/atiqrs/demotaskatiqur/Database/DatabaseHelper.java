package com.atiqrs.demotaskatiqur.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.atiqrs.demotaskatiqur.Database.model.Information;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1; // Database Version
    private static final String DATABASE_NAME = "info_db"; // Database Name
    Context context;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        try {
            db.execSQL(Information.CREATE_TABLE);
        } catch (Exception e){
            Toast.makeText(context, "Error on Creating DB!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Information.TABLE_NAME); // Drop older table
            onCreate(db); // Create tables again
        } catch (Exception e){
            Toast.makeText(context, "Error on Updating DB!", Toast.LENGTH_SHORT).show();
        }
    }
    //Create note
    public long insertInformation(String name,String mobile,String email) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` will be inserted automatically.
        values.put(Information.COLUMN_NAME, name);
        values.put(Information.COLUMN_MOBILE, mobile);
        values.put(Information.COLUMN_EMAIL, email);
        // insert row
        long id = db.insert(Information.TABLE_NAME, null, values);
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }

    public Information getInfo(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Information.TABLE_NAME,
                new String[]{Information.COLUMN_ID,
                        Information.COLUMN_NAME,
                        Information.COLUMN_MOBILE,
                        Information.COLUMN_EMAIL},
                Information.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Information info = new Information(
                cursor.getInt(cursor.getColumnIndex(Information.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Information.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Information.COLUMN_MOBILE)),
                cursor.getString(cursor.getColumnIndex(Information.COLUMN_EMAIL)));

        // close the db connection
        cursor.close();

        return info;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Information.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

}