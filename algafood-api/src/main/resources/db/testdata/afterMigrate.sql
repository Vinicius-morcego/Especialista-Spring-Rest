set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from permissao;
delete from grupo_permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from pedido;
delete from item_pedido;
delete from foto_produto;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table permissao auto_increment = 1;
alter table grupo auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;

insert into cozinha (nome) values('Tailandesa');
insert into cozinha (nome) values('Indiana');
insert into cozinha (nome) values('Francesa');
insert into cozinha (nome) values('Japonesa');

insert into estado (nome) values('Minas Gerais');
insert into estado (nome) values('Goias');
insert into estado (nome) values('Mato Grosso');

insert into cidade (nome, estado_id) values('Campina Verde', 1);
insert into cidade (nome, estado_id) values('Iturama', 1);
insert into cidade (nome, estado_id) values('Uberlândia', 1);
insert into cidade (nome, estado_id) values('Paranaiba', 3);

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, ativo, aberto) values('Tailandes', '10.2', '1', utc_timestamp, utc_timestamp, '38280000', '27 DE DEZEMBRO', '380', 'A JOIA', 'CENTRO', '2', true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, ativo, aberto) values('Indiano', '15.3', '1', utc_timestamp, utc_timestamp, '38270000', 'JUCA TEIXEIRA', '264', 'BRANCA', 'SENHOR TEIXEIRA', '1', true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, ativo, aberto) values('Japonês', '10.15', '1', utc_timestamp, utc_timestamp, '38270000', 'JUCA TEIXEIRA', '264', 'BRANCA', 'SENHOR TEIXEIRA', '3', true, true);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, ativo, aberto) values('Francês', '12.7', '1', utc_timestamp, utc_timestamp, '38270000', 'JUCA TEIXEIRA', '264', 'BRANCA', 'SENHOR TEIXEIRA', '2', true, true);

insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Bom Bril', 'Esponja de Aço', 2.5, 0, 1);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Colgate', 'Pasta de Dente', 1.5, 1, 1);

insert into forma_pagamento (descricao, data_atualizacao) values('Cartão de Crédito', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values('Cartão de Débito', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values('Dinheiro', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values('Pix', utc_timestamp);

#insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (1, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'CONSULTAR_CIDADES', 'Permite consultar cidades');
#insert into permissao (id, nome, descricao) values (5, 'EDITAR_CIDADES', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_ESTADOS', 'Permite consultar estados');
#insert into permissao (id, nome, descricao) values (7, 'EDITAR_ESTADOS', 'Permite editar estados');
insert into permissao (id, nome, descricao) values (4, 'CONSULTAR_USUARIOS', 'Permite consultar usuarios');
insert into permissao (id, nome, descricao) values (5, 'EDITAR_USUARIOS', 'Permite editar usuarios');
insert into permissao (id, nome, descricao) values (6, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
#insert into permissao (id, nome, descricao) values (11, 'EDITAR_RESTAURANTES', 'Permite editar restaurantes');
insert into permissao (id, nome, descricao) values (7, 'CONSULTAR_PRODUTOS', 'Permite consultar produtos');
#insert into permissao (id, nome, descricao) values (13, 'EDITAR_PRODUTOS', 'Permite editar produtos');
#insert into permissao (id, nome, descricao) values (14, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissao (id, nome, descricao) values (8, 'EDITAR_PEDIDOS', 'Permite editar pedidos');
insert into permissao (id, nome, descricao) values (9, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissao (id, nome, descricao) values (10, 'GERAR_RELATORIOS', 'Permite gerar relatórios');
insert into permissao (id, nome, descricao) values (11, 'EDITAR_FORMAS_PAGAMENTO', 'Permite editar formas de pagamento');

insert into grupo (id, nome) values(1, 'Gerente'), (2, 'Vendedor'), (4, 'Cadastrador'), (3, 'Secretária'); 

#Adiciona todas as permissoes no grupo do gerente
insert into grupo_permissao (grupo_id, permissao_id)
select 1, id from permissao;

#Adiciona todas as permissoes no grupo do vendedor
insert into grupo_permissao (grupo_id, permissao_id)
select 2, id from permissao where nome like 'CONSULTAR_%';

#insert into grupo_permissao (grupo_id, permissao_id) values(2, 14);

#Adiciona todas as permissoes no grupo do auxiliar
insert into grupo_permissao (grupo_id, permissao_id)
select 3, id from permissao where nome like 'CONSULTAR_%';

#Adiciona todas as permissoes no grupo do cadastrador
insert into grupo_permissao (grupo_id, permissao_id)
select 4, id from permissao where nome like 'EDITAR_%';

insert into usuario(nome_usuario, email_usuario, senha_usuario, data_cadastro) values 
('VINICIUS', 'vinicius.ger@gmail.com','$2a$12$wuwUH4YO1ux9qi82rIjB9OOZxBCS69bdGkYFVxOrwR04Dud3sKBeG', utc_timestamp),
('LUQUE', 'luque.loja@gmail.com', '$2a$12$wuwUH4YO1ux9qi82rIjB9OOZxBCS69bdGkYFVxOrwR04Dud3sKBeG', utc_timestamp), 
('PEDRO AUGUSTO', 'pedrim.cad@gmail.com', '$2a$12$wuwUH4YO1ux9qi82rIjB9OOZxBCS69bdGkYFVxOrwR04Dud3sKBeG', utc_timestamp), 
('BRANCA', 'branca.aux@gmail.com', '$2a$12$wuwUH4YO1ux9qi82rIjB9OOZxBCS69bdGkYFVxOrwR04Dud3sKBeG', utc_timestamp),
('DEBORA', 'aws-teste+debora@gmail.com', '$2a$12$wuwUH4YO1ux9qi82rIjB9OOZxBCS69bdGkYFVxOrwR04Dud3sKBeG', utc_timestamp),
('JOAQUIM', 'aws-teste+joaquim@gmail.com', '$2a$12$wuwUH4YO1ux9qi82rIjB9OOZxBCS69bdGkYFVxOrwR04Dud3sKBeG', utc_timestamp),
('MARIA CECILIA', 'aws-teste+mariacecilia@gmail.com', '$2a$12$wuwUH4YO1ux9qi82rIjB9OOZxBCS69bdGkYFVxOrwR04Dud3sKBeG', utc_timestamp),
('ULISSES', 'aws-teste+ulisses@gmail.com', '$2a$12$wuwUH4YO1ux9qi82rIjB9OOZxBCS69bdGkYFVxOrwR04Dud3sKBeG', utc_timestamp);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3);

insert into usuario_grupo (usuario_id, grupo_id) values(1, 1), (1, 2), (3, 4);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values(1, 1), (1, 2), (2, 2), (3, 3);    

insert into pedido (codigo, subtotal, taxa_frete, valor_total, status, data_criacao, restaurante_id, usuario_cliente_id, forma_pagamento_id, 
endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
values ('bdd46d58-03fa-49c7-8484-80f6d62c56ca', 200.0, 15.2, 215.2, 'CRIADO', utc_timestamp, 2, 2, 2, 2, '38270000', 'RUA', '380', 'CENTRO');

insert into item_pedido(quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) values
(2, 100.0, 100.00, 'Muito Saboroso', 1, 2);  

insert into item_pedido(quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) values
(2, 100.0, 100.00, 'Ingredientes picantes', 1, 1);

insert into pedido (codigo, subtotal, taxa_frete, valor_total, status, data_criacao, restaurante_id, usuario_cliente_id, forma_pagamento_id, 
endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro)
values ('5e7588c7-2fed-42dd-984e-e2fddea76916', 200.0, 15.2, 215.2, 'CRIADO', utc_timestamp, 2, 1, 2, 2, '38270000', 'AVENIDA', '274', 'JUCA TEIXEIRA');

insert into item_pedido(quantidade, preco_unitario, preco_total, observacao, pedido_id, produto_id) values
(2, 100.0, 100.00, 'Muito Saboroso 2', 2, 1);  







