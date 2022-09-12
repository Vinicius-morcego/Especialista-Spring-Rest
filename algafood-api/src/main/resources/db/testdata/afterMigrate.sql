set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from pedido;
delete from item_pedido;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into cozinha (nome) values('Tailandesa');
insert into cozinha (nome) values('Indiana');

insert into estado (nome) values('Minas Gerais');
insert into estado (nome) values('Goias');
insert into estado (nome) values('Mato Grosso');

insert into cidade (nome, estado_id) values('Campina Verde', 1);
insert into cidade (nome, estado_id) values('Iturama', 1);
insert into cidade (nome, estado_id) values('Paranaiba', 3);

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, ativo) values('Tailandes', '10.2', '1', utc_timestamp, utc_timestamp, '38280000', '27 DE DEZEMBRO', '380', 'A JOIA', 'CENTRO', '2', true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, ativo) values('Indiano', '15.3', '1', utc_timestamp, utc_timestamp, '38270000', 'JUCA TEIXEIRA', '264', 'BRANCA', 'SENHOR TEIXEIRA', '1', true);

insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Bom Bril', 'Esponja de Aço', 2.5, 1, 1);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Colgate', 'Pasta de Dente', 1.5, 1, 1);

insert into forma_pagamento (descricao) values('Cartão de Crédito');
insert into forma_pagamento (descricao) values('Cartão de Débito');
insert into forma_pagamento (descricao) values('Dinheiro');
insert into forma_pagamento (descricao) values('Pix');

insert into permissao (descricao, nome) values('Pode Inserir, Alterar e Excluir', 'ADMINISTRADOR');
insert into permissao (descricao, nome) values('Pode Inserir, Alterar', 'USUÁRIO');
insert into permissao (descricao, nome) values('Pode Inserir', 'ESTAGIÁRIO');

insert into grupo (nome) values('USUARIOS'), ('ADMINISTRADORES'); 

insert into usuario(nome_usuario, email_usuario, senha_usuario, data_cadastro) values ('VINICIUS', 'vinicius.templario@gmail.com','123', utc_timestamp), ('LUQUE', 'luque.templario@gmail.com', 123, utc_timestamp), 
('PEDRO AUGUSTO', 'pedrim.templario@gmail.com', 123, utc_timestamp), ('BRANCA', 'branca.templario@gmail.com', 123, utc_timestamp);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3);

insert into grupo_permissao (grupo_id, permissao_id) values(1, 2), (2, 1);

insert into usuario_grupo (usuario_id, grupo_id) values(1, 1), (2, 2);   



