#!/bin/bash
export PGPASSWORD='postgres1'
BASEDIR=$(dirname $0)
DATABASE=DungeonsAndDragons
psql -U postgres -f "$BASEDIR/dropdb.sql" &&
createdb -U postgres $DATABASE &&
psql -U postgres -d $DATABASE -f "$BASEDIR/schema.sql" &&
psql -U postgres -d $DATABASE -f "$BASEDIR/monster_data.sql"