import { useState, useEffect } from 'react';


export default function HomePage() {

    const[errorMessage, setErrorMessage] = useState('');

    return (
        <>
        <div>{ errorMessage }</div>
        <h1>Dnd App</h1>
        </>
    )

}