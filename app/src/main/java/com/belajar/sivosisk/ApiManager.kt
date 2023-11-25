package com.belajar.sivosisk

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val apiService: ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://sivosis.my.id/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun loginWithQrScan(): Call<List<akun>> {
        return apiService.loginWithQrScan()
    }
    fun getDataKandidat(): Call<List<KandidatResponse>> {
        return apiService.getDataKandidat()
}
    fun sendVote(nisNip: String, idKandidat: String): Call<ResponseBody> {
        return apiService.sendVote(nisNip, idKandidat)
    }
}