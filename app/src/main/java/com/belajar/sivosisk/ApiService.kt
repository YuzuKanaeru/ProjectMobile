package com.belajar.sivosisk

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("akun.php")
    fun loginWithQrScan(): Call<List<akun>>

    @GET("datakandidat.php")
    fun getDataKandidat(): Call<List<KandidatResponse>>

    @POST("voting.php")
    @FormUrlEncoded
    fun sendVote(
        @Field("nis_nip") nisNip: String,
        @Field("id_kandidat") idKandidat: String
    ): Call<ResponseBody>
}
