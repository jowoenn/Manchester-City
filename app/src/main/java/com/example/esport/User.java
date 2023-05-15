package com.example.esport;

import static com.example.esport.DatabaseHelperTransaction.COLUMN_LOCATION;
import static com.example.esport.DatabaseHelperTransaction.COLUMN_NAME;
import static com.example.esport.DatabaseHelperTransaction.COLUMN_PRICE;
import static com.example.esport.DatabaseHelperTransaction.COLUMN_TIME;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class User extends AppCompatActivity {

   BottomNavigationView bottomNavigation;
   TextView textName;
   RecyclerView recyclerView;
   private ArrayList<UsersDetail> usersDetailArrayList;
   private AdapterUsers adapterUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        // Database
        SQLiteDatabase db = new DatabaseHelperTransaction(this).getReadableDatabase();
        SQLiteDatabase db2 = new DatabaseHelper(this).getReadableDatabase();
        // Profile
        textName = findViewById(R.id.textUsername);

        String[] p = {
                DatabaseHelper.UserEntry.COLUMN_NAME
        };
        Cursor cursor = db2.query(
          DatabaseHelper.UserEntry.TABLE_NAME,
                p,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor.moveToFirst()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.UserEntry.COLUMN_NAME));
            textName.setText(username);
        }
        // Recycler View
        recyclerView = findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersDetailArrayList = new ArrayList<>();
        adapterUsers = new AdapterUsers(usersDetailArrayList, this);
        recyclerView.setAdapter(adapterUsers);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        // Database -> Array
        addData();
        // Navigation
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if(item.getItemId() == R.id.ticket){
                    Intent intent = new Intent(User.this, Ticket.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.news){
                    Intent intent = new Intent(User.this, News.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.user){
                    Intent intent = new Intent(User.this, User.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
    private void addData(){
        DatabaseHelperTransaction dbHelper = new DatabaseHelperTransaction(this);
        ArrayList<UsersDetail> dataList = dbHelper.getAllData();
        usersDetailArrayList.addAll(dataList);
        adapterUsers.notifyDataSetChanged();
    }
}