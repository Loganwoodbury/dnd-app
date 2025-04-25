import { useState, useEffect } from "react";
import JournalService from "../../services/JournalService";
import JournalEntryComponent from "../../components/JournalEntryComponent/JournalEntryComponent";
import styles from './JournalPage.module.css';

export default function JournalPage() {

    const [errorMessage, setErrorMessage] = useState('');
    const [journalEntries, setJournalEntries] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');

    function getJournalEntries() {
        JournalService.getAllEntries()
        .then((response) => {
            setJournalEntries(response.data);
            setIsLoading(false);
        })
        .catch ((error) => {
            if (error.response) {
                setErrorMessage(`Received an error message from the server: ${error.response.status}`);
            } else if (error.request){
                setErrorMessage('No response from the server')
            } else {
                setErrorMessage('An error occurred while creating request');
            }
        })
    }

    function handleSubmit(event) {
        event.preventDefault();
        const journalEntry = {
            title: title,
            description: description,
        };

        JournalService.createEntry(journalEntry)
        .then((response) => {
            getJournalEntries();
            setTitle('');
            setDescription('');
        })
        .catch ((error) => {
            if (error.response) {
                setErrorMessage(`Received an error message from the server: ${error.response.status}`);
            } else if (error.request){
                setErrorMessage('No response from the server')
            } else {
                setErrorMessage('An error occurred while creating request');
            }
        })
    }

    useEffect(() => {
        getJournalEntries();
    }, []);

    return (
        <>
        <div>{errorMessage}</div>
        <h1>Game Journal</h1>

        {isLoading ? (
            <p>Loading...</p>
        ) : (
            <>
            <div className={styles.entryContainer}>
            {journalEntries.map((entry, index) => (
                <JournalEntryComponent entry={entry} key={index} />
            ))}
            </div>

            <form onSubmit={handleSubmit}>
                <input 
                    type="text" 
                    placeholder="Title..."
                    value={title}
                    onChange={(e) => setTitle(e.target.value)} 
                />
                <textarea 
                    placeholder="Journal Entry..."
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}>
                </textarea>
                <button type="submit">Add Entry</button>
            </form>
            </>
        )}
        </>
    )

}