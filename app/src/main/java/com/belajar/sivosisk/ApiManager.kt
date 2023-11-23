package com.belajar.sivosisk

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val apiService: ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://sivosis.my.id/api/") // Update with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun loginWithQrScan(): Call<List<akun>> {
        return apiService.loginWithQrScan()
    }

    val dataList: Call<List<KandidatResponse>>
        get() = apiService.dataList
}