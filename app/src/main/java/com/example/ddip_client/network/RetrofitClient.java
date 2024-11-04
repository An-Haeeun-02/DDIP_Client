package com.example.ddip_client.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    // 서버의 Base URL 설정 (Spring Boot의 주소로 변경해야 합니다. AWS 사용지 퍼블릭ip 설정하여 변경)
    private static final String BASE_URL = "http://192.168.0.5:8080/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    // SalaryApiService 인스턴스를 반환하는 메서드
    public static SalaryApiService getSalaryApiService() {
        return getClient().create(SalaryApiService.class);
    }
}
