import { useState, useEffect } from "react";
import MonsterService from "../../services/MonsterService";
import { Link } from "react-router-dom";


export default function MonsterPage() {

    const [errorMessage, setErrorMessage] = useState('');
    const [monster, setMonster] = useState([]);


    useEffect(() => {
        MonsterService.getAllMonsters()
        .then((response) => {
            setMonster(response.data);
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
    }, []);


    return (
        <>
        <div>{errorMessage}</div>
        <h1>Bestiary</h1>
        <ul>
            {monster.map((item, index) => (
                <li key={index}>
                <Link to={`/monster/${item.name.split(' ').join('-').toLowerCase()}`}> {item.name} </Link>
                </li>
            ))}
        </ul>
        </>
    )
}