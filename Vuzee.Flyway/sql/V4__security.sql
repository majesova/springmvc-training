/* ---------------------------------------------------- */
/*  Generated by Enterprise Architect Version 12.0 		*/
/*  Created On : 25-oct.-2017 12:23:59 p. m. 				*/
/*  DBMS       : PostgreSQL 						*/
/* ---------------------------------------------------- */


/* Drop Tables */

DROP TABLE IF EXISTS "users" CASCADE
;

DROP TABLE IF EXISTS "roles" CASCADE
;

DROP TABLE IF EXISTS "privileges" CASCADE
;

DROP TABLE IF EXISTS "user_roles" CASCADE
;

DROP TABLE IF EXISTS "roles_privileges" CASCADE
;
/* Drop Sequences for Autonumber Columns */

DROP SEQUENCE IF EXISTS "users_id_seq"
;

/* Create Tables */

CREATE TABLE "users"
(
	"id" bigint NOT NULL DEFAULT nextval(('"users_id_seq"'::text)::regclass),
	"firstName" varchar(100)	 NULL,
	"lastName" varchar(100)	 NULL,
	"email" varchar(300)	 NOT NULL,
	"password" varchar(50)	 NOT NULL,
	"isEmailConfirmed" boolean NULL,
	"isLocked" boolean NULL,
	"username" varchar(100)	 NOT NULL
)
;

CREATE TABLE "roles"
(
	"id" varchar(20)	 NOT NULL,
	"name" varchar(50)	 NULL,
	"isActive" boolean NULL
)
;

CREATE TABLE "privileges"
(
	"id" varchar(20)	 NOT NULL,
	"isActive" boolean NOT NULL,
	"name" varchar(100)	 NOT NULL
)
;

CREATE TABLE "user_roles"
(
	"role_id" varchar(20)	 NULL,
	"user_id" bigint NULL
)
;

CREATE TABLE "roles_privileges"
(
	"privilege_id" varchar(20)	 NULL,
	"role_id" varchar(20)	 NULL
)
;

/* Create Table Comments, Sequences for Autonumber Columns */

CREATE SEQUENCE "users_id_seq" INCREMENT 1 START 1
;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE "users" ADD CONSTRAINT "PK_users"
	PRIMARY KEY ("id")
;

ALTER TABLE "roles" ADD CONSTRAINT "PK_roles"
	PRIMARY KEY ("id")
;

ALTER TABLE "privileges" ADD CONSTRAINT "PK_privileges"
	PRIMARY KEY ("id")
;

CREATE INDEX "IXFK_user_roles_roles" ON "user_roles" ("role_id" ASC)
;

CREATE INDEX "IXFK_user_roles_users" ON "user_roles" ("user_id" ASC)
;

CREATE INDEX "IXFK_user_roles_users_02" ON "user_roles" ("user_id" ASC)
;

CREATE INDEX "IXFK_roles_privileges_privileges" ON "roles_privileges" ("privilege_id" ASC)
;

CREATE INDEX "IXFK_roles_privileges_roles" ON "roles_privileges" ("role_id" ASC)
;

/* Create Foreign Key Constraints */

ALTER TABLE "user_roles" ADD CONSTRAINT "FK_user_roles_roles"
	FOREIGN KEY ("role_id") REFERENCES "roles" ("id") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "user_roles" ADD CONSTRAINT "FK_user_roles_users"
	FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "roles_privileges" ADD CONSTRAINT "FK_roles_privileges_privileges"
	FOREIGN KEY ("privilege_id") REFERENCES "privileges" ("id") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "roles_privileges" ADD CONSTRAINT "FK_roles_privileges_roles"
	FOREIGN KEY ("role_id") REFERENCES "roles" ("id") ON DELETE No Action ON UPDATE No Action
;
