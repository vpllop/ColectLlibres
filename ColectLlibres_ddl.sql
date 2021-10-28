

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_tablespace = '';
SET default_with_oids = false;
---
--- drop tables
---

DROP TABLE IF EXISTS usuaris;
DROP TABLE IF EXISTS coleccions;
DROP TABLE IF EXISTS llibres;
DROP TABLE IF EXISTS intercanvis;

# -- DATABASE SENTENCES
CREATE SEQUENCE seq START 1000;

CREATE TABLE IF NOT EXISTS usuaris (
	idUsuari smallint PRIMARY KEY DEFAULT nextval('seq'),
	usuari varchar(20),
	pass varchar(20),
	nom varchar(20) NOT NULL,
	cognoms varchar(20),
	dni varchar(10),
	telefon varchar(10)
);

CREATE TABLE IF NOT EXISTS coleccions (
	idColect smallint PRIMARY KEY DEFAULT nextval('seq'),
	tematica varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS llibres (
	idLlibre smallint PRIMARY KEY DEFAULT nextval('seq'),
	idUsuari smallint NOT NULL REFERENCES ususaris(idUsuari) ON UPDATE CASCADE ON DELETE CASCADE,
	isbn varchar(40) NOT NULL,
	titol varchar(30) NOT NULL,
	autor varchar(30) NOT NULL,
	FOREIGN KEY (idUsuari) REFERENCES usuaris
);

CREATE TABLE IF NOT EXISTS intercanvis (
	idPrestec integer PRIMARY KEY DEFAULT nextval('seq'),
	idLlibre char(9) NOT NULL UNIQUE,
	dataInt date
	FOREIGN KEY (idLlibre) REFERENCES llibres
);





