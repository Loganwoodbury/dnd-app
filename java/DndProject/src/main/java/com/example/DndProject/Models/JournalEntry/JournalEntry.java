package com.example.DndProject.Models.JournalEntry;

import java.time.LocalDateTime;

public class JournalEntry {

    private int id;
    private String title;
    private LocalDateTime entry_date;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(LocalDateTime entry_date) {
        this.entry_date = entry_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
