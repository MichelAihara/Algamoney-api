CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo boolean NOT NULL,
	logradouro VARCHAR(80),
	numero VARCHAR(10),
	complemento VARCHAR(80),
	bairro VARCHAR(80),
	cep VARCHAR(10),
	cidade VARCHAR(40),
	estado VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade) 
values ('Michel', 1, 'Rua_Japao', 10, 'casa', 'parque_das_nacoes', 123456789, 'sao_paulo');