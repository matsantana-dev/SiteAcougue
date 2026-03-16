---------- Deleta a tabela se ela já existir ----------

DROP TABLE IF EXISTS Cliente CASCADE;
DROP TABLE IF EXISTS Departamento CASCADE;
DROP TABLE IF EXISTS Produto CASCADE;
DROP TABLE IF EXISTS Venda CASCADE;
DROP TABLE IF EXISTS Item CASCADE;

---------- Criação das Tabelas ----------

CREATE TABLE Cliente (
codigo SERIAL PRIMARY KEY,
nome VARCHAR(100),
login VARCHAR(100) UNIQUE,
senha CHAR(32) );

CREATE TABLE Departamento(
codigo SERIAL PRIMARY KEY,
nome VARCHAR(50));

CREATE TABLE Produto (
codigo SERIAL PRIMARY KEY,
descricao VARCHAR(100),
preco FLOAT,
qtde INT CHECK(qtde >=0),
imagem VARCHAR(100),
coddep INT NOT NULL REFERENCES Departamento(codigo) ON UPDATE CASCADE);

CREATE TABLE Venda(
codigo SERIAL PRIMARY KEY,
total FLOAT DEFAULT 0,
datav TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
codcli INT NOT NULL REFERENCES Cliente(codigo) ON UPDATE CASCADE);

CREATE TABLE Item (
codigo SERIAL PRIMARY KEY,
qtde INT CHECK(qtde > 0),
precounit FLOAT,
codproduto INT NOT NULL REFERENCES Produto(codigo) ON UPDATE CASCADE,
codvenda INT NOT NULL REFERENCES Venda(codigo) ON UPDATE CASCADE);

---------- Inserções e Consultas de Valores ----------

----- Cliente -----

INSERT INTO Cliente(nome, login, senha) VALUES('Teste','a1','111');

----- Departamentos -----

INSERT INTO Departamento(nome) VALUES('Bovino');
INSERT INTO Departamento(nome) VALUES('Suíno');
INSERT INTO Departamento(nome) VALUES('Frango');

----- Produtos -----

--Bovino --

INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Picanha de 1Kg',57.99,13,'fig1.jpg',1);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Alcatra de 1Kg',45.99,15,'fig2.jpg',1);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Contra Filé de 1Kg',68.90,12,'fig3.jpg',1);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Maminha de 1Kg',42.99,25,'fig4.jpg',1);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Filé Mignon de 1Kg',83.99,10,'fig5.jpg',1);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Coxão Mole de 1Kg',39.99,20,'fig6.jpg',1);

-- Suino --

INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('1kg de Bacon',40.99,50,'fig7.jpg',2);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('1kg de Pata',12.99,39,'fig8.jpg',2);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('1Kg de Orelha',29.99,12,'fig9.jpg',2);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('1Kg de Banha',16.09,46,'fig10.jpg',2);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('1kg de Panceta',46.99,10,'fig11.jpg',2);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('1 Kg de Costelinha',29.11,10,'fig12.jpg',2);

-- Frango --

INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Frango Caipira Inteiro',22.26,30,'fig13.jpg',3);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Peito de 1Kg',7.29,28,'fig14.jpg',3);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Coxa de 1Kg',15.98,17,'fig15.jpg',3);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Sobrecoxa de 1Kg',15.99,18,'fig16.jpg',3);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Asa de 1Kg',27.99,14,'fig17.jpg',3);
INSERT INTO Produto (descricao, preco, qtde, imagem, coddep) 
VALUES('Pescoço de 1Kg',12.80,7,'fig18.jpg',3);

---------- AUX ----------

-- SELECT * FROM Cliente;
-- SELECT * FROM Departamento;
-- SELECT * FROM Produto;
-- SELECT * FROM Item;
-- SELECT * FROM Venda;