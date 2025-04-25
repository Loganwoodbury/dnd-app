package com.example.DndProject.services;

import com.example.DndProject.Models.JournalEntry.JournalEntry;
import com.example.DndProject.dao.JournalEntryDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {

    private final JournalEntryDao journalEntryDao;

    public JournalEntryService (JournalEntryDao journalEntryDao){
        this.journalEntryDao = journalEntryDao;
    }

    public List<JournalEntry> getAllEntries(){
        return journalEntryDao.getAllEntries();
    }

    public JournalEntry createEntry(JournalEntry journalEntry){
        return journalEntryDao.createJournalEntry(journalEntry);
    }
}
