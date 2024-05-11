CREATE TABLE IF NOT EXISTS "tb_usuarios" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"username" text NOT NULL UNIQUE DEFAULT '30',
	"password" text NOT NULL DEFAULT '120',
	"email" text NOT NULL DEFAULT '50',
	"name" text NOT NULL DEFAULT '50',
	"company" text DEFAULT '30',
	"job_title" text NOT NULL DEFAULT '30',
	"about" text DEFAULT '1000',
	"address" text DEFAULT '100',
	"city" text DEFAULT '30',
	"phone" text DEFAULT '15',
	"has_new_notifications" boolean NOT NULL,
	"is_active" boolean NOT NULL,
	PRIMARY KEY ("id")
);