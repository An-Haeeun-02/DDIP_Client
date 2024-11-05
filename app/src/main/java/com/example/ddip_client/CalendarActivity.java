package com.example.ddip_client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Button addWorkButton;
    private long selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        // XML 요소와 연결
        calendarView = findViewById(R.id.calendar_view);
        addWorkButton = findViewById(R.id.add_work_button);

        // 날짜 선택 리스너 설정
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // 선택된 날짜를 저장
                selectedDate = calendarView.getDate();

                // 선택된 날짜와 관련된 메시지를 출력하거나 설정
                String selectedDateMessage = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일";
                Toast.makeText(CalendarActivity.this, selectedDateMessage + " 선택됨", Toast.LENGTH_SHORT).show();

                // 버튼을 보이게 설정
                addWorkButton.setVisibility(View.VISIBLE);
            }
        });

        // 근무 추가 버튼 클릭 리스너 설정
        addWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 근무 추가 액티비티로 이동
                Intent intent = new Intent(CalendarActivity.this, AddWorkActivity.class);

                // 선택된 날짜를 전달 (필요시)
                intent.putExtra("selectedDate", selectedDate);

                startActivity(intent);
            }
        });
    }
}
