package com.projects3.projectsivosis;

import android.widget.ImageView;

public class Kandidat {
    private int id;
    private String nama,visi,misi;
    private int gambar;
    // Add other candidate properties as needed

    public Kandidat(int id, String nama, String visi, String misi, int gambar) {
        this.id = id;
        this.nama = nama;
        this.gambar = gambar;
        this.visi = visi;
        this.misi = misi;
        // Initialize other properties
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getGambar(){
        return gambar;
    }

    public String getVisi() {
        return visi;
    }

    public String getMisi() {
        return misi;
    }
}
