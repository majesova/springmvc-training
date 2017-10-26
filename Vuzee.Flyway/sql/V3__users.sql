CREATE TABLE users(
	id bigserial PRIMARY KEY,
	firstname varchar(100) NOT NULL,
	lastname varchar(100) NOT NULL,
	email varchar(250) NOT NULL,
	password varchar(100)
)