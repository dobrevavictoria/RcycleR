package com.example.pc.rcycler;

import models.ServerRequest;
import models.ServerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("rcycler-login-register/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}