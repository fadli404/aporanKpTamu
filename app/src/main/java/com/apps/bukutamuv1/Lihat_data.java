package com.apps.bukutamuv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

public class Lihat_data extends AppCompatActivity {

    private MyDatabaseHelper objectMyDatabaseHelper;
    private RecyclerView objectRecyclerView;

    private RVAdapter objectRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        try {
            objectRecyclerView = findViewById(R.id.rv_data);
            objectMyDatabaseHelper = new MyDatabaseHelper(this);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void getData (View view){
        try {
            objectRVAdapter = new RVAdapter(objectMyDatabaseHelper.getAllImagesData());
            objectRecyclerView.setHasFixedSize(true);

            objectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            objectRecyclerView.setAdapter(objectRVAdapter);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public void isiBukuTamu(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}