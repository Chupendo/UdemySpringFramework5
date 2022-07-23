-- ERROR: no schema has been selected to create in SQL state: 3F000
-- ejecuta la consulta:
show search_path;
/*
Te muestra para el $user, que esquema tiene por defecto.
Si no existe el esquema que tienes por defecto tendras que prefijarlo para crear objetos en la BD.
 */
SET search_path TO "db_break_limit", information_schema;
/*
Lo que hice es cambiar de esquema public el cual trae por defecto al esquema que yo deseaba ocupar.
 */
-- Database: db_break_limit
-- Creating USERS
DROP USER IF EXISTS springuser;
create user springuser with encrypted password 'mypass'; -- Creates the user

-- Creating DATABASE
DROP DATABASE IF EXISTS db_break_limit;

CREATE DATABASE db_break_limit
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- PRIVILEGIES
GRANT ALL ON DATABASE db_break_limit TO springuser;
GRANT ALL ON DATABASE db_break_limit TO postgres;

-- CONFING
GRANT TEMPORARY, CONNECT ON DATABASE db_break_limit TO PUBLIC;

-- METADATA
COMMENT ON DATABASE db_break_limit
    IS 'Shop of supplementation foods'

-- SPECIFING SECURTY //TODO Revisar, no funciona bien
REVOKE ALL ON DATABASE db_break_limit FROM springuser;
GRANT select, INSERT, UPDATE, DELETE, TEMPORARY, CONNECT ON USER springuser;-- TO springuser;


