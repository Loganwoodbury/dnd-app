package com.example.DndProject.controller;


import com.example.DndProject.Models.JournalEntry.JournalEntry;
import com.example.DndProject.services.JournalEntryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/journal")
public class JournalEntryController {

    private JournalEntryService journalEntryService;

    public JournalEntryController(JournalEntryService journalEntryService){
        this.journalEntryService = journalEntryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<JournalEntry> getJournalEntries(){
        List<JournalEntry> journalEntries = new ArrayList<>();

        journalEntries = journalEntryService.getAllEntries();

        return journalEntries;
    }

    @RequestMapping(method = RequestMethod.POST)
    public JournalEntry createJournalEntry(@RequestBody JournalEntry newJournalEntry){
        JournalEntry journalEntry = null;

        journalEntry = journalEntryService.createEntry(newJournalEntry);

        return journalEntry;
    }

}
