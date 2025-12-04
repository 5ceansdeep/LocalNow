package com.example.localnow;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.localnow.adapters.EventAdapter;
import com.example.localnow.model.Event;
import com.example.localnow.utils.MockData;
import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        RecyclerView recyclerView = findViewById(R.id.rv_bookmarks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Filter for bookmarked events (MockData already has isBookmarked=true for all, but let's simulate)
        List<Event> allEvents = MockData.getEvents();
        List<Event> bookmarkedEvents = new ArrayList<>();
        for (Event event : allEvents) {
            if (event.isBookmarked()) {
                bookmarkedEvents.add(event);
            }
        }

        EventAdapter adapter = new EventAdapter(bookmarkedEvents);
        recyclerView.setAdapter(adapter);
    }
}
