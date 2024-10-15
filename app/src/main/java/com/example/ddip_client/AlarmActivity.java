package com.example.ddip_client;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_alarm.xml과 연결
        setContentView(R.layout.activity_alarm);

        // ------------------ Bottom Navigation (하단바) ------------------
        ImageButton homeButton = findViewById(R.id.home_button);
        ImageButton calendarButton = findViewById(R.id.calendar_button);
        ImageButton crewRoomButton = findViewById(R.id.crew_room_button);
        ImageButton myPageButton = findViewById(R.id.mypage_button);

        // 홈 버튼 클릭 시 홈 액티비티로 이동
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // 캘린더 버튼 클릭 시 캘린더 액티비티로 이동
        calendarButton.setOnClickListener(v -> {
            Toast.makeText(this, "캘린더 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            // Intent를 추가해 캘린더 화면으로 전환 가능
        });

        // 크루룸 버튼 클릭 시 크루룸 액티비티로 이동
        crewRoomButton.setOnClickListener(v -> {
            Toast.makeText(this, "크루룸 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            // Intent를 추가해 크루룸 화면으로 전환 가능
        });

        // 마이페이지 버튼 클릭 시 마이페이지 액티비티로 이동
        myPageButton.setOnClickListener(v -> {
            Intent intent = new Intent(AlarmActivity.this, MypageActivity.class);
            startActivity(intent);
        });
    }
}
