INSERT INTO users (nome, email, password) VALUES ('John Doe', 'Doe@email.com', '123') ON CONFLICT DO NOTHING;
INSERT INTO anuncios (tipo, titulo, user_id) VALUES ('OFERTA', 'Quarto no centro', 1);