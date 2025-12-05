package com.example.localnow;

import android.app.Application;

public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Kakao Map SDK initialization - DISABLED (native library issue)
        // com.kakao.vectormap.KakaoMapSdk.init(this,
        // "68d6b9b8cf31c66b8a16177d7ee4f891");
    }
}
