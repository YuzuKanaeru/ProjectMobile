package com.belajar.sivosisk

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("akun.php")
    fun loginWithQrScan(): Call<List<akun>>

    @GET("datakandidat.php")
    fun getDataKandidat(): Call<List<KandidatResponse>>

    @POST("voting.php")
    fun sendVote(@Body Voting: voting): Call<ResponseBody>
}
