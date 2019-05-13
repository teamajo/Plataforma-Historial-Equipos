-- Drop table

-- DROP TABLE public.elemento;
CREATE TABLE users (
	usernick varchar NOT NULL,
	userpassword varchar NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (usernick)
);

CREATE TABLE equipo (
	id serial NOT NULL,
	lab int4  NULL,
	name varchar,
	activo bool NULL DEFAULT true,
	CONSTRAINT equipo_pkey PRIMARY KEY (id)
);



CREATE TABLE elemento (
	id serial NOT NULL,
	name varchar NOT NULL,
	idequipo int4 NULL,
	activo bool NULL DEFAULT true,
	tipo varchar NOT NULL,
	descripcion varchar NOT NULL,
	CONSTRAINT elemento_pkey PRIMARY KEY (id),
	CONSTRAINT uc_elemento UNIQUE (idequipo, tipo),
	CONSTRAINT fk_elementoequipo FOREIGN KEY (idequipo) REFERENCES equipo(id)
);

CREATE TABLE novedadequipo (
	id serial NOT NULL,
	titulo varchar NOT NULL,
	idequipo int4 NULL,
	fecha Date NOT NULL,
	descripcion varchar NOT NULL,
	responsable varchar NOT NULL,
	CONSTRAINT novedadequipo_pkey PRIMARY KEY (id),
	CONSTRAINT fk_novedadequipoequipo FOREIGN KEY (idequipo) REFERENCES equipo(id)
);

CREATE TABLE novedadelemento (
	id serial NOT NULL,
	titulo varchar NOT NULL,
	idequipo int4 NULL,
	idelemento int4 NULL,
	fecha Date NOT NULL,
	descripcion varchar NOT NULL,
	responsable varchar NOT NULL,
	CONSTRAINT novedadelemento_pkey PRIMARY KEY (id),
	CONSTRAINT fk_novedadelementoequipo FOREIGN KEY (idequipo) REFERENCES equipo(id),
	CONSTRAINT fk_novedadelementoelemento FOREIGN KEY (idelemento) REFERENCES elemento(id)
);

CREATE TABLE laboratorio (
	id serial NOT NULL,
	activo bool NULL DEFAULT true,
	name varchar NULL,
	fechacreacion date NOT NULL,
	fechacierre date NULL,
	CONSTRAINT laboratorio_pkey PRIMARY KEY (id)
);