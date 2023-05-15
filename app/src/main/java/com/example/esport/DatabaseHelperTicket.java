package com.example.esport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperTicket extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Ticket.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "ticket_list";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "ticket_name";
    public static final String COLUMN_TIME = "ticket_time";
    public static final String COLUMN_LOCATION = "ticket_location";
    public static final String COLUMN_PRICE = "ticket_price";

    public DatabaseHelperTicket(@Nullable Context context) {
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

        // Database for ticket

        String addTicket =
                "INSERT INTO " + DatabaseHelperTicket.TABLE_NAME + " (" + DatabaseHelperTicket.COLUMN_NAME + " , " + DatabaseHelperTicket.COLUMN_TIME + " , " +
                DatabaseHelperTicket.COLUMN_LOCATION + " , " + DatabaseHelperTicket.COLUMN_PRICE + " )\n" +
                "VALUES " + " ('Manchester City vs West Ham United', '20:00 BST', 'Etihad Stadium', 'Rp. 1.050.000');";
        db.execSQL(addTicket);

        String addTicket2 =
                "INSERT INTO " + DatabaseHelperTicket.TABLE_NAME + " (" + DatabaseHelperTicket.COLUMN_NAME + " , " + DatabaseHelperTicket.COLUMN_TIME + " , " +
                        DatabaseHelperTicket.COLUMN_LOCATION + " , " + DatabaseHelperTicket.COLUMN_PRICE + " )\n" +
                        "VALUES " + " ('Manchester City vs Leeds United', '15:00 BST', 'Etihad Stadium', 'Rp. 1.050.000');";
        db.execSQL(addTicket2);

        String addTicket3 =
                "INSERT INTO " + DatabaseHelperTicket.TABLE_NAME + " (" + DatabaseHelperTicket.COLUMN_NAME + " , " + DatabaseHelperTicket.COLUMN_TIME + " , " +
                        DatabaseHelperTicket.COLUMN_LOCATION + " , " + DatabaseHelperTicket.COLUMN_PRICE + " )\n" +
                        "VALUES " + " ('Real Madrid vs Manchester City', '20:00 BST', 'Santiago Bernabeu', 'Rp. 1.050.000');";
        db.execSQL(addTicket3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
