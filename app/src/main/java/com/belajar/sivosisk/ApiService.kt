package com.belajar.sivosisk

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("akun.php")
    fun loginWithQrScan(): Call<List<akun>>

    @get:GET("datakandidat.php")
    val dataList: Call<List<KandidatResponse>>
}