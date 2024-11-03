package com.example.ddip_client;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ddip_client.network.ApiService;
import com.example.ddip_client.models.ddip_db;
import com.example.ddip_client.network.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginSignupActivity extends AppCompatActivity {

    private EditText idInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button signupButton;
    private int res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // EditText와 Button을 연결합니다
        idInput = findViewById(R.id.id_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);

        // 로그인 버튼 클릭 리스너 설정
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputID = idInput.getText().toString();
                String inputPW = passwordInput.getText().toString();

                loginUser(inputID, inputPW);

                if(res == 1){
                    Intent intent = new Intent(LoginSignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    idInput.setText("");
                    passwordInput.setText("");
                }
            }
        });

        // 회원가입 버튼 클릭 리스너 설정
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = idInput.getText().toString().trim();
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

    private void loginUser(String ID, String PW){
        ApiService userApi = RetrofitClient.getClient().create(ApiService.class);
        Call<ddip_db> call = userApi.login(ID, PW);

        call.enqueue(new Callback<ddip_db>() {
            @Override
            public void onResponse(Call<ddip_db> call, Response<ddip_db> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginSignupActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    res = 1;

                }else{
                    Toast.makeText(LoginSignupActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                    res = 0;
                }
            }

            @Override
            public void onFailure(Call<ddip_db> call, Throwable t) {
                Toast.makeText(LoginSignupActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                res = -1;
                Log.e("LoginActivity", t.getMessage());
            }
        });
    }
}
