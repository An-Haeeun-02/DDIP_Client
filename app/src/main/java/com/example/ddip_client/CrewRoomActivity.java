package com.example.ddip_client;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CrewRoomActivity extends AppCompatActivity {

    private RadioButton radioCalendar;
    private RadioButton radioExchange;
    private RadioButton radioWorkerList;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crewroom);

        // 상단바 설정 (제목 등)
        ImageButton homeButton = findViewById(R.id.home_button);
        ImageButton calendarButton = findViewById(R.id.calendar_button);
        ImageButton crewRoomButton = findViewById(R.id.crew_room_button);
        ImageButton myPageButton = findViewById(R.id.mypage_button);

        homeButton.setOnClickListener(v -> Toast.makeText(CrewRoomActivity.this, "홈 버튼 클릭됨", Toast.LENGTH_SHORT).show());
        calendarButton.setOnClickListener(v -> {
            Intent intent = new Intent(CrewRoomActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
        crewRoomButton.setOnClickListener(v -> Toast.makeText(CrewRoomActivity.this, "크루룸 버튼 클릭됨", Toast.LENGTH_SHORT).show());
        myPageButton.setOnClickListener(v -> Toast.makeText(CrewRoomActivity.this, "마이페이지 버튼 클릭됨", Toast.LENGTH_SHORT).show());

        // 라디오 버튼 초기화
        radioCalendar = findViewById(R.id.radio_calendar);
        radioExchange = findViewById(R.id.radio_exchange);
        radioWorkerList = findViewById(R.id.radio_worker_list);
        radioGroup = findViewById(R.id.radio_group);

        // 라디오 버튼 선택 이벤트 리스너 설정
        radioCalendar.setOnClickListener(v -> {
            Toast.makeText(CrewRoomActivity.this, "캘린더 선택됨", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CrewRoomActivity.this, CalendarActivity.class);
            startActivity(intent);
        });
        radioExchange.setOnClickListener(v -> Toast.makeText(CrewRoomActivity.this, "교환하기 선택됨", Toast.LENGTH_SHORT).show());
        radioWorkerList.setOnClickListener(v -> Toast.makeText(CrewRoomActivity.this, "알바생 리스트 선택됨", Toast.LENGTH_SHORT).show());
    }
}
