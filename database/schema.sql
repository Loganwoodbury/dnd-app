START TRANSACTION;

DROP TABLE IF EXISTS monster, player, character, player_character, journal_entries;

CREATE TABLE monster (
	id serial,
	name varchar(50) NOT NULL,
	size varchar(100),
	type varchar(100),
	alignment varchar(50),
	armor_class int,
	hit_points int,
	hit_points_dice varchar(10),
	speed int,
	fly_speed int,
	swim_speed int,
	climb_speed int,
	base_str int,
	mod_str int,
	base_int int,
	mod_int int,
	base_dex int,
	mod_dex int,
	base_cha int,
	mod_cha int,
	base_con int,
	mod_con int,
	base_wis int,
	mod_wis int,
	saving_throw varchar(100),
	skills varchar(255),
	damage_immunities varchar(255),
	damage_Vulnerabilities varchar(255),
	resistances varchar(255),
	condition_immunities varchar(255),
	senses varchar(255),
	languages varchar(255),
	challenge_rating decimal (3,2),
	racial_abilities text,
	actions text,
	legendary_actions text,
	legendary_actions_allowed int,
	description text,
	homebrew boolean,
	
	CONSTRAINT pk_monster_id PRIMARY KEY (id)
);

CREATE TABLE player (
	id serial,
	name varchar(20) NOT NULL,
	
	CONSTRAINT pk_player_id PRIMARY KEY (id)
);

CREATE TABLE character (
	id serial,
	name varchar(50) NOT NULL,
	hit_points int,
	
	CONSTRAINT pk_character_id PRIMARY KEY (id)
);

CREATE TABLE player_character (
	player_id int,
	character_id int,
	
	CONSTRAINT pk_player_character PRIMARY KEY (player_id, character_id)
);

CREATE TABLE journal_entries (
	id serial,
	title varchar(200),
	entry_date TIMESTAMP,
	description text	
);

ALTER TABLE player_character
	ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES player (id),
	ADD CONSTRAINT fk_character_id FOREIGN KEY (character_id) REFERENCES character (id)
;

COMMIT TRANSACTION;