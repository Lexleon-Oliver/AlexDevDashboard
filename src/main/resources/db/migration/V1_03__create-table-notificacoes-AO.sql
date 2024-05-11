CREATE TABLE IF NOT EXISTS "tb_notificacoes" (
	"id" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"user_id" bigint NOT NULL,
	"type" text,
	"title" text NOT NULL DEFAULT '100',
	"message" text NOT NULL DEFAULT '100',
	"readed_at" text NOT NULL DEFAULT '20',
	"created_at" text NOT NULL DEFAULT '20',
	"is_active" boolean NOT NULL,
	PRIMARY KEY ("id")
);