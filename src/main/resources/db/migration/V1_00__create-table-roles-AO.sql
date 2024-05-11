CREATE TABLE IF NOT EXISTS "tb_roles" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"name" text NOT NULL,
	"is_active" boolean NOT NULL,
	PRIMARY KEY ("id")
);