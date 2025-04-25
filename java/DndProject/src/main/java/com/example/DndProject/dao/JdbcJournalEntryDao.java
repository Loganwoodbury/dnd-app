package com.example.DndProject.dao;

import com.example.DndProject.Models.JournalEntry.JournalEntry;
import com.example.DndProject.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SqlArrayValue;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcJournalEntryDao implements JournalEntryDao {

    private final JdbcTemplate jdbcTemplate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public JdbcJournalEntryDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

   @Override
    public List <JournalEntry> getAllEntries(){

        List<JournalEntry> journalEntries = new ArrayList<>();

        String sql = "SELECT * FROM journal_entries;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                JournalEntry journalEntry = mapRowToJournalEntry(results);
                journalEntries.add(journalEntry);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return journalEntries;
    }

    @Override
    public JournalEntry createJournalEntry(JournalEntry journalEntry){
        JournalEntry newJournalEntry = null;

        String newEntrySql = "INSERT INTO journal_entries(title, entry_date, description)" +
                "VALUES(?,?,?) RETURNING *;";

        try {
            SqlRowSet insertedRow = jdbcTemplate.queryForRowSet(newEntrySql, journalEntry.getTitle(), LocalDateTime.now(), journalEntry.getDescription());

            if(insertedRow.next()) {
                newJournalEntry = mapRowToJournalEntry(insertedRow);
            }
        }catch(BadSqlGrammarException bsg){
            throw new DaoException("Unable to process request", bsg);
        }catch(CannotGetJdbcConnectionException connEx){
            throw new DaoException("Unable to communicate with server", connEx);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new DaoException("Data integrity violation", e);
        }catch(Exception ex){
            throw new DaoException("Please contact admin", ex);
        }

        return newJournalEntry;
    }

    private JournalEntry mapRowToJournalEntry(SqlRowSet rs) {
        JournalEntry journalEntry = new JournalEntry();

        journalEntry.setId(rs.getInt("id"));
        journalEntry.setTitle(rs.getString("title"));
        journalEntry.setEntry_date(LocalDateTime.parse(rs.getString("entry_date"), formatter));
        journalEntry.setDescription(rs.getString("description"));

        return journalEntry;
    }
}
