CREATE TABLE autuacao (
id bigint not null auto_increment,
veiculo_id bigint not null,
descricao text not null,
valor_multa decimal (10,2) not null,
data_ocorrencia datetime not null,

PRIMARY KEY (id),

CONSTRAINT fk_autuacao_veiculo FOREIGN KEY(veiculo_id) REFERENCES veiculo (id)
);
