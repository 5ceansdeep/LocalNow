package com.example.localnow;

import android.os.Bundle;
import android.widget.CalendarView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.localnow.adapters.EventAdapter;
import com.example.localnow.model.Event;
import com.example.localnow.utils.MockData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    private List<Event> allEvents;
    private BookmarkManager bookmarkManager;  // ✅ 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        bookmarkManager = new BookmarkManager(this);  // ✅ 추가

        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.rv_calendar_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allEvents = MockData.getEvents();

        // Show events for today initially
        updateEventsForDate(System.currentTimeMillis());

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            updateEventsForDate(calendar.getTimeInMillis());
        });
    }

    private void updateEventsForDate(long dateInMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String selectedDate = sdf.format(dateInMillis);

        List<Event> eventsOnDate = new ArrayList<>();
        for (Event event : allEvents) {
            if (isEventOnDate(event, selectedDate)) {
                eventsOnDate.add(event);
            }
        }

        adapter = new EventAdapter(eventsOnDate, bookmarkManager);  // ✅ bookmarkManager 추가
        recyclerView.setAdapter(adapter);
    }

    private boolean isEventOnDate(Event event, String date) {
        String startDate = event.getStartDate();
        String endDate = event.getEndDate();

        if (startDate.isEmpty() || endDate.isEmpty()) {
            return false;
        }

        // Check if date is between start and end (inclusive)
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }
}