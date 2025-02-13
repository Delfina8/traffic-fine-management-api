create TABLE veiculo(
id bigint not null AUTO_INCREMENT,
proprietario_id bigint not null,
marca VARCHAR(20) not null,
modelo VARCHAR(20) not null,
placa VARCHAR(7) not null,
status VARCHAR(7) not null,
data_cadastro datetime not null,
data_apreensao datetime,

primary key(id)
);

ALTER TABLE veiculo add constraint fk_veiculo_proprietario FOREIGN KEY (proprietario_id) references proprietario (id);

ALTER TABLE veiculo add CONSTRAINT uk_veiculo UNIQUE (placa);