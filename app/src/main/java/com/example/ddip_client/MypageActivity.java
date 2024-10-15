package com.example.ddip_client;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MypageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // activity_mypage.xml과 연결
        setContentView(R.layout.activity_mypage);

        // ------------------ Bottom Navigation (하단바) ------------------
        ImageButton homeButton = findViewById(R.id.home_button);
        ImageButton calendarButton = findViewById(R.id.calendar_button);
        ImageButton crewRoomButton = findViewById(R.id.crew_room_button);
        ImageButton myPageButton = findViewById(R.id.mypage_button);

        homeButton.setOnClickListener(v -> Toast.makeText(this, "홈 버튼 클릭됨", Toast.LENGTH_SHORT).show());
        calendarButton.setOnClickListener(v -> Toast.makeText(this, "캘린더 버튼 클릭됨", Toast.LENGTH_SHORT).show());
        crewRoomButton.setOnClickListener(v -> Toast.makeText(this, "크루룸 버튼 클릭됨", Toast.LENGTH_SHORT).show());

        // 현재 페이지에서는 myPageButton 클릭 시 별다른 액션을 하지 않도록 설정
        myPageButton.setOnClickListener(v -> Toast.makeText(this, "현재 마이페이지입니다", Toast.LENGTH_SHORT).show());
    }
}
