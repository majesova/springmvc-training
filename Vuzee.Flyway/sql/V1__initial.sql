CREATE TABLE candidates(
	id bigserial PRIMARY KEY,
	name varchar(100) NOT NULL,
	positionApplied varchar(100) NOT NULL,
	create_at timestamp NOT NULL,
	create_by varchar(100) NOT NULL
);
CREATE TABLE tasks(
	id bigserial PRIMARY KEY,
	task_date timestamp,
	hasRating boolean  NOT NULL,
	create_at timestamp NOT NULL,
	create_by varchar(100) NOT NULL,
	task_type integer NOT NULL,
	candidate_id bigint  REFERENCES candidates(id)
);
