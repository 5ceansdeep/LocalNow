package com.example.localnow;

import android.app.Application;
import com.kakao.vectormap.KakaoMapSdk;

public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Kakao Map SDK
        KakaoMapSdk.init(this, "YOUR_KAKAO_APP_KEY");
    }
}
