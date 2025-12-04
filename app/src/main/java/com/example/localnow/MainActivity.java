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

        ImageView btnNotification, btnBookmark, btnSearch;
        // androidx.cardview.widget.CardView btnDetail; // Removed as it's now in ViewPager

        btnNotification = findViewById(R.id.btnNotification);
        btnBookmark = findViewById(R.id.btnBookmark);
        btnSearch = findViewById(R.id.btnSearch);
        // btnDetail = findViewById(R.id.btnDetail);

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

        btnSearch.setOnClickListener(v -> {
            // SearchActivity 이동
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        /*
        btnDetail.setOnClickListener(v -> {
            // EventDetailActivity 이동
            Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
            startActivity(intent);
        });
        */

        // Setup Bottom Sheet ViewPager
        setupBottomSheet();
    }

    private void setupBottomSheet() {
        androidx.viewpager2.widget.ViewPager2 viewPager = findViewById(R.id.viewPager);
        com.google.android.material.tabs.TabLayout tabLayout = findViewById(R.id.tabLayout);

        java.util.List<com.example.localnow.adapters.BottomSheetAdapter.PageData> dataList = new java.util.ArrayList<>();

        // 1. Near You
        dataList.add(new com.example.localnow.adapters.BottomSheetAdapter.PageData(
                "Near You",
                ": Within 2km",
                "Music Festival",
                "1.7km",
                R.drawable.ic_marker_yellow,
                v -> {
                    Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
                    startActivity(intent);
                }
        ));

        // 2. Upcoming Schedule
        dataList.add(new com.example.localnow.adapters.BottomSheetAdapter.PageData(
                "Upcoming Schedule",
                "",
                "Incheon Marathon",
                "D-2",
                R.drawable.ic_marker_green,
                v -> {
                    Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
                    startActivity(intent);
                }
        ));

        // 3. Closing Soon
        dataList.add(new com.example.localnow.adapters.BottomSheetAdapter.PageData(
                "Closing Soon",
                "",
                "Incheon Marathon",
                "~2025.11.23",
                R.drawable.ic_marker_green,
                v -> {
                    Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
                    startActivity(intent);
                }
        ));

        com.example.localnow.adapters.BottomSheetAdapter adapter = new com.example.localnow.adapters.BottomSheetAdapter(dataList);
        viewPager.setAdapter(adapter);

        new com.google.android.material.tabs.TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // No text, just dots
                }
        ).attach();
    }

}
