DROP DATABASE IF EXISTS DBESTAMPARIA;
CREATE DATABASE DBESTAMPARIA;
USE DBESTAMPARIA;

CREATE TABLE `DBESTAMPARIA`.`CLIENTE` (
`ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NOT NULL,
  `CPF` VARCHAR(15) NOT NULL,
  `EMAIL` VARCHAR(255) NOT NULL,
  `SENHA` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`));
  
  CREATE TABLE `DBESTAMPARIA`.`FUNCIONARIO`(
	`ID` INT NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NOT NULL,
  `CPF` VARCHAR(15) NOT NULL,
  `EMAIL` VARCHAR(255) NOT NULL,
  `SENHA` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`));
  
  CREATE TABLE `DBESTAMPARIA`.`CAMISA`(
	`ID` INT NOT NULL AUTO_INCREMENT,
    `TAMANHO` CHAR(4) NOT NULL,
    `COR` VARCHAR(20) NOT NULL,
    `ESTAMPA` blob NOT NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `DBESTAMPARIA`.`PEDIDO`(
	`ID` INT NOT NULL AUTO_INCREMENT,
    `STATUS_PEDIDO` VARCHAR(20) NOT NULL,
    `ID_CLIENTE` INT NULL,
    `ID_CAMISA` INT NULL,
    PRIMARY KEY (`ID`),
    CONSTRAINT ID_CAMISA
    FOREIGN KEY (ID_CAMISA)
    REFERENCES DBESTAMPARIA.CAMISA (id),
	CONSTRAINT ID_CLIENTE
    FOREIGN KEY (ID_CLIENTE)
    REFERENCES DBESTAMPARIA.CLIENTE (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
SELECT * FROM CLIENTE;