ALTER TABLE "tb_roles_usuarios" ADD CONSTRAINT "tb_roles_usuarios_fk0" FOREIGN KEY ("user_id") REFERENCES "tb_usuarios"("id");

ALTER TABLE "tb_roles_usuarios" ADD CONSTRAINT "tb_roles_usuarios_fk1" FOREIGN KEY ("role_id") REFERENCES "tb_roles"("id");
ALTER TABLE "tb_notificacoes" ADD CONSTRAINT "tb_notificacoes_fk1" FOREIGN KEY ("user_id") REFERENCES "tb_usuarios"("id");