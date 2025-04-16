import { useState, useEffect } from 'react';

import MonsterService from '../services/MonsterService';
import { useParams } from 'react-router-dom';

export default function MonsterDetail() {

const [monsterDet, setMonsterDet] = useState({});
const { monsterName } = useParams();
const [armorClass, setArmorClass] = useState({});

useEffect(() => {
    MonsterService.getMonsterDetail(monsterName)
    .then((response) => {
        console.log(response.data.armor_class);
        setMonsterDet(response.data);
        setArmorClass(response.data.armor_class);
    })
},[]);

return (
    <>
    <h1>{monsterDet.name}</h1>
    <p>
        <span>{monsterDet.size}</span>&nbsp;
        <span>{monsterDet.type}</span>&nbsp;
        <span>{monsterDet.alignment}</span>
    </p>
    <p>
        <span>Armor Class: {armorClass[0].value}</span>&nbsp;
        <span> {armorClass[0].type}</span>
    </p>
    <p>
        <span>Hit Points: {monsterDet.hit_points}</span>&nbsp;
        <span>{`(${monsterDet.hit_dice})`}</span>
    </p>
    <p>
        <span>Speed: {monsterDet.speed.walk}</span>&nbsp;
        <span>{`swim ${monsterDet.speed.swim}`}</span>&nbsp;
        <span>{`fly ${monsterDet.speed.fly}`}</span>&nbsp;
        <span>{`climb ${monsterDet.speed.climb}`}</span>&nbsp;
        <span>{`burrow ${monsterDet.speed.burrow}`}</span>&nbsp;
        <span>{`hover ${monsterDet.speed.hover}`}</span>&nbsp;
    </p>
    </>
)
}