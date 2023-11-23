package com.belajar.sivosisk

class Kandidat(val id: Int, val nama: String, visi: String, misi: String, gambar: Int) {
    val visi: String
    val misi: String
    val gambar: Int

    // Add other candidate properties as needed
    init {
        this.gambar = gambar
        this.visi = visi
        this.misi = misi
        // Initialize other properties
    }
}
