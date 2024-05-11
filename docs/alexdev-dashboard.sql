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

CREATE TABLE IF NOT EXISTS "tb_roles" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"name" text NOT NULL,
	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "tb_roles_usuarios" (
	"user_id" bigint NOT NULL,
	"role_id" bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS "tb_notificacoes" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"user_id" bigint NOT NULL,
	"type" text,
	"title" text NOT NULL DEFAULT '100',
	"message" text NOT NULL DEFAULT '100',
	"read_at" text NOT NULL DEFAULT '20',
	"create_at" text NOT NULL DEFAULT '20',
	PRIMARY KEY ("id")
);



ALTER TABLE "tb_roles_usuarios" ADD CONSTRAINT "tb_roles_usuarios_fk0" FOREIGN KEY ("user_id") REFERENCES "tb_usuarios"("id");

ALTER TABLE "tb_roles_usuarios" ADD CONSTRAINT "tb_roles_usuarios_fk1" FOREIGN KEY ("role_id") REFERENCES "tb_roles"("id");
ALTER TABLE "tb_notificacoes" ADD CONSTRAINT "tb_notificacoes_fk1" FOREIGN KEY ("user_id") REFERENCES "tb_usuarios"("id");