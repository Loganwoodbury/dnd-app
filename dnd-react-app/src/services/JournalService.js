import axios from 'axios';

const http = axios.create( {
    baseURL: 'http://localhost:8080/journal'
});

export default {
    getAllEntries() {
        return http.get();
    },

    createEntry(journalEntry) {
        return http.post('', journalEntry);
    }
}