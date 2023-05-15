package com.example.esport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "User.db";
    private static final int DATABASE_VERSION = 1;

    // Content
//    private static final String TABLE_NAME = "user_list";
//    private static final String COLUMN_ID = "_id";
//    private static final String COLUMN_NAME = "user_name";
//    private static final String COLUMN_PASSWORD = "user_password";

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_list";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "user_name";
        public static final String COLUMN_PASSWORD = "user_password";
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + UserEntry.TABLE_NAME +
                        " (" + UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        UserEntry.COLUMN_NAME + " TEXT, " +
                        UserEntry.COLUMN_PASSWORD + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        onCreate(db);
    }

    void addUser(String userName, String userPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UserEntry.COLUMN_NAME, userName);
        contentValues.put(UserEntry.COLUMN_PASSWORD, userPassword);

        // Insert to Database
        long result = db.insert(UserEntry.TABLE_NAME, null, contentValues);
        if(result == -1){
            Toast.makeText(context, "Failed to insert!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully insert data!", Toast.LENGTH_SHORT).show();
        }
    }

}















