import { useState, useEffect } from 'react';

import MonsterService from '../services/MonsterService';
import { useParams } from 'react-router-dom';

export default function MonsterDetail() {

const [monsterDet, setMonsterDet] = useState({});
const { monsterName } = useParams();
const [armorClass, setArmorClass] = useState({});
const [isLoading, setIsLoading] = useState(false);
const [errorMessage, setErrorMessage] = useState('');

function getMonsterDetail() {

    setIsLoading(true);

    MonsterService.getMonsterDetail(monsterName)
    .then((response) => {
        console.log(response.data);
        setMonsterDet(response.data);
        setArmorClass(response.data.armor_class || []);
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
        setIsLoading(false);
    })


}

useEffect(() => {
    getMonsterDetail();
},[]);


return (
    <>
        {isLoading ? (
            <p>Loading...</p>
        ) : (
            <> 
                <h1>{monsterDet.name}</h1>
                <p>
                    <span>{monsterDet.size}</span>&nbsp;
                    <span>{monsterDet.type}</span>&nbsp;
                    <span>{monsterDet.alignment}</span>
                </p>
                <hr />
                {armorClass.length > 0 && (
                    
                    <p>
                        <span>Armor Class: {armorClass[0].value}</span>&nbsp;
                        <span> {armorClass[0].type}</span>
                    </p>
                )}
                <p>
                    <span>Hit Points: {monsterDet.hit_points}</span>&nbsp;
                    <span>{`(${monsterDet.hit_dice})`}</span>
                </p>

                {monsterDet.speed && (
                <p>
                    <span>Speed: {monsterDet.speed.walk || 0}</span>&nbsp;
                    {monsterDet.speed.swim && (
                        <span>{`swim ${monsterDet.speed.swim || 0}`}&nbsp;</span>
                    )}
                    {monsterDet.speed.fly && (
                        <span>{`fly ${monsterDet.speed.fly || 0}`}&nbsp;</span>
                    )}
                    {monsterDet.speed.climb && (
                        <span>{`climb ${monsterDet.speed.climb || 0}`}&nbsp;</span>
                    )}
                    {monsterDet.speed.burrow && (
                        <span>{`burrow ${monsterDet.speed.burrow || 0}`}&nbsp;</span>
                    )}
                    {monsterDet.speed.hover && (
                        <span>{`hover ${monsterDet.speed.hover || 0}`}&nbsp;</span>
                    )}
                </p>
                )}
                <hr />
                {monsterDet.strength && (
                    <>
                        <p>
                            <span>Strength</span>&nbsp;
                            <span>Dexterity</span>&nbsp;
                            <span>Constitution</span>&nbsp;
                            <span>Intelligence</span>&nbsp;
                            <span>Wisdom</span>&nbsp;
                            <span>Charisma</span>
                        </p>
                        <p>
                            <span>{monsterDet.strength}</span>&nbsp;
                            <span>{monsterDet.dexterity}</span>&nbsp;
                            <span>{monsterDet.constitution}</span>&nbsp;
                            <span>{monsterDet.intelligence}</span>&nbsp;
                            <span>{monsterDet.wisdom}</span>&nbsp;
                            <span>{monsterDet.charisma}</span>
                        </p>
                    </>
                )}
                <hr />
                {monsterDet.proficiencies && (
                    <>
                        <h3>Proficiencies</h3>
                        <ul>
                            {monsterDet.proficiencies.map((proficiency) => (
                                <li key={proficiency.name}>
                                    {proficiency.name}: {proficiency.value}
                                </li>
                            ))}
                        </ul>
                    </>
                )}
                {monsterDet.damage_immunities && monsterDet.damage_immunities.length > 0 && (
                    <>
                        <p>
                            <span>Damage Immunities: </span>
                            {monsterDet.damage_immunities.map((immunity) => (
                                <span key={immunity}>{immunity}, </span>
                            ))}
                        </p>
                    </>
                )}
                {monsterDet.damage_vulnerabilities && monsterDet.damage_vulnerabilities.length > 0 && (
                    <>
                        <p>
                            <span>Damage Vulnerabilities: </span>
                            {monsterDet.damage_vulnerabilities.map((vulnerability) => (
                                <span key={vulnerability}>{vulnerability}, </span>
                            ))}
                        </p>
                    </>
                )}
                {monsterDet.damage_resistances && monsterDet.damage_resistances.length > 0 && (
                    <>
                        <p>
                            <span>Damage Resistances: </span>
                            {monsterDet.damage_resistances.map((resistance) => (
                                <span key={resistance}>{resistance}, </span>
                            ))}
                        </p>
                    </>
                )}
                {monsterDet.condition_immunities && monsterDet.condition_immunities.length > 0 && (
                    <>
                        <p>
                            <span>Condition Immunities: </span>
                            {monsterDet.condition_immunities.map((immunity) => (
                                <span key={immunity.index}>{immunity.name}, </span>
                            ))}
                        </p>
                    </>
                )}
                {monsterDet.senses && (
                    <>
                        <p>
                            <span>Senses: </span>
                            {monsterDet.senses.blindsight && (
                                <span>{`Blindsight ${monsterDet.senses.blindsight}ft.`}</span>
                            )}
                            {monsterDet.senses.darkvision && (
                                <span>{`Darkvision ${monsterDet.senses.darkvision}ft.`}</span>
                            )}
                            {monsterDet.senses.truesight && (
                                <span>{`Truesight ${monsterDet.senses.truesight}ft.`}</span>
                            )}
                            {monsterDet.senses.tremorsense && (
                                <span>{`Tremorsense ${monsterDet.senses.tremorsense}ft.`}</span>
                            )}
                            {monsterDet.senses.passive_perception && (
                                <span>{`Passive Perception ${monsterDet.senses.passive_perception}`}</span>
                            )}
                        </p>
                    </>
                )}
                {monsterDet.languages && (
                    <>
                        <p>
                            <span>{`Languages: ${monsterDet.languages}`}</span>
                        </p>
                    </>
                )}
                {monsterDet.challenge_rating && (
                    <>
                        <p>
                            <span>{`Challenge Rating: ${monsterDet.challenge_rating} (${monsterDet.xp} XP)`}</span>
                        </p>
                    </>
                )}
                <hr />
                {monsterDet.special_abilities && monsterDet.special_abilities.length > 0 && (
                    <>
                    {monsterDet.special_abilities.map((ability) => (
                        <div key= {ability.name}>
                            <span><strong>{ability.name}</strong></span>
                            <span>{ability.desc}</span>
                        </div>
                    ))}
                    </>
                )}
                <h3>Actions</h3>
                <hr />
                {monsterDet.actions && monsterDet.actions.length > 0 && ( 
                    <>
                        {monsterDet.actions.map((action) => (
                            <div key={action.name}>
                                <span><strong>{action.name}</strong>&nbsp;</span>
                                <span>{action.desc}</span>
                            </div>
                        ))} 
                    </>
                )}
                
                {monsterDet.legendary_actions && monsterDet.legendary_actions.length > 0 && (
                    <>
                    <h3>Legendary Actions</h3>
                    <hr />
                        {monsterDet.legendary_actions.map((action) => (
                            <div key={action.name}>
                                <span><strong>{action.name}</strong>&nbsp;</span>
                                <span>{action.desc}</span>
                            </div>
                        ))}
                    </>
                )}
            </>
        )}
    </>
)
}