package com.belajar.sivosisk

import com.google.gson.annotations.SerializedName

data class KandidatResponse(
    @SerializedName("id_kandidat")
    val id_kandidat: String?,

    @SerializedName("nama_ketua")
    val nama_ketua: String?,

    @SerializedName("nama_wakil")
    val nama_wakil: String?,

    @SerializedName("visi")
    val visi: String?,

    @SerializedName("misi")
    val misi: String?,

    @SerializedName("gambar")
    val gambar: String?
)

