-- Drop table

-- DROP TABLE public.elemento;
CREATE TABLE users (
	usernick varchar NOT NULL,
	userpassword varchar NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (usernick)
);



CREATE TABLE elemento (
	id serial NOT NULL,
	name varchar NOT NULL,
	idequipo int4 NULL,
	tipo varchar NOT NULL,
	descripcion varchar NOT NULL,
	CONSTRAINT elemento_pkey PRIMARY KEY (id)
);
