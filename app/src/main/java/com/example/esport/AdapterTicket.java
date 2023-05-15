package com.example.esport;

import static com.example.esport.DatabaseHelperTransaction.COLUMN_LOCATION;
import static com.example.esport.DatabaseHelperTransaction.COLUMN_NAME;
import static com.example.esport.DatabaseHelperTransaction.COLUMN_PRICE;
import static com.example.esport.DatabaseHelperTransaction.COLUMN_TIME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTicket extends RecyclerView.Adapter<AdapterTicket.TicketHolder> {

    // 1. Adapter

    private ArrayList<UsersDetail> usersDetailArrayList;
    private AdapterUsers adapterUsers;
    private ArrayList<TicketsDetail> dataList;
    Context context;

    // Constructor

    public AdapterTicket(ArrayList<TicketsDetail> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        this.usersDetailArrayList = new ArrayList<>();
    }


    @NonNull
    @Override
    public TicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_card, parent, false);
        return new TicketHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTicket.TicketHolder holder, int position) {
        TicketsDetail ticket = dataList.get(position);
        holder.setDetails(ticket);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // 2. Holder

    class TicketHolder extends RecyclerView.ViewHolder{

        private TextView ticketName, ticketTime, ticketLocation, ticketPrice;
        private Button buttonBuy;

        public TicketHolder(@NonNull View itemView) {
            super(itemView);
            buttonBuy = itemView.findViewById(R.id.buttonBuy);
            ticketName = itemView.findViewById(R.id.ticketName);
            ticketTime = itemView.findViewById(R.id.ticketTime);
            ticketLocation = itemView.findViewById(R.id.ticketLocation);
            ticketPrice = itemView.findViewById(R.id.ticketPrice);
        }

        void setDetails(TicketsDetail ticket){

            // Percobaan ke 1203023248x
            ticketName.setText(ticket.getTicketName());
            ticketTime.setText(ticket.getTicketTime());
            ticketLocation.setText(ticket.getTicketLocation());
            ticketPrice.setText(ticket.getTicketPrice());

            buttonBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHelperTicket dbTicket = new DatabaseHelperTicket(context);
                    SQLiteDatabase dbRead = dbTicket.getReadableDatabase();

                    // Reading
                    Cursor cursor = dbRead.query(
                            DatabaseHelperTicket.TABLE_NAME,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null);

                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            String ticketName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperTicket.COLUMN_NAME));
                            String ticketTime = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperTicket.COLUMN_TIME));
                            String ticketLocation = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperTicket.COLUMN_LOCATION));
                            String ticketPrice = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperTicket.COLUMN_PRICE));

                            if (ticketName.equals(ticket.getTicketName()) && ticketTime.equals(ticket.getTicketTime())
                                    && ticketLocation.equals(ticket.getTicketLocation()) && ticketPrice.equals(ticket.getTicketPrice())) {
                                DatabaseHelperTransaction dbTransaction = new DatabaseHelperTransaction(context);
                                SQLiteDatabase dbWrite = dbTransaction.getWritableDatabase();
                                ContentValues values = new ContentValues();

                                values.put(DatabaseHelperTransaction.COLUMN_NAME, ticketName);
                                values.put(DatabaseHelperTransaction.COLUMN_TIME, ticketTime);
                                values.put(DatabaseHelperTransaction.COLUMN_LOCATION, ticketLocation);
                                values.put(DatabaseHelperTransaction.COLUMN_PRICE, ticketPrice);
                                dbWrite.insert(DatabaseHelperTransaction.TABLE_NAME, null, values);
                                break;
                            }

                        } while (cursor.moveToNext());
                        cursor.close();
                    }
                }
            });


        }
    }
}












