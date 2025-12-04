package com.example.localnow.utils;

import com.example.localnow.model.Event;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Music Festival", "Weekly Views : 12,303", "Music", true, "2025-05-03", "2025-05-07"));
        events.add(new Event("Incheon Marathon", "~2025.11.23", "Marathon", true, "2025-11-23", "2025-11-23"));
        events.add(new Event("Incheon Marathon", "~2025.11.23", "Marathon", true, "2025-11-23", "2025-11-23"));
        events.add(new Event("Food Festival", "June 5, 2025", "Food", true, "2025-06-05", "2025-06-05"));
        events.add(new Event("Food Festival", "June 5, 2025", "Food", true, "2025-06-05", "2025-06-05"));
        events.add(new Event("Incheon Marathon", "~2025.11.23", "Marathon", true, "2025-11-23", "2025-11-23"));
        return events;
    }
}
