package com.example.localnow;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // Kakao Map temporarily disabled
    // private com.kakao.vectormap.MapView mapView;
    // private com.kakao.vectormap.KakaoMap kakaoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Kakao Map initialization - DISABLED (native library issue)
        /*
         * try {
         * mapView = findViewById(R.id.map_view);
         * mapView.start(new com.kakao.vectormap.MapLifeCycleCallback() {
         * 
         * @Override
         * public void onMapDestroy() {
         * android.util.Log.d("MainActivity", "Map destroyed");
         * }
         * 
         * @Override
         * public void onMapError(Exception e) {
         * android.util.Log.e("MainActivity", "Map error: " + e.getMessage());
         * android.widget.Toast.makeText(MainActivity.this, "Map error: " +
         * e.getMessage(),
         * android.widget.Toast.LENGTH_SHORT).show();
         * }
         * }, new com.kakao.vectormap.KakaoMapReadyCallback() {
         * 
         * @Override
         * public void onMapReady(com.kakao.vectormap.KakaoMap map) {
         * kakaoMap = map;
         * com.kakao.vectormap.LatLng songdoPosition =
         * com.kakao.vectormap.LatLng.from(37.3948, 126.6392);
         * com.kakao.vectormap.camera.CameraUpdate cameraUpdate =
         * com.kakao.vectormap.camera.CameraUpdateFactory
         * .newCenterPosition(songdoPosition, 15);
         * kakaoMap.moveCamera(cameraUpdate);
         * android.util.Log.d("MainActivity", "Map initialized successfully at Songdo");
         * }
         * });
         * } catch (Exception e) {
         * android.util.Log.e("MainActivity", "Failed to initialize Kakao Map", e);
         * android.widget.Toast.makeText(this, "Map unavailable: " + e.getMessage(),
         * android.widget.Toast.LENGTH_LONG).show();
         * }
         */

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

        // Fetch Events (will populate bottom sheet automatically)
        fetchEvents();

        // Log Kakao Key Hash
        try {
            android.content.pm.PackageInfo info = getPackageManager().getPackageInfo(getPackageName(),
                    android.content.pm.PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = android.util.Base64.encodeToString(md.digest(), android.util.Base64.DEFAULT);
                android.util.Log.d("KeyHash", keyHash);
                // Temporary Toast for easy copying (User requested)
                android.widget.Toast.makeText(this, "KeyHash: " + keyHash, android.widget.Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            android.util.Log.e("KeyHash", "Failed to get key hash", e);
        }
    }

    private void fetchEvents() {
        com.example.localnow.api.RetrofitClient.getApiService().getEvents()
                .enqueue(new retrofit2.Callback<com.example.localnow.model.EventResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<com.example.localnow.model.EventResponse> call,
                            retrofit2.Response<com.example.localnow.model.EventResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            java.util.List<com.example.localnow.model.Event> events = response.body().getData();

                            if (events != null && !events.isEmpty()) {
                                android.util.Log.d("MainActivity", "✅ Fetched " + events.size() + " events");
                                // Update bottom sheet with real data
                                updateBottomSheetWithEvents(events);
                            } else {
                                android.util.Log.w("MainActivity", "⚠️ No events found in response");
                                android.widget.Toast.makeText(MainActivity.this, "이벤트 데이터가 없습니다",
                                        android.widget.Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            android.util.Log.w("MainActivity", "⚠️ Response unsuccessful: " + response.code());
                            android.widget.Toast.makeText(MainActivity.this, "데이터를 불러오지 못했습니다",
                                    android.widget.Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<com.example.localnow.model.EventResponse> call,
                            Throwable t) {
                        android.util.Log.e("MainActivity", "❌ Failed to fetch events: " + t.getMessage());
                        android.widget.Toast.makeText(MainActivity.this, "서버 연결 실패: " + t.getMessage(),
                                android.widget.Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateBottomSheetWithEvents(java.util.List<com.example.localnow.model.Event> events) {
        androidx.viewpager2.widget.ViewPager2 viewPager = findViewById(R.id.viewPager);
        com.google.android.material.tabs.TabLayout tabLayout = findViewById(R.id.tabLayout);

        java.util.List<com.example.localnow.adapters.BottomSheetAdapter.PageData> dataList = new java.util.ArrayList<>();

        // Create pages from real event data
        for (int i = 0; i < Math.min(events.size(), 5); i++) {
            com.example.localnow.model.Event event = events.get(i);

            dataList.add(new com.example.localnow.adapters.BottomSheetAdapter.PageData(
                    event.getCategory() != null ? event.getCategory() : "행사",
                    event.getLocation() != null ? event.getLocation() : "",
                    event.getTitle(),
                    event.getDate() != null ? event.getDate() : "",
                    R.drawable.ic_marker_yellow,
                    v -> {
                        Intent intent = new Intent(MainActivity.this, EventDetailActivity.class);
                        intent.putExtra("id", event.getId());
                        intent.putExtra("title", event.getTitle());
                        intent.putExtra("date", event.getDate());
                        intent.putExtra("location", event.getLocation());
                        intent.putExtra("description", event.getDescription());
                        intent.putExtra("image", event.getImage());
                        startActivity(intent);
                    }));
        }

        // If no events, show empty state
        if (dataList.isEmpty()) {
            dataList.add(new com.example.localnow.adapters.BottomSheetAdapter.PageData(
                    "알림",
                    "",
                    "등록된 이벤트가 없습니다",
                    "새로운 이벤트를 기다려주세요",
                    R.drawable.ic_marker_yellow,
                    v -> {
                    }));
        }

        com.example.localnow.adapters.BottomSheetAdapter adapter = new com.example.localnow.adapters.BottomSheetAdapter(
                dataList);
        viewPager.setAdapter(adapter);

        new com.google.android.material.tabs.TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // Tab configuration
                }).attach();
    }

}
