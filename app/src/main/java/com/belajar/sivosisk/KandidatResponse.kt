package com.belajar.sivosisk

import com.google.gson.annotations.SerializedName

class KandidatResponse {
    @SerializedName("id_kandidat")
    private val id_kandidat = 0

    @SerializedName("nama_ketua")
    private val nama_ketua: String? = null

    @SerializedName("nama_wakil")
    private val nama_wakil: String? = null

    @SerializedName("gambar")
    private val gambar: String? = null
}
