package com.example.ddip_client;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ddip_client.network.ApiService;
import com.example.ddip_client.models.ddip_db;
import com.example.ddip_client.network.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity{
    private EditText NameInput, IdInput, emailInput, pwdInput, pwdCheck;
    private Button signupBtn, checkIdBtn, checkPwdBtn;
    private CheckBox checkManager;
    private boolean isIdValid = false;
    private boolean isPwdValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        NameInput = findViewById(R.id.name_input);
        IdInput = findViewById(R.id.id_input);
        checkIdBtn = findViewById(R.id.id_check_button);
        emailInput = findViewById(R.id.email_input);
        pwdInput = findViewById(R.id.password_input);
        pwdCheck = findViewById(R.id.password_check_input);
        checkPwdBtn = findViewById(R.id.password_check_button);
        checkManager = findViewById(R.id.manager_checkbox);
        signupBtn = findViewById(R.id.signup_button);

        //아이디 중복 체크 버튼
        checkIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = IdInput.getText().toString();
                checkIdAvailability(id);
                if (isIdValid){
                    checkIdBtn.setText("사용 가능");
                }else{
                    return;
                }
            }
        });

        //비밀번호 확인 버튼
        checkPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pwd1 = pwdInput.getText().toString();
                String pwd2 = pwdCheck.getText().toString();
                checkPwdAvaliability(pwd1, pwd2);
                if (isPwdValid){
                    checkPwdBtn.setText("확인 완료");
                }else{
                    return;
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isIdValid){
                    Toast.makeText(SignupActivity.this, "아이디 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!isPwdValid){
                    Toast.makeText(SignupActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String userid = IdInput.getText().toString();
                String userpwd = pwdInput.getText().toString();
                String name = NameInput.getText().toString();
                String email = emailInput.getText().toString();
                boolean admin;
                if(checkManager.isChecked()){
                    admin = true;
                }else{
                    admin = false;
                }

                ddip_db user = new ddip_db(userid, userpwd, name, email, admin);
                signupUser(user);
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //아이디 중복 확인 요청 함수
    private void checkIdAvailability(String userid) {
        ApiService userApi = RetrofitClient.getClient().create(ApiService.class);
        //서버로 아이디 중복 확인 요청 보내기
        Call<Boolean> call = userApi.checkUserid(userid);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("CheckUsername", "Request URL: " + call.request().url());  // 요청 URL 확인
                Log.d("CheckUsername", "Response Code: " + response.code());     // 응답 코드 확인

                if (response.isSuccessful() && response.body() != null) {
                    boolean isTaken = response.body();
                    Log.d("CheckUsername", "Response Body: " + isTaken);
                    Toast.makeText(SignupActivity.this, "사용 가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                    isIdValid = true;
                } else {
                    Log.d("CheckUsername", "Response Error: " + response.errorBody()); // 에러 응답 출력
                    Toast.makeText(SignupActivity.this, "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT).show();
                    isIdValid = false;
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("registActivity", "Request Failure: " + t.getMessage());  // 실패 메시지 출력
                Toast.makeText(SignupActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                isIdValid = false;
            }
        });
    }

    //비밀번호 확인 함수
    private void checkPwdAvaliability(String pwd1, String pwd2){
        if(pwd1.equals(pwd2) && PwdFormat(pwd1)){
            Toast.makeText(SignupActivity.this, "비밀번호 확인 완료", Toast.LENGTH_SHORT).show();
            isPwdValid = true;
        }else{
            Toast.makeText(SignupActivity.this, "비밀번호를 확인해 주세요.", Toast.LENGTH_SHORT).show();
            isPwdValid = false;
        }
    }

    //비밀번호 조건 함수
    public boolean PwdFormat(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = false; //대문자
        boolean hasLowerCase = false; //소문자
        boolean hasDigit = false; //숫자
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) { //Character 클래스의 isUpperCase 메소드를 호출
                hasUpperCase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        return hasUpperCase && hasLowerCase && hasDigit;
    }

    //회원가입 요청 함수
    private void signupUser(ddip_db user){
        ApiService userApi = RetrofitClient.getClient().create(ApiService.class);
        Call<ddip_db> call = userApi.signup(user);
        call.enqueue(new Callback<ddip_db>() {
            @Override
            public void onResponse(Call<ddip_db> call, Response<ddip_db> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignupActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ddip_db> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("registActivity", t.getMessage());
            }
        });
    }
}
