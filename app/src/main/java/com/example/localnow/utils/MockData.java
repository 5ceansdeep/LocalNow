package com.example.localnow.utils;

import com.example.localnow.model.Event;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static List<Event> getEvents() {
        List<Event> events = new ArrayList<>();

        // ✅ 각 줄 앞에 "event_1", "event_2" 등 ID 추가
        events.add(new Event("event_1", "Seoul Jazz Festival", "2025-05-03 ~ 2025-05-07", "Music", false, "2025-05-03", "2025-05-07"));
        events.add(new Event("event_2", "Han River Marathon", "2025-06-15", "Marathon", false));
        events.add(new Event("event_3", "Korean Food Festival", "2025-07-20 ~ 2025-07-22", "Food", false, "2025-07-20", "2025-07-22"));
        // ... 나머지 이벤트들도 동일하게 수정

        return events;
    }
}