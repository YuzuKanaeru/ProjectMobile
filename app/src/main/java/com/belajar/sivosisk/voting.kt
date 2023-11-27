package com.belajar.sivosisk

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class voting(
    @SerializedName("nis_nip")
    @Expose
    val nis_nip: String? = null,

    @SerializedName("id_kandidat")
    @Expose
    val id_kandidat: String? = null
) {
    fun toMap(): Map<String, String?> {
        val map = mutableMapOf<String, String?>()
        map["nis_nip"] = nis_nip
        map["id_kandidat"] = id_kandidat
        return map
    }
}
