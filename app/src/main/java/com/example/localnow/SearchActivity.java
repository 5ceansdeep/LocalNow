package com.example.localnow;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.localnow.adapters.EventAdapter;
import com.example.localnow.utils.MockData;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EventAdapter adapter;
    private List<com.example.localnow.model.Event> allEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        RecyclerView recyclerView = findViewById(R.id.rv_search_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        android.widget.EditText searchBox = findViewById(R.id.et_search);

        // Fetch events from server or use MockData
        fetchEvents(recyclerView, searchBox);
    }

    private void fetchEvents(RecyclerView recyclerView, android.widget.EditText searchBox) {
        com.example.localnow.api.RetrofitClient.getApiService().getEvents()
                .enqueue(new retrofit2.Callback<java.util.List<com.example.localnow.model.Event>>() {
                    @Override
                    public void onResponse(retrofit2.Call<java.util.List<com.example.localnow.model.Event>> call,
                            retrofit2.Response<java.util.List<com.example.localnow.model.Event>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            allEvents = response.body();
                        } else {
                            allEvents = MockData.getEvents();
                        }
                        setupSearch(recyclerView, searchBox);
                    }

                    @Override
                    public void onFailure(retrofit2.Call<java.util.List<com.example.localnow.model.Event>> call,
                            Throwable t) {
                        allEvents = MockData.getEvents();
                        setupSearch(recyclerView, searchBox);
                    }
                });
    }

    private void setupSearch(RecyclerView recyclerView, android.widget.EditText searchBox) {
        adapter = new EventAdapter(allEvents);
        recyclerView.setAdapter(adapter);

        searchBox.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterEvents(s.toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
            }
        });
    }

    private void filterEvents(String query) {
        List<com.example.localnow.model.Event> filteredList = new ArrayList<>();

        if (query.isEmpty()) {
            filteredList = allEvents;
        } else {
            String lowerQuery = query.toLowerCase();
            for (com.example.localnow.model.Event event : allEvents) {
                if (event.getTitle() != null && event.getTitle().toLowerCase().contains(lowerQuery)) {
                    filteredList.add(event);
                } else if (event.getLocation() != null && event.getLocation().toLowerCase().contains(lowerQuery)) {
                    filteredList.add(event);
                }
            }
        }

        adapter = new EventAdapter(filteredList);
        RecyclerView recyclerView = findViewById(R.id.rv_search_results);
        recyclerView.setAdapter(adapter);
    }
}
