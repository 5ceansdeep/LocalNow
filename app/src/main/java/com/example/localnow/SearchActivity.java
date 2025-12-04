package com.example.localnow;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.localnow.adapters.EventAdapter;
import com.example.localnow.utils.MockData;

public class SearchActivity extends AppCompatActivity {
    private BookmarkManager bookmarkManager;  // ✅ 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        bookmarkManager = new BookmarkManager(this);  // ✅ 추가

        RecyclerView recyclerView = findViewById(R.id.rv_search_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Use MockData
        EventAdapter adapter = new EventAdapter(MockData.getEvents(), bookmarkManager);  // ✅ bookmarkManager 추가
        recyclerView.setAdapter(adapter);
    }
}