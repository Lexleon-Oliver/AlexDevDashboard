CREATE TABLE IF NOT EXISTS "tb_notificacoes" (
    "id" BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "user_id" BIGINT NOT NULL,
    "type" VARCHAR(10) NOT NULL,
    "title" VARCHAR(50) NOT NULL,
    "message" VARCHAR(100) NOT NULL,
    "created_at" VARCHAR(20) NOT NULL,
    "readed_at" VARCHAR(20),
    "is_active" BOOLEAN NOT NULL
);

