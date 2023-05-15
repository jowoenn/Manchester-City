package com.example.esport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Ticket extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    RecyclerView recyclerView;
    private AdapterUsers adapterUsers;
    private AdapterTicket adapterTicket;
    private ArrayList<TicketsDetail> ticketsDetailArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket);

        // Database
        SQLiteDatabase db = new DatabaseHelperTicket(this).getReadableDatabase();

        // Recycler View
        recyclerView = findViewById(R.id.recyclerViewTicket);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ticketsDetailArrayList = new ArrayList<>();
        adapterTicket = new AdapterTicket(ticketsDetailArrayList, this);
        recyclerView.setAdapter(adapterTicket);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        addData();

        // Navigation
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if(item.getItemId() == R.id.ticket){
                    Intent intent = new Intent(Ticket.this, Ticket.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.news){
                    Intent intent = new Intent(Ticket.this, News.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.user){
                    Intent intent = new Intent(Ticket.this, User.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }

    private void addData(){
        // Move from database to array

        SQLiteDatabase db = new DatabaseHelperTicket(this).getReadableDatabase();

        String[] projection = {
                DatabaseHelperTicket.COLUMN_NAME,
                DatabaseHelperTicket.COLUMN_TIME,
                DatabaseHelperTicket.COLUMN_LOCATION,
                DatabaseHelperTicket.COLUMN_PRICE
        };

        Cursor cursor = db.query(
                DatabaseHelperTicket.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                String ticketName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperTicket.COLUMN_NAME));
                String ticketTime = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperTicket.COLUMN_TIME));
                String ticketLocation = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperTicket.COLUMN_LOCATION));
                String ticketPrice = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperTicket.COLUMN_PRICE));
                TicketsDetail ticketsDetail = new TicketsDetail(ticketName, ticketTime, ticketLocation, ticketPrice);
                ticketsDetailArrayList.add(ticketsDetail);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        adapterTicket.notifyDataSetChanged();
    }
}