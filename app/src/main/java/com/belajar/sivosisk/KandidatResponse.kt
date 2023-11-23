package com.belajar.sivosisk

import com.google.gson.annotations.SerializedName

data class KandidatResponse(
    @SerializedName("id_kandidat")
    val id_kandidat: Int,

    @SerializedName("nama_ketua")
    val nama_ketua: String?,

    @SerializedName("nama_wakil")
    val nama_wakil: String?,

    @SerializedName("gambar")
    val gambar: String?
)
