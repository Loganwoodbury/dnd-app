import { useState, useEffect } from "react";
import MonsterService from "../../services/MonsterService";
import { Link } from "react-router-dom";
import LetterSearchComponent from "../../components/LetterSearchComponent/LetterSearchComponent";
import styles from './MonsterPage.module.css';


export default function MonsterPage() {

    const [errorMessage, setErrorMessage] = useState('');
    const [monster, setMonster] = useState([]);
    const [filterMonster, setFilterMonster] = useState([]);


    useEffect(() => {
        MonsterService.getAllMonsters()
        .then((response) => {
            setMonster(response.data);
            setFilterMonster(response.data);
            
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

    function handleSearch(letter) {
        setFilterMonster(letter ? monster.filter((item) => item.name.startsWith(letter)) : monster);
        console.log(letter);
        console.log(filterMonster);
    }


    return (
        <>
        <div>{errorMessage}</div>
        <h1>Bestiary</h1>
        <LetterSearchComponent onLetterClick={handleSearch} />
        <ul>
            {filterMonster.map((item, index) => (
                <li key={index}>
                <Link to={`/monster/${item.name.split(' ').join('-').toLowerCase()}`}> {item.name} </Link>
                </li>
            ))}
        </ul>
        </>
    )
}