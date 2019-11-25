drop database if exists boutique;
create database boutique;
use boutique;

CREATE TABLE IF NOT EXISTS cliente(
   cpf VARCHAR(15) primary key not NULL,
   nome VARCHAR(45) NOT NULL,
   sexo varchar(15),
   email varchar(45),
   cidade varchar(45),
   rua varchar(45),
   num int,
   estado varchar(45),
   tel int
   
);
CREATE TABLE IF NOT EXISTS empresa(
   e_cnpj VARCHAR(15) primary key not NULL,
   nome VARCHAR(45) NOT NULL,
   email varchar(45),
   cidade varchar(45),
   rua varchar(45),
   num int,
   estado varchar(45),
   tel int
 
);

CREATE TABLE IF NOT EXISTS funcionario(
   cpf VARCHAR(15) PRIMARY KEY NOT NULL,
   nome VARCHAR(45) NOT NULL,
   sexo VARCHAR(15),
   email VARCHAR(45),
   cidade VARCHAR(45),
   rua VARCHAR(45),
   num INT,
   estado VARCHAR(45),
   tel INT   
);

CREATE TABLE IF NOT EXISTS produto(
   codigo int primary key not NULL,
   tipo varchar(15),
   marca varchar(20),
   quant int,
   moda varchar(45),
   precoCusto int,
   precoVenda int,
   cnpj varchar(45),
   constraint fk_cnpj_produto
   foreign key (cnpj)
   references empresa(e_cnpj)
   ON DELETE NO ACTION
   ON UPDATE NO ACTION
   
);

CREATE TABLE IF NOT EXISTS cliente_has_produto(
	Cliente_cpf int not null,
    Produto_id_produto int not null
);
