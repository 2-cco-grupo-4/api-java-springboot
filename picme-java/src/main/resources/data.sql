INSERT INTO tema (tema) VALUES
('Natureza'),
('Viagem'),
('Arquitetura');

INSERT INTO usuario (tipo_usuario, autenticado, cpf, data_nasc, email, nome, num_celular, senha, token_solicitacao) VALUES
('fotografo', false, '519.747.228-64', '2002-04-29', 'kelvin@email.com', 'Kelvin Guima',
'11 1111-1111', '$2a$10$p9lagrnJxAbqjDgsxmeJz.Z0mIZ.qrI8RmiyhtLFLy/SVu1I8A8da', null);

INSERT INTO album (descricao, id_fotografo, titulo, tema_id) VALUES
('Álbum de viagem pela Europa', 1, 'Europa 2022', 2);

INSERT INTO imagem (descricao, id_album, path, tipo) VALUES
('Torre Eiffel à noite', 1, 'torre_eiffel.jpg', 'Viagem'),
('Coliseu em Roma', 1, 'coliseu.jpg', 'Viagem'),
('Canal de Veneza', 1, 'veneza.jpg', 'Viagem');
