package com.example.ddip_client;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginSignupActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // EditText와 Button을 연결합니다
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);

        // 로그인 버튼 클릭 리스너 설정
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                // 이메일 입력 여부 확인
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginSignupActivity.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 비밀번호 입력 여부 확인
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginSignupActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 더미 로그인 로직 (실제 인증 로직으로 대체 필요)
                if (email.equals("test@example.com") && password.equals("password")) {
                    Toast.makeText(LoginSignupActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    // 메인 액티비티로 이동하기 위한 Intent
                    Intent intent = new Intent(LoginSignupActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginSignupActivity.this, "이메일 또는 비밀번호가 올바르지 않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 회원가입 버튼 클릭 리스너 설정
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                // 이메일 입력 여부 확인
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginSignupActivity.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 비밀번호 입력 여부 확인
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginSignupActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 더미 회원가입 로직 (실제 등록 로직으로 대체 필요)
                Toast.makeText(LoginSignupActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                // 회원가입 성공 후 메인 액티비티로 이동
                Intent intent = new Intent(LoginSignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
