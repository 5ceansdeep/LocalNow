package com.example.localnow.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.localnow.BookmarkActivity;
import com.example.localnow.BookmarkManager;
import com.example.localnow.R;
import com.example.localnow.model.Event;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private final List<Event> events;
    private final BookmarkManager bookmarkManager;

    public EventAdapter(List<Event> events, BookmarkManager bookmarkManager) {
        this.events = events;
        this.bookmarkManager = bookmarkManager;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_card, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event, position);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView dateText;
        ImageButton bookmarkButton;

        EventViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.tv_event_title);
            dateText = itemView.findViewById(R.id.tv_event_date);
            bookmarkButton = itemView.findViewById(R.id.btn_bookmark);
        }

        void bind(Event event, int position) {
            titleText.setText(event.getTitle());
            dateText.setText(event.getDate());

            updateBookmarkIcon(event);

            bookmarkButton.setOnClickListener(v -> {
                bookmarkManager.toggleBookmark(event.getId());
                event.setBookmarked(bookmarkManager.isBookmarked(event.getId()));
                updateBookmarkIcon(event);

                if (itemView.getContext() instanceof BookmarkActivity) {
                    events.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, events.size());
                }
            });
        }

        private void updateBookmarkIcon(Event event) {
            if (bookmarkManager.isBookmarked(event.getId())) {
                bookmarkButton.setImageResource(R.drawable.ic_bookmark_filled);
            } else {
                bookmarkButton.setImageResource(R.drawable.ic_bookmark_border);
            }
        }
    }
}