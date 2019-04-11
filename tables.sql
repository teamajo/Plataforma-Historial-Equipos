-- Drop table

-- DROP TABLE public.elemento;

CREATE TABLE ELEMENTO (
	id int4 NOT NULL,
	"name" varchar NULL,
	idequipo int4 NULL,
	tipo varchar NULL,
	CONSTRAINT elemento_pk PRIMARY KEY (id)
);

