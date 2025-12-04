package com.example.localnow;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // TODO: Kakao Map will be added later
    // private net.daum.mf.map.api.MapView mapView;
    // private android.widget.RelativeLayout mapViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Initialize MapView later when Kakao SDK is properly configured
        // mapView = new net.daum.mf.map.api.MapView(this);
        // mapViewContainer = findViewById(R.id.map_view);
        // mapViewContainer.addView(mapView);
        // mapView.setMapCenterPoint(net.daum.mf.map.api.MapPoint.mapPointWithGeoCoord(37.3948,
        // 126.6392), true);
        // mapView.setZoomLevel(3, true);

        ImageView btnNotification, btnBookmark, btnSearch;

        btnNotification = findViewById(R.id.btnNotification);
        btnBookmark = findViewById(R.id.btnBookmark);
        btnSearch = findViewById(R.id.btnSearch);

        btnNotification.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);
        });

        btnBookmark.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BookmarkActivity.class);
            startActivity(intent);
        });

        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });

        ImageView btnCalendar = findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        });

        // Setup Bottom Sheet ViewPager
        setupBottomSheet();

        // Fetch Events
        fetchEvents();
    }

    private void fetchEvents() {
        com.example.localnow.api.RetrofitClient.getApiService().getEvents()
                .enqueue(new retrofit2.Callback<java.util.List<com.example.localnow.model.Event>>() {
                    @Override
                    public void onResponse(retrofit2.Call<java.util.List<com.example.localnow.model.Event>> call,
                            retrofit2.Response<java.util.List<com.example.localnow.model.Event>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            // TODO: Display on map when available
                            android.util.Log.d("MainActivity", "Fetched " + response.body().size() + " events");
                        } else {
                            // Fallback to Mock Data
                            android.util.Log.d("MainActivity", "Using mock data");
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<java.util.List<com.example.localnow.model.Event>> call,
                            Throwable t) {
                        android.widget.Toast.makeText(MainActivity.this, "Server error, using mock data",
                                android.widget.Toast.LENGTH_SHORT).show();
                    }
                });
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
                }));

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
                }));

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
                }));

        com.example.localnow.adapters.BottomSheetAdapter adapter = new com.example.localnow.adapters.BottomSheetAdapter(
                dataList);
        viewPager.setAdapter(adapter);

        new com.google.android.material.tabs.TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // No text, just dots
                }).attach();
    }
}
