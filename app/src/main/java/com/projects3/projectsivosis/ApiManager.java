package com.projects3.projectsivosis;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private ApiService apiService;

    public ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sivosis.my.id/api/") // Update with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public Call<List<akun>> loginWithQrScan() {
        return apiService.loginWithQrScan();
    }
}
