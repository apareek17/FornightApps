package com.example.evanstrittmatter16.fornightapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FornightService {

    @Headers("TRN-Api-Key: 4c503120-d709-4743-b15e-4d6c00a167dd")
    @GET("profile/{platform}/{epic-nickname}")
    public Call<ItemResponse> searchByPlatfrom(@Path("platform") String platform, @Path("epic-nickname") String epicNickname);


    @Headers("TRN-Api-Key: 4c503120-d709-4743-b15e-4d6c00a167dd")
    @GET("store")
    public Call<List<Store>> getStore();
}

