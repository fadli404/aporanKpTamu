package com.apps.bukutamuv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.widget.Toast;
import androidx.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    Context context;
    private static String DATABASE_NAME = "bukutamu.db";
    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "create table DetailBukuTamu (nama_input TEXT" +
            ",tanggal_input TEXT" + ",keperluan_input TEXT" + ",foto_input BLOB)";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(createTableQuery);
            Toast.makeText(context,"Table created successfully inside our database", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void storeImage(ModelClass objectModelClass){
        try {
            SQLiteDatabase objectSqliteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = objectModelClass.getFoto_input();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);
            imageInBytes=objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues=new ContentValues();

            objectContentValues.put("nama_input",objectModelClass.getNama_input());
            objectContentValues.put("tanggal_input",objectModelClass.getTanggal_input());
            objectContentValues.put("keperluan_input",objectModelClass.getKeperluan_input());
            objectContentValues.put("foto_input",imageInBytes);

            long checkIfQueryRuns = objectSqliteDatabase.insert("DetailBukuTamu",null,objectContentValues);
            if (checkIfQueryRuns!=0){
                Toast.makeText(context,"Data added into our table", Toast.LENGTH_SHORT).show();
                objectSqliteDatabase.close();
            }else {
                Toast.makeText(context,"Fails to add data", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<ModelClass> getAllImagesData()
    {
        try {
            SQLiteDatabase objectSQLiteDatabase = this.getReadableDatabase();
            ArrayList<ModelClass> objectModelClassList = new ArrayList<>();

            Cursor objectCursor=objectSQLiteDatabase.rawQuery("select * from DetailBukuTamu",null);
            if (objectCursor.getCount()!=0){
                while(objectCursor.moveToNext()){
                    String namaData = objectCursor.getString(0);
                    String tanggalData = objectCursor.getString(1);
                    String keperluanData = objectCursor.getString(2);
                    byte [] fotoData = objectCursor.getBlob(3);

                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(fotoData,0, fotoData.length);
                    objectModelClassList.add(new ModelClass(namaData,tanggalData,keperluanData,objectBitmap));
                }
                return objectModelClassList;
            }else{
                Toast.makeText(context,"Tidak ada data", Toast.LENGTH_SHORT).show();
                return null;
            }
        }catch (Exception e)
        {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
