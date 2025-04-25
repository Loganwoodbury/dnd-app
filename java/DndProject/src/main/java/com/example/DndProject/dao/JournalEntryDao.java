package com.example.DndProject.dao;

import com.example.DndProject.Models.JournalEntry.JournalEntry;

import java.util.List;

public interface JournalEntryDao {

    List<JournalEntry> getAllEntries();

    JournalEntry createJournalEntry(JournalEntry journalEntry);
}
