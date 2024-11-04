package com.example.ddip_client;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ddip_client.network.SalaryApiService;
import com.example.ddip_client.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Map;

public class ImsiPayActivity extends AppCompatActivity {

    // TextViews 선언
    private TextView hourlyRateView;
    private TextView[] workHoursViews = new TextView[5];
    private TextView[] allowanceViews = new TextView[5];
    private TextView[] workAllowanceViews = new TextView[5];
    private TextView totalPaymentView;

    private double hourlyRate = 9860.0;  // 기본 최저 시급, 서버에서 받아옴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imsipay);

        // UI 요소 연결
        initializeViews();

        // Retrofit 설정 불필요, 바로 SalaryApiService 인스턴스 사용
        SalaryApiService apiService = RetrofitClient.getSalaryApiService();

        // 서버에서 시급 및 주별 근무 시간 가져오기
        fetchHourlyRate(apiService);
        fetchWeeklyHours(apiService, 11);  // 예시로 11월 데이터를 요청
    }

    // UI 요소 초기화
    private void initializeViews() {
        hourlyRateView = findViewById(R.id.hourlyRate);
        totalPaymentView = findViewById(R.id.totalPayment);

        // 주별 근무 시간, 주휴수당, 근무 수당 텍스트뷰 설정
        for (int i = 0; i < 5; i++) {
            workHoursViews[i] = findViewById(getResources().getIdentifier("workHoursWeek" + (i + 1), "id", getPackageName()));
            allowanceViews[i] = findViewById(getResources().getIdentifier("allowanceWeek" + (i + 1), "id", getPackageName()));
            workAllowanceViews[i] = findViewById(getResources().getIdentifier("workAllowanceWeek" + (i + 1), "id", getPackageName()));
        }
    }

    // 시급 가져오기
    private void fetchHourlyRate(SalaryApiService apiService) {
        apiService.getHourlyRate().enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hourlyRate = response.body();
                    hourlyRateView.setText(hourlyRate + " 원");
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {
                // 오류 처리
                hourlyRateView.setText("시급 로드 실패");
            }
        });
    }

    // 주별 근무 시간 가져오기
    private void fetchWeeklyHours(SalaryApiService apiService, int month) {
        apiService.getWeeklyHours(month).enqueue(new Callback<Map<Integer, Double>>() {
            @Override
            public void onResponse(Call<Map<Integer, Double>> call, Response<Map<Integer, Double>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<Integer, Double> weeklyHours = response.body();
                    Log.d("API_SUCCESS", "Weekly Hours Data: " + weeklyHours.toString());  // 데이터 확인용 로그
                    calculateAndDisplayWeeklyData(weeklyHours);
                } else {
                    Log.e("API_RESPONSE_ERROR", "Response failed with code: " + response.code());
                    for (TextView workHoursView : workHoursViews) {
                        workHoursView.setText("서버 응답 실패");
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<Integer, Double>> call, Throwable t) {
                Log.e("API_FAILURE", "Network request failed: " + t.getMessage());
                for (TextView workHoursView : workHoursViews) {
                    workHoursView.setText("네트워크 오류");
                }
            }
        });
    }



    // 주별 데이터 계산 및 UI에 표시
    private void calculateAndDisplayWeeklyData(Map<Integer, Double> weeklyHours) {
        double totalAllowance = 0.0;
        double totalWorkPay = 0.0;

        for (int week = 1; week <= 5; week++) {
            double hours = weeklyHours.getOrDefault(week, 0.0);

            // 근무 수당 계산 및 표시
            double workPay = hours * hourlyRate;
            totalWorkPay += workPay;
            workAllowanceViews[week - 1].setText(workPay + " 원");

            // 주휴수당 계산 및 표시
            if (hours >= 15) {
                double allowance = (hours * 8 / 40) * hourlyRate;
                totalAllowance += allowance;
                allowanceViews[week - 1].setText(allowance + " 원");
            } else {
                allowanceViews[week - 1].setText("해당안됨");
            }

            // 근무 시간 표시
            workHoursViews[week - 1].setText(hours + " 시간");
        }

        // 총 지급 금액 계산 및 표시
        double totalPayment = totalAllowance + totalWorkPay;
        totalPaymentView.setText(totalPayment + " 원");
    }
}
