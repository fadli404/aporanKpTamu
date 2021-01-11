package com.apps.bukutamuv1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    private EditText nama_inputET,tanggal_inputET,keperluan_inputET;
    private ImageView foto_inputIV;
    private Uri imageFilePath;

    private Bitmap imageToStore;
    MyDatabaseHelper objectDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            foto_inputIV = findViewById(R.id.foto_input);
            nama_inputET = findViewById(R.id.nama_input);
            tanggal_inputET = findViewById(R.id.tanggal_input);
            keperluan_inputET = findViewById(R.id.keperluan_input);

            objectDatabaseHelper=new MyDatabaseHelper(this);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void Pilihfoto(View objectView)
    {
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,0);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==0 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                imageFilePath=data.getData();
                imageToStore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                foto_inputIV.setImageBitmap(imageToStore);

            }
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void storeImage(View view)
    {
        try {
            if (!nama_inputET.getText().toString().isEmpty() &&
            !tanggal_inputET.getText().toString().isEmpty() &&
            !keperluan_inputET.getText().toString().isEmpty() &&
            foto_inputIV.getDrawable()!=null && imageToStore != null){
                objectDatabaseHelper.storeImage(new ModelClass(nama_inputET.getText().toString(),
                        tanggal_inputET.getText().toString(), keperluan_inputET.getText().toString(),
                        imageToStore));
            }else {
                Toast.makeText(this,"Pastikan semua telah terisi dengan benar", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToShowActivity(View view){
        startActivity(new Intent(this,Lihat_data.class));
    }
}