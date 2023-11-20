package com.projects3.projectsivosis;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

}

