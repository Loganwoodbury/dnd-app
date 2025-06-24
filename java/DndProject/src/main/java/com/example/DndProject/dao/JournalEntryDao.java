package com.example.DndProject.dao;

import com.example.DndProject.Models.JournalEntry.JournalEntry;

import java.util.List;

public interface JournalEntryDao {


    /**
     * Retrieves a list of journal entry objects from the database
     *
     * @return List of JournalEntry objects
     */
    List<JournalEntry> getAllEntries();


    /**
     * Creates a journal entry and post to database.
     * @param journalEntry object being posted to the database
     * @return the mapped JournalEntry from the database
     */
    JournalEntry createJournalEntry(JournalEntry journalEntry);
}
