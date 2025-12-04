package com.example.localnow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btnSignupComplete = findViewById(R.id.btn_signup_complete);

        btnSignupComplete.setOnClickListener(v -> {
            // 회원가입 완료 후 메인 화면으로 이동 (또는 로그인 화면으로 이동)
            // 여기서는 바로 메인으로 이동하도록 구현
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // 이전 스택 비우기
            startActivity(intent);
        });
    }
}
