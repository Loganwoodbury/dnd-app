
import styles from './JournalEntryComponent.module.css';


export default function JournalEntryComponent({ entry }) {

    return (
        <div className={styles.journalEntry}>
            <h2>{entry.title}</h2>
            <p className={styles.date}><strong>Date:</strong> {new Date(entry.entry_date).toLocaleDateString()}</p>

            <p className={styles.content}>{entry.description}</p>
        </div>
    );
}