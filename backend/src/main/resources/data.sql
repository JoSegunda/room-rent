INSERT INTO users (nome, email, password) VALUES ('John Doe', 'Doe@email.com', '123') ON CONFLICT DO NOTHING;
-- Anúncio 1: OFERTA
INSERT INTO anuncios (
    tipo_anuncio,titulo,cidade,endereco,codigo_postal,
    preco,quartos_disponiveis,idade_min,
    idade_max,genero_procurado,area,
    tipologia,andar,user_id
) VALUES (
    'OFERTA','Quarto individual no centro','Lisboa',
    'Rua Augusta, nº 100','1100-053',450.00,1,18,35,
    'INDIFERENTE',85.0,'T2',3,1
);

-- Anúncio 2: PROCURA
INSERT INTO anuncios (
    tipo_anuncio,titulo,cidade,endereco,
    codigo_postal,preco,quartos_disponiveis,
    idade_min,idade_max,genero_procurado,
    area,tipologia,andar,user_id
) VALUES (
    'PROCURA','Procuro quarto perto da universidade','Porto',
    'Rua das Flores, nº 45','4050-262',350.00,1,
    20,30,'FEMININO',70.0,'T3',2,1
);
