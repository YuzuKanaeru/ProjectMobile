package com.projects3.projectsivosis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiService {

    @POST("akun.php")
    Call<List<akun>> loginWithQrScan();

}
