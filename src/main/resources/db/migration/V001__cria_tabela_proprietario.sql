CREATE TABLE proprietario (
id bigint not null auto_increment,
nome varchar(80) not null,
email varchar(255) not null,
telefone varchar(20) not null,

PRIMARY KEY(id),

CONSTRAINT uk_proprietario unique(email) -- O email será único, não pode ter emails repetidos na base de dados
);