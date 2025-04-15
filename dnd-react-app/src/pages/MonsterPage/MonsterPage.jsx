import { useState } from "react"


export default function MonsterPage() {

    const [errorMessage, setErrorMessage] = useState('');

    return (
        <>
        <div>{errorMessage}</div>
        <h1>Bestiary</h1>
        <h2>Search for a Monster</h2>
        </>
    )
}