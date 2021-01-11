package com.apps.bukutamuv1;

import android.graphics.Bitmap;

public class ModelClass {
    private String nama_input,tanggal_input,keperluan_input;
    private Bitmap foto_input;

    public ModelClass(String nama_input, String tanggal_input, String keperluan_input, Bitmap foto_input) {
        this.nama_input = nama_input;
        this.tanggal_input = tanggal_input;
        this.keperluan_input = keperluan_input;
        this.foto_input = foto_input;
    }

    public String getNama_input() {
        return nama_input;
    }

    public void setNama_input(String nama_input) {
        this.nama_input = nama_input;
    }

    public String getTanggal_input() {
        return tanggal_input;
    }

    public void setTanggal_input(String tanggal_input) {
        this.tanggal_input = tanggal_input;
    }

    public String getKeperluan_input() {
        return keperluan_input;
    }

    public void setKeperluan_input(String keperluan_input) {
        this.keperluan_input = keperluan_input;
    }

    public Bitmap getFoto_input() {
        return foto_input;
    }

    public void setFoto_input(Bitmap foto_input) {
        this.foto_input = foto_input;
    }
}
