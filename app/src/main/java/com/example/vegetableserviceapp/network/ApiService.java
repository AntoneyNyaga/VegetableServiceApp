package com.example.vegetableserviceapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("vegetables/add")
    Call<String> addVegetable(
            @Query("name") String name,
            @Query("price") double price
    );
}
