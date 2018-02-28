
create table public.calciatore(
	codice bigserial PRIMARY KEY,
	nome VARCHAR(255),
	squadra VARCHAR(255),
	dataNascita DATE, 
	ruolo VARCHAR(255),
	costo real
);
ALTER TABLE public.calciatore OWNER TO postgres;

create table public.utente (
	id bigserial PRIMARY KEY,
	nome VARCHAR(255),
	cognome VARCHAR(255), 
	username VARCHAR(255),
	email VARCHAR(255), 
	password VARCHAR(255),
	CONSTRAINT uq_utente_email UNIQUE (email),
	CONSTRAINT uq_utente_username UNIQUE (username)
);
ALTER TABLE public.utente OWNER TO postgres;

create table public.amministratore (
	id bigserial PRIMARY KEY,
	nome VARCHAR(255),
	cognome VARCHAR(255), 
	username VARCHAR(255),
	email VARCHAR(255), 
	password VARCHAR(255),
	CONSTRAINT uq_amministratore_email UNIQUE (email),
	CONSTRAINT uq_amministratore_username UNIQUE (username)
);
ALTER TABLE public.utente OWNER TO postgres;


CREATE TABLE public.lega(
	id bigserial PRIMARY KEY,
	budget_iniziale numeric(10,2),
	nome character varying(200),
	descrizione character varying(2000),
	fk_utente bigint,
	CONSTRAINT fk_utente_crea_lega FOREIGN KEY (fk_utente) REFERENCES public.utente (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH ( OIDS=FALSE );
ALTER TABLE public.lega OWNER TO postgres;

CREATE TABLE public.invito (
	fk_utente_invia bigint,
	fk_utente_riceve bigint NOT NULL,
	fk_lega integer NOT NULL,
	accettazione boolean,
	CONSTRAINT pk_invito PRIMARY KEY (fk_lega, fk_utente_riceve),
	CONSTRAINT fk_invito_partecipa_lega FOREIGN KEY (fk_lega) REFERENCES public.lega (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_utente_invia_invito FOREIGN KEY (fk_utente_invia) REFERENCES public.utente (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_utente_riceve_invito FOREIGN KEY (fk_utente_invia) REFERENCES public.utente (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION ) WITH ( OIDS=FALSE );
ALTER TABLE public.invito OWNER TO postgres;

CREATE TABLE public.squadra (
	fk_lega bigint NOT NULL,
	fk_utente bigint NOT NULL,
	nome VARCHAR(255) NOT NULL,
	punteggio bigint DEFAULT 0,
	CONSTRAINT pk_squadra PRIMARY KEY (fk_lega, fk_utente),
	CONSTRAINT fk_squadra_comprende_lega FOREIGN KEY (fk_lega) REFERENCES public.lega (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_squadra_di_utente FOREIGN KEY (fk_utente) REFERENCES public.utente (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH ( OIDS=FALSE );
ALTER TABLE public.squadra OWNER TO postgres;

CREATE TABLE public.composta (
	fk_calciatore bigint NOT NULL,
	fk_lega bigint NOT NULL,
	fk_utente bigint NOT NULL,
	CONSTRAINT pk_composta PRIMARY KEY (fk_calciatore, fk_lega, fk_utente),
	CONSTRAINT fk_squadra_composta_calciatore FOREIGN KEY (fk_calciatore) REFERENCES public.calciatore (codice) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_calciatore_composta_squadra FOREIGN KEY (fk_lega, fk_utente) REFERENCES public.squadra (fk_lega, fk_utente) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH ( OIDS=FALSE );
ALTER TABLE public.composta OWNER TO postgres;

CREATE TABLE public.giornata
(
  id bigserial PRIMARY KEY,
  inizio date NOT NULL,
  fine date NOT NULL
) WITH ( OIDS=FALSE );
ALTER TABLE public.giornata OWNER TO postgres;

CREATE TABLE public.partita
(
  id bigserial PRIMARY KEY,
  fk_lega1 bigint NOT NULL,
  fk_utente1 bigint NOT NULL,
  fk_lega2 bigint NOT NULL,
  fk_utente2 bigint NOT NULL,
  fk_giornata bigint NOT NULL,
  punteggio1 integer DEFAULT 0,
  punteggio2 integer DEFAULT 0,
  avvenuta boolean NOT NULL DEFAULT false,
  CONSTRAINT fk_partita_giornata FOREIGN KEY (fk_giornata) REFERENCES public.giornata (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_partita_squadra1 FOREIGN KEY (fk_lega1, fk_utente1) REFERENCES public.squadra (fk_lega, fk_utente) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_partita_squadra2 FOREIGN KEY (fk_lega2, fk_utente2) REFERENCES public.squadra (fk_lega, fk_utente) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH ( OIDS=FALSE );
ALTER TABLE public.partita OWNER TO postgres;

CREATE TABLE public.gioca (
	fk_calciatore bigint NOT NULL,
	fk_giornata bigint NOT NULL,
	voto int,
	CONSTRAINT pk_gioca PRIMARY KEY (fk_calciatore, fk_giornata),
	CONSTRAINT fk_gioca_calciatore FOREIGN KEY (fk_calciatore) REFERENCES public.calciatore (codice) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT fk_gioca_giornata FOREIGN KEY (fk_giornata) REFERENCES public.giornata (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH ( OIDS=FALSE );
ALTER TABLE public.gioca OWNER TO postgres;