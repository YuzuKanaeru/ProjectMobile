package com.belajar.sivosisk

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("akun.php")
    fun loginWithQrScan(): Call<List<akun>>

    @GET("datakandidat.php")
    fun getDataKandidat(): Call<List<KandidatResponse>>
}