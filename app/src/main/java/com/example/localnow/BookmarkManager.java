package com.example.localnow;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class BookmarkManager {
    private static final String PREF_NAME = "BookmarkPrefs";
    private static final String KEY_BOOKMARKS = "bookmarked_event_ids";
    private SharedPreferences prefs;

    public BookmarkManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void addBookmark(String eventId) {
        Set<String> bookmarks = getBookmarks();
        bookmarks.add(eventId);
        saveBookmarks(bookmarks);
    }

    public void removeBookmark(String eventId) {
        Set<String> bookmarks = getBookmarks();
        bookmarks.remove(eventId);
        saveBookmarks(bookmarks);
    }

    public void toggleBookmark(String eventId) {
        if (isBookmarked(eventId)) {
            removeBookmark(eventId);
        } else {
            addBookmark(eventId);
        }
    }

    public boolean isBookmarked(String eventId) {
        return getBookmarks().contains(eventId);
    }

    public Set<String> getBookmarks() {
        return new HashSet<>(prefs.getStringSet(KEY_BOOKMARKS, new HashSet<>()));
    }

    private void saveBookmarks(Set<String> bookmarks) {
        prefs.edit().putStringSet(KEY_BOOKMARKS, bookmarks).apply();
    }
}