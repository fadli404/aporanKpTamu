package com.apps.bukutamuv1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolderClass> {

    ArrayList<ModelClass> objectModelClassList;

    public RVAdapter(ArrayList<ModelClass> objectModelClassList) {
        this.objectModelClassList = objectModelClassList;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass holder, int position) {
        ModelClass objectModelClass = objectModelClassList.get(position);
        holder.nama_dataTV.setText(objectModelClass.getNama_input());
        holder.tanggal_dataTV.setText(objectModelClass.getTanggal_input());
        holder.keperluan_dataTV.setText(objectModelClass.getKeperluan_input());

        holder.foto_dataIV.setImageBitmap(objectModelClass.getFoto_input());

    }

    @Override
    public int getItemCount() {
        return objectModelClassList.size();
    }

    public static class RVViewHolderClass extends RecyclerView.ViewHolder{

        TextView nama_dataTV,tanggal_dataTV,keperluan_dataTV;
        ImageView foto_dataIV;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            nama_dataTV = itemView.findViewById(R.id.nama_data);
            tanggal_dataTV = itemView.findViewById(R.id.tanggal_data);
            keperluan_dataTV = itemView.findViewById(R.id.keperluan_data);
            foto_dataIV = itemView.findViewById(R.id.foto_data);


        }
    }
}
