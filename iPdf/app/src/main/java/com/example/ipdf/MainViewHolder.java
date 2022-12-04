package com.example.ipdf;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MainViewHolder extends RecyclerView.ViewHolder {

    public TextView pdf_textName;
    public CardView pdf_cardView;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);

        pdf_textName=itemView.findViewById(R.id.pdf_textName);
        pdf_cardView=itemView.findViewById(R.id.pdf_cardView);


    }
}
