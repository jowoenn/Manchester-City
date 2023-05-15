package com.example.esport;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelperTransaction extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Transaction.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "transaction_list";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "transaction_name";
    public static final String COLUMN_TIME = "transaction_time";
    public static final String COLUMN_LOCATION = "transaction_location";
    public static final String COLUMN_PRICE = "transaction_price";

    public DatabaseHelperTransaction(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create table

        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_TIME + " TEXT, " +
                        COLUMN_LOCATION + " TEXT, " +
                        COLUMN_PRICE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<UsersDetail> getAllData() {
        ArrayList<UsersDetail> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String name = (cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                String time = (cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME)));
                String location = (cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION)));
                String price = (cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));

                UsersDetail usersDetail = new UsersDetail(name, time, location, price);
                dataList.add(usersDetail);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataList;
    }

}
