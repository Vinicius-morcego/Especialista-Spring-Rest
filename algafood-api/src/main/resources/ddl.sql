create table forma_pagamento (
id bigint not null auto_increment, 
descricao varchar(255), 
primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo (
id bigint not null auto_increment, 
nome varchar(255), 
primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo_permissao (
grupo_id bigint not null, 
permissao_id bigint not null
) engine=InnoDB default charset=utf8;

create table permissao (
id bigint not null auto_increment, 
descricao varchar(255), 
nome varchar(255), primary key (id)
) engine=InnoDB default charset=utf8;

create table produto (
id bigint not null auto_increment, 
ativo bit not null, 
descricao varchar(255) not null, 
nome varchar(255) not null, 
preco decimal(19,2) not null, 
restaurante_id bigint, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante (
id bigint not null auto_increment, 
data_atualizacao datetime not null, 
data_cadastro datetime not null, 
endereco_bairro varchar(255), 
endereco_cep varchar(255), 
endereco_complemento varchar(255), 
endereco_logradouro varchar(255), 
endereco_numero varchar(255), 
nome varchar(255), 
taxa_frete decimal(19,2) not null, 
cozinha_id bigint not null, 
endereco_cidade_id bigint, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante_forma_pagamento (
restaurante_id bigint not null, 
forma_pagamento_id bigint not null
) engine=InnoDB default charset=utf8;

create table usuario (
id bigint not null auto_increment, 
data_cadastro datetime not null, 
email_usuario varchar(255), 
nome_usuario varchar(255), 
senha_usuario varchar(255), 
primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario_grupo (
usuario_id bigint not null, 
grupo_id bigint not null
) engine=InnoDB default charset=utf8;

alter table grupo_permissao add constraint FK_permissao_grupo foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint FKt_grupo_permissao foreign key (grupo_id) references grupo (id);

alter table produto add constraint FK_restaurante_produto foreign key (restaurante_id) references restaurante (id);

alter table restaurante add constraint FK_cozinha_restaurante foreign key (cozinha_id) references cozinha (id);

alter table restaurante add constraint FK_cidade_restaurante foreign key (endereco_cidade_id) references cidade (id);

alter table restaurante_forma_pagamento add constraint FK_forma_pagamento_restaurante foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint FK_restaurante_forma_pagamento foreign key (restaurante_id) references restaurante (id);

alter table usuario_grupo add constraint FK_grupo_usuario foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint FK_usuario_grupo foreign key (usuario_id) references usuario (id);