START TRANSACTION;

DROP TABLE IF EXISTS monster, armor_class, proficiency_type, proficiency_junction, damage_type, monster_damage_resistance,
	monster_damage_immunity, monster_damage_vulnerability, condition_type, monster_condition_immunity, special_ability,
	creature_special_ability, actions, creature_action, action_damage_roll, multiattack_sub_action, dc_type, action_dc,
	legendary_action;

CREATE TABLE monster (
	id serial,
	index varchar(255) UNIQUE NOT NULL,
	name VARCHAR(255) UNIQUE NOT NULL,
	size VARCHAR(50) NOT NULL,
	type VARCHAR(50) NOT NULL,
	alignment VARCHAR(100) NOT NULL,
	hit_points INT NOT NULL,
	hit_dice VARCHAR(50) NOT NULL,
	hit_points_roll VARCHAR(50) NOT NULL,
	speed_walk VARCHAR(50),
	speed_fly VARCHAR(50),
	speed_hover VARCHAR(50),
	speed_burrow VARCHAR(50),
	speed_swim VARCHAR(50),
	speed_climb VARCHAR(50),
	strength INT NOT NULL,
    dexterity INT NOT NULL,
    constitution INT NOT NULL,
    intelligence INT NOT NULL,
    wisdom INT NOT NULL,
    charisma INT NOT NULL,
    senses VARCHAR(50),
    senses_passive_perception INT NOT NULL,
    languages TEXT,
    challenge_rating DECIMAL(4,2) NOT NULL,
    proficiency_bonus INT NOT NULL,
    xp INT NOT NULL,
    url TEXT UNIQUE,

	CONSTRAINT pk_monster_id PRIMARY KEY (id)
);

CREATE TABLE armor_class (
	id serial,
	creature_id INT NOT NULL,
	type VARCHAR(50) NOT NULL,
	value INT NOT NULL,
	equipment_desc VARCHAR(255),
	spell_desc VARCHAR(255),

	CONSTRAINT pk_armor_id PRIMARY KEY (id),
	CONSTRAINT fk_creature_armor_class FOREIGN KEY (creature_id) REFERENCES monster (id)
);

CREATE TABLE proficiency_type (
	id serial,
	index VARCHAR(255) UNIQUE NOT NULL,
	name VARCHAR(255) UNIQUE NOT NULL,
	url TEXT UNIQUE,

	CONSTRAINT pk_proficiency_id PRIMARY KEY (id)	
);

CREATE TABLE proficiency_junction (
	creature_id INT NOT NULL,
	proficiency_type_id INT NOT NULL,
	value INT NOT NULL,

	CONSTRAINT pk_creature_proficiency PRIMARY KEY (creature_id, proficiency_type_id),
	CONSTRAINT fk_creature_id FOREIGN KEY (creature_id) REFERENCES monster (id),
	CONSTRAINT fk_proficiency_id FOREIGN KEY (proficiency_type_id) REFERENCES proficiency_type (id)
);

CREATE TABLE damage_type (
	id serial,
	name VARCHAR(100) UNIQUE NOT NULL,
	index VARCHAR(100) UNIQUE,
	url TEXT UNIQUE,

	CONSTRAINT pk_damage_id PRIMARY KEY (id)
);

CREATE TABLE monster_damage_resistance (
	creature_id INT NOT NULL,
	damage_type_id INT,
	notes VARCHAR(255),

	CONSTRAINT pk_monster_dmg_res PRIMARY KEY (creature_id, damage_type_id),
	CONSTRAINT fk_creature_res_id FOREIGN KEY (creature_id) REFERENCES monster (id),
	CONSTRAINT fk_dmg_type_id FOREIGN KEY (damage_type_id) REFERENCES damage_type (id)
);

CREATE TABLE monster_damage_immunity (
	creature_id INT NOT NULL,
	damage_type_id INT,
	notes VARCHAR(255),

	CONSTRAINT pk_monster_dmg_imm PRIMARY KEY (creature_id, damage_type_id),
	CONSTRAINT fk_creature_imm_id FOREIGN KEY (creature_id) REFERENCES monster (id),
	CONSTRAINT fk_dmg_type_id FOREIGN KEY (damage_type_id) REFERENCES damage_type (id)
);

CREATE TABLE monster_damage_vulnerability (
	creature_id INT NOT NULL,
	damage_type_id INT,
	notes VARCHAR(255),

	CONSTRAINT pk_monster_dmg_vul PRIMARY KEY (creature_id, damage_type_id),
	CONSTRAINT fk_creature_vul_id FOREIGN KEY (creature_id) REFERENCES monster (id),
	CONSTRAINT fk_dmg_type_id FOREIGN KEY (damage_type_id) REFERENCES damage_type (id)
);

CREATE TABLE condition_type (
	id serial,
	index VARCHAR(100) UNIQUE NOT NULL,
	name VARCHAR(100) UNIQUE NOT NULL,
	url TEXT UNIQUE,

	CONSTRAINT pk_condition_id PRIMARY KEY (id)
);

CREATE TABLE monster_condition_immunity (
	creature_id INT NOT NULL,
	condition_type_id INT NOT NULL,

	CONSTRAINT pk_monster_cond_imm PRIMARY KEY (creature_id, condition_type_id),
	CONSTRAINT fk_creature_cond_imm FOREIGN KEY (creature_id) REFERENCES monster (id),
	CONSTRAINT fk_creature_cond_imm_type FOREIGN KEY (condition_type_id) REFERENCES condition_type (id)
);

CREATE TABLE special_ability (
	id serial,
	index VARCHAR(255) UNIQUE NOT NULL,
	name VARCHAR(255) UNIQUE NOT NULL,
	ability_desc TEXT,

	CONSTRAINT pk_special_ability PRIMARY KEY (id)
);

CREATE TABLE creature_special_ability (
	creature_id INT NOT NULL,
	special_ability_id INT NOT NULL,

	CONSTRAINT pk_creature_special_ability PRIMARY KEY (creature_id, special_ability_id),
	CONSTRAINT fk_creature_id FOREIGN KEY (creature_id) REFERENCES monster (id),
	CONSTRAINT fk_special_ability_id FOREIGN KEY (special_ability_id) REFERENCES special_ability (id)
);

CREATE TABLE actions (
	id SERIAL,
	name VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
	attack_bonus INT,
	multiattack_type VARCHAR(50),
	usage_type VARCHAR(50),
	usage_times INT,

	CONSTRAINT pk_action_id PRIMARY KEY (id)
);

CREATE TABLE creature_action (
	creature_id INT NOT NULL,
	action_id INT NOT NULL,

	CONSTRAINT pk_creature_action PRIMARY KEY (creature_id, action_id),
	CONSTRAINT fk_creature_id FOREIGN KEY (creature_id) REFERENCES monster (id),
	CONSTRAINT fk_action_id FOREIGN KEY (action_id) REFERENCES actions (id)
);

CREATE TABLE action_damage_roll (
	id serial,
	action_id INT NOT NULL,
	damage_type_id INT NOT NULL,
	damage_dice VARCHAR(50),

	CONSTRAINT pk_action_damage PRIMARY KEY (id),
	CONSTRAINT fk_action_id FOREIGN KEY (action_id) REFERENCES actions(id),
	CONSTRAINT fk_damage_type_id FOREIGN KEY (damage_type_id) REFERENCES damage_type(id)
);

CREATE TABLE multiattack_sub_action (
	id serial,
	multiattack_action_id INT NOT NULL,
	action_name_ref VARCHAR(255) NOT NULL,
	action_count VARCHAR(255) NOT NULL,
	action_type VARCHAR(255),

	CONSTRAINT pk_sub_action_id PRIMARY KEY (id),
	CONSTRAINT fk_action_id FOREIGN KEY (multiattack_action_id) REFERENCES actions (id)
);

CREATE TABLE dc_type (
	id serial,
	index VARCHAR(255) NOT NULL,
	name VARCHAR(255) NOT NULL,
	url TEXT,

	CONSTRAINT pk_dc_type_id PRIMARY KEY (id)
);

CREATE TABLE action_dc (
	id serial,
	dc_type INT NOT NULL,
	dc_value INT NOT NULL,
	success_type TEXT,

	CONSTRAINT pk_action_dc PRIMARY KEY (id),
	CONSTRAINT fk_dc_type FOREIGN KEY (dc_type) REFERENCES dc_type (id)
);

CREATE TABLE legendary_action (
	id serial,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	damage INT,
	dc INT,

	CONSTRAINT pk_legendary_action_id PRIMARY KEY (id),
	CONSTRAINT fk_legend_action_dc FOREIGN KEY (dc) REFERENCES action_dc (id)
);

END TRANSACTION;

SELECT * FROM monster;