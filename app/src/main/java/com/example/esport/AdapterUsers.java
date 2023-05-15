package com.example.esport;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.UsersHolder> {

    // 1. Adapter

    private ArrayList<UsersDetail> dataList;
    Context context;

    public AdapterUsers(ArrayList<UsersDetail> dataList, Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card, parent, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUsers.UsersHolder holder, int position) {
        UsersDetail user = dataList.get(position);
//        Log.d("AdapterUsers", "onBindViewHolder: " + user.getTransactionName() + ", " + user.getTransactionTime() + ", " + user.getTransactionLocation() + ", " + user.getTransactionPrice());
        holder.setDetails(user);
    }

    @Override
    public int getItemCount() { return dataList.size(); }

    // 2. Holder

    class UsersHolder extends RecyclerView.ViewHolder{
        private TextView transactionName, transactionTime, transactionLocation, transactionPrice;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);
            transactionName = itemView.findViewById(R.id.transactionName);
            transactionTime = itemView.findViewById(R.id.transactionTime);
            transactionLocation = itemView.findViewById(R.id.transactionLocation);
            transactionPrice = itemView.findViewById(R.id.transactionPrice);
        }

        void setDetails(UsersDetail user){
            if(transactionName != null){
                transactionName.setText(user.getTransactionName());
                transactionTime.setText(user.getTransactionTime());
                transactionLocation.setText(user.getTransactionLocation());
                transactionPrice.setText(user.getTransactionPrice());
            }
        }

    }

    public void updateData(ArrayList<UsersDetail> newDataList){

        // Ga kepake

        dataList.clear();
        dataList.addAll(newDataList);
        notifyDataSetChanged();
    }

}