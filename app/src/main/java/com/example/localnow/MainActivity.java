package com.example.localnow;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btnNotification, btnBookmark, btnDetail;

        btnNotification = findViewById(R.id.btnNotification);
        btnBookmark = findViewById(R.id.btnBookmark);
        btnDetail = findViewById(R.id.btnDetail);

        btnNotification.setOnClickListener(v -> {
            // NotificationActivity 이동
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);
        });

        btnBookmark.setOnClickListener(v -> {
            // BookmarkActivity 이동
            Intent intent = new Intent(MainActivity.this, BookmarkActivity.class);
            startActivity(intent);
        });

        btnDetail.setOnClickListener(v -> {
            // EventDetailActivity 이동
            Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
            startActivity(intent);
        });

    }
}
