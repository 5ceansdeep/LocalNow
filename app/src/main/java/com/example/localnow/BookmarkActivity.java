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

        fetchBookmarks(recyclerView);
    }

    private void fetchBookmarks(RecyclerView recyclerView) {
        com.example.localnow.api.RetrofitClient.getApiService().getBookmarks()
                .enqueue(new retrofit2.Callback<List<Event>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<Event>> call, retrofit2.Response<List<Event>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            EventAdapter adapter = new EventAdapter(response.body());
                            recyclerView.setAdapter(adapter);
                        } else {
                            // Fallback to local mock data for demo if server fails or returns empty
                            // In a real app, you might show an empty state or error
                            loadMockBookmarks(recyclerView);
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<List<Event>> call, Throwable t) {
                        loadMockBookmarks(recyclerView);
                        android.widget.Toast.makeText(BookmarkActivity.this, "Failed to load bookmarks",
                                android.widget.Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loadMockBookmarks(RecyclerView recyclerView) {
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
