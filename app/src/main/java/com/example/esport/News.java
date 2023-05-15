package com.example.esport;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class News extends AppCompatActivity {

    RecyclerView recyclerView;
    private AdapterNews adapterNews;
    private ArrayList<NewsDetail> newsDetailArrayList;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        // Recycler View
        recyclerView = findViewById(R.id.recyclerViewNews);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsDetailArrayList = new ArrayList<>();
        adapterNews = new AdapterNews(newsDetailArrayList, this);
        recyclerView.setAdapter(adapterNews);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        addData();

        // Navigation
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if(item.getItemId() == R.id.ticket){
                    Intent intent = new Intent(News.this, Ticket.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.news){
                    Intent intent = new Intent(News.this, News.class);
                    startActivity(intent);
                    return true;
                }
                else if(item.getItemId() == R.id.user){
                    Intent intent = new Intent(News.this, User.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void addData(){
        NewsDetail newsDetail = new NewsDetail("Haaland Breaks Record!","18:00 BST","Haaland scores again at the liverpool match to make it 4-1 at fulltime. He scored the 34th goal of the season to break the record of most EPL goals in a 38 match season.");
        newsDetailArrayList.add(newsDetail);

        newsDetail = new NewsDetail("Kevin de Bruyne 100th Assists","04:00 BST","Kevin de Bruyne with the masterclass of passes to make his 100th career goals in Manchester City. He gave a cross to Erling Haaland to beat Southhampton.");
        newsDetailArrayList.add(newsDetail);

        newsDetail = new NewsDetail("City's Magazine","-","City are on the cusp of an historic season as we enter the final weeks of the campaign. Enjoy the May issue!");
        newsDetailArrayList.add(newsDetail);

        newsDetail = new NewsDetail("FULHAM 1-2 CITY: EXTENDED HIGHLIGHTS","01:00 BST","Haaland scored his 50th goal of an incredible season to put us ahead in the third minute of the game.");
        newsDetailArrayList.add(newsDetail);

        newsDetail = new NewsDetail("We Must Win Our Games in Hand - Guardiola","14:00 BST","“The next two games will define a lot,” he added. “Two games at home, step-by-step, game-by-game.");
        newsDetailArrayList.add(newsDetail);

        adapterNews.notifyDataSetChanged();
    }
}
