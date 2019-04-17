-- Drop table

-- DROP TABLE public.elemento;
CREATE TABLE users (
	usernick varchar NOT NULL,
	userpassword varchar NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (usernick)
);

CREATE TABLE equipo (
	id int4 NOT NULL,
	lab varchar(10) NOT NULL,
	CONSTRAINT equipo_pkey PRIMARY KEY (id)
);



CREATE TABLE elemento (
	id serial NOT NULL,
	name varchar NOT NULL,
	idequipo int4 NULL,
	tipo varchar NOT NULL,
	descripcion varchar NOT NULL,
	CONSTRAINT elemento_pkey PRIMARY KEY (id),
	CONSTRAINT uc_elemento UNIQUE (idequipo, tipo),
	CONSTRAINT fk_elementoequipo FOREIGN KEY (idequipo) REFERENCES equipo(id)
);
