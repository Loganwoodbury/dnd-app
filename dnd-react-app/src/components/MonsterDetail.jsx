import { useState, useEffect } from 'react';

import MonsterService from '../services/MonsterService';
import { useParams } from 'react-router-dom';
import styles from '../components/MonsterDetail.module.css';

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

function handleSubmit(){
    const monster = {
        index: monsterDet.index,
        name: monsterDet.name,
        size: monsterDet.size,
        type: monsterDet.type,
        alignment: monsterDet.alignment,
        hit_points: monsterDet.hit_points,
        hit_dice: monsterDet.hit_dice,
        hit_points_roll: monsterDet.hit_points_roll,
        strength: monsterDet.strength,
        dexterity: monsterDet.dexterity,
        constitution: monsterDet.constitution,
        intelligence: monsterDet.intelligence,
        wisdom: monsterDet.wisdom,
        charisma: monsterDet.charisma,
        languages: monsterDet.languages,
        proficiency_bonus: monsterDet.proficiency_bonus,
        xp: monsterDet.xp,
        challenge_rating: monsterDet.challenge_rating,
        url: monsterDet.url,
        armor_class: armorClass ? armorClass.map(ac => ({
            value: ac.value,
            type: ac.type,
        })) : [],
        speed: monsterDet.speed ? {
            walk: monsterDet.speed.walk || 0,
            swim: monsterDet.speed.swim || 0,
            fly: monsterDet.speed.fly || 0,
            climb: monsterDet.speed.climb || 0,
            burrow: monsterDet.speed.burrow || 0,
            hover: monsterDet.speed.hover || 0
        } : {},
        proficiencies: monsterDet.proficiencies ? monsterDet.proficiencies.map(prof => ({
            value: prof.value,
            proficiency: {
                index: prof.proficiency.index,
                name: prof.proficiency.name,
                url: prof.proficiency.url
            }
        })) : [],
        damageVulnerabilities: monsterDet.damage_vulnerabilities || [],
        damage_immunities: monsterDet.damage_immunities || [],
        damage_resistances: monsterDet.damage_resistances || [],
        condition_immunities: monsterDet.condition_immunities ? monsterDet.condition_immunities.map(immunity => ({
            index: immunity.index,
            name: immunity.name,
            url: immunity.url
        })) : [],
        senses: monsterDet.senses ? {
            blindsight: monsterDet.senses.blindsight || 0,
            darkvision: monsterDet.senses.darkvision || 0,
            truesight: monsterDet.senses.truesight || 0,
            tremorsense: monsterDet.senses.tremorsense || 0,
            passive_perception: monsterDet.senses.passive_perception || 0
        } : {},
        specialAbilities: monsterDet.special_abilities ? monsterDet.special_abilities.map(ability => ({
            name: ability.name,
            desc: ability.desc,
            damage: ability.damage ? {
                damageType: {
                    index: ability.damage.damage_type.index,
                    name: ability.damage.damage_type.name,
                    url: ability.damage.damage_type.url
                }
            } : null,
            usage: ability.usage ? {
                type: ability.usage.type,
                times: ability.usage.times || 0,
            } : null,
        })) : [],
        actions: monsterDet.actions ? monsterDet.actions.map(action => ({
            name: action.name,
            desc: action.desc,
            attack_bonus: action.attack_bonus || 0,
            damage: action.damage && action.damage.damage_type ? {
                damage_type: {
                    index: action.damage.damage_type.index,
                    name: action.damage.damage_type.name,
                    url: action.damage.damage_type.url
                },
                damage_dice: action.damage.damage_dice || ''
            } : null,
            damage_bonus: action.damage_bonus || 0,
            url: action.url,
            actionDc: action.dc ? {
                dc_type: {
                    index: action.dc.dc_type.index,
                    name: action.dc.dc_type.name,   
                    url: action.dc.dc_type.url
                },
                dc_value: action.dc.dc_value || 0,
                success_type: action.dc.success_type || ''
            } : null,

        })) : []
    }

    MonsterService.createMonster(monster)
        .then((response) => {
            console.log('Monster created successfully, from database:', response.data);
            // Optionally, redirect or show a success message
        })
        .catch((error) => {
            console.error('Error creating monster:', error);
            // Optionally, show an error message
        });
}

function handleLookup() {
    MonsterService.getMonsterByName(monsterDet.name)
        .then((response) => {
            console.log('Monster retrieved successfully:', response.data);
            // Optionally, redirect or show a success message
        })
        .catch((error) => {
            console.error('Error retrieving monster:', error);
            // Optionally, show an error message
        });
}


return (
    <>
        {isLoading ? (
            <p>Loading...</p>
        ) : (
            <div className={styles.modalBackdrop}>
            <button onClick={handleSubmit}>Save Monster</button>
            <button onClick={handleLookup}>Look Up Monster</button>

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
                        <div className={styles.stats}>
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
                        </div>
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
                                <span>{`Blindsight ${monsterDet.senses.blindsight}`}</span>
                            )}
                            {monsterDet.senses.darkvision && (
                                <span>{`Darkvision ${monsterDet.senses.darkvision}`}</span>
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
                            <span>&nbsp;{ability.desc}</span>
                        </div>
                    ))}
                    </>
                )}
                <h3 id={styles.actionTitle}>Actions</h3>
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
                    <h3 id={styles.legendaryActionTitle}>Legendary Actions</h3>
                    <hr />
                        {monsterDet.legendary_actions.map((action) => (
                            <div key={action.name}>
                                <span><strong>{action.name}</strong>&nbsp;</span>
                                <span>{action.desc}</span>
                            </div>
                        ))}
                    </>
                )}
            </div>
        )}
    </>
)
}