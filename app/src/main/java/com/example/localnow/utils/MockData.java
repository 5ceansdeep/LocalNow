package com.example.localnow.utils;

import com.example.localnow.model.Event;
import java.util.ArrayList;
import java.util.List;

public class MockData {
    public static List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Music Festival", "Weekly Views : 12,303", "Music", true));
        events.add(new Event("Incheon Marathon", "~2025.11.23", "Marathon", true));
        events.add(new Event("Incheon Marathon", "~2025.11.23", "Marathon", true));
        events.add(new Event("Food Festival", "June 5, 2025", "Food", true));
        events.add(new Event("Food Festival", "June 5, 2025", "Food", true));
        events.add(new Event("Incheon Marathon", "~2025.11.23", "Marathon", true));
        return events;
    }
}
