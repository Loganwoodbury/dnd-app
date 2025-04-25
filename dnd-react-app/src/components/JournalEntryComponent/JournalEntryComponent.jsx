

export default function JournalEntryComponent({ entry }) {

    return (
        <div className="journal-entry">
            <h2>{entry.title}</h2>
            <p>{entry.description}</p>
            <p><strong>Date:</strong> {new Date(entry.entry_date).toLocaleDateString()}</p>
        </div>
    );
}