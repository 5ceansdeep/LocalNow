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
import java.util.Set;

public class BookmarkActivity extends AppCompatActivity {
    private BookmarkManager bookmarkManager;
    private EventAdapter adapter;
    private List<Event> bookmarkedEvents;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        bookmarkManager = new BookmarkManager(this);

        recyclerView = findViewById(R.id.rv_bookmarks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadBookmarkedEvents();

        adapter = new EventAdapter(bookmarkedEvents, bookmarkManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBookmarkedEvents();
        adapter = new EventAdapter(bookmarkedEvents, bookmarkManager);
        recyclerView.setAdapter(adapter);
    }

    private void loadBookmarkedEvents() {
        bookmarkedEvents = new ArrayList<>();
        Set<String> bookmarkedIds = bookmarkManager.getBookmarks();
        List<Event> allEvents = MockData.getEvents();

        for (Event event : allEvents) {
            if (bookmarkedIds.contains(event.getId())) {
                event.setBookmarked(true);
                bookmarkedEvents.add(event);
            }
        }
    }
}