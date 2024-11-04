package com.example.ddip_client.network;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

// Retrofit API 인터페이스 정의
public interface SalaryApiService {
    @GET("api/salary/hourly-rate")
    Call<Double> getHourlyRate();

    @GET("api/salary/work-hours/{month}")
    Call<Map<Integer, Double>> getWeeklyHours(@Path("month") int month);
}
