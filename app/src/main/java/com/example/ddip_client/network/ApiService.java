package com.example.ddip_client.network;

import com.example.ddip_client.models.ddip_db;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("users/register")
    Call<ddip_db> register1(@Body ddip_db ddip_db);

    @POST("users/register")
    Call<ddip_db> register2(@Body String userid, String userpwd, String username, String email, boolean admin);

    @POST("users/login")
    Call<ddip_db> login(@Query("userid") String userid, @Query("userpwd") String userpwd);

    @GET("/api/check-username")
    Call<Boolean> checkUserid(@Query("userid") String userid);
}
