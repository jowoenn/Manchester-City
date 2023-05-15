package com.example.esport;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.NewsHolder> {

    private ArrayList<NewsDetail> dataList;
    Context context;

    // Constructor

    public AdapterNews(ArrayList<NewsDetail> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNews.NewsHolder holder, int position) {
        NewsDetail news = dataList.get(position);
        holder.setDetails(news);
    }

    @Override
    public int getItemCount() { return dataList.size(); }

    class NewsHolder extends RecyclerView.ViewHolder{
        private TextView newsName, newsTime, newsContent;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            newsName = itemView.findViewById(R.id.newsName);
            newsTime = itemView.findViewById(R.id.newsTime);
            newsContent = itemView.findViewById(R.id.newsContent);
        }

        void setDetails(NewsDetail news){
            newsName.setText(news.getNewsName());
            newsTime.setText(news.getNewsTime());
            newsContent.setText(news.getNewsContent());
        }
    }

}
