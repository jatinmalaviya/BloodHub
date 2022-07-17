package com.example.bloodhub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapterView extends RecyclerView.Adapter<adapterView.ViewHolder> {
    private List<modelClass> modelClasses;

    public adapterView(List<modelClass> modelClasses) {
        this.modelClasses = modelClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        adapterView.ViewHolder viewHolder = new adapterView.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.age.setText(modelClasses.get(position).getAge());
        holder.name.setText(modelClasses.get(position).getName());
        holder.email.setText(modelClasses.get(position).getEmail());
        holder.number.setText(modelClasses.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView age;
        public TextView name;
        public TextView email;
        public TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            this.age = (TextView) itemView.findViewById(R.id.age);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.email = (TextView) itemView.findViewById(R.id.email);
            this.number = (TextView) itemView.findViewById(R.id.number);
        }
    }
}