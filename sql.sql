CREATE DATABASE IF NOT EXISTS loja_cds;
USE loja_cds;

CREATE TABLE IF NOT EXISTS cds (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    artista VARCHAR(100) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    ano INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    estoque INT NOT NULL
);

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(100),
    telefone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    cargo VARCHAR(50) NOT NULL,
    salario DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    id_funcionario INT NOT NULL,
    permissoes VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)
);


DROP TABLE IF EXISTS vendas_itens;
DROP TABLE IF EXISTS vendas_header; 

CREATE TABLE IF NOT EXISTS vendas_header (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_cliente INT NULL, 
    id_funcionario INT NOT NULL,
    total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    qnt_itens INT NOT NULL DEFAULT 0, 
    FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE SET NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)
);

CREATE TABLE IF NOT EXISTS vendas_itens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venda INT NOT NULL,
    id_cd INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    subtotal DECIMAL(10,2) NOT NULL, 
    FOREIGN KEY (id_venda) REFERENCES vendas_header(id) ON DELETE CASCADE,
    FOREIGN KEY (id_cd) REFERENCES cds(id)
);


DELIMITER //
CREATE TRIGGER trg_vendas_itens_after_insert
AFTER INSERT ON vendas_itens
FOR EACH ROW
BEGIN
    UPDATE vendas_header
    SET total = total + NEW.subtotal,
        qnt_itens = qnt_itens + NEW.quantidade
    WHERE id = NEW.id_venda;
END //
DELIMITER ;


DELIMITER //
CREATE TRIGGER trg_vendas_itens_after_delete
AFTER DELETE ON vendas_itens
FOR EACH ROW
BEGIN
    UPDATE vendas_header
    SET total = total - OLD.subtotal,
        qnt_itens = qnt_itens - OLD.quantidade
    WHERE id = OLD.id_venda;
END //
DELIMITER ;


DELIMITER //
CREATE TRIGGER trg_vendas_itens_after_update
AFTER UPDATE ON vendas_itens
FOR EACH ROW
BEGIN
    UPDATE vendas_header
    SET total = total - OLD.subtotal + NEW.subtotal,
        qnt_itens = qnt_itens - OLD.quantidade + NEW.quantidade
    WHERE id = NEW.id_venda;
END //
DELIMITER ;


INSERT INTO cds (titulo, artista, genero, ano, preco, estoque) VALUES 
('Testes', 'AB', 'Rock', 2006, 15.30, 17),
('Saas', 'AB', 'Rock', 2002, 15.60, 1);

INSERT INTO clientes (nome, cpf, email, telefone) VALUES 
('Cliente1', '987.654.321-00', 'email11@gmail.com', '(11)9999-9999');

INSERT INTO funcionarios (nome, cpf, cargo, salario) VALUES 
('Func1', '987.654.321-00', 'Vendedor', 2500.00);

INSERT INTO funcionarios (nome, cpf, cargo, salario) VALUES 
('Func2', '987.654.321-01', 'Vendedor', 2600.00);

INSERT INTO usuarios (username, senha, id_funcionario, permissoes) VALUES 
('Gerente', '1', 1, 'gerente');

INSERT INTO usuarios (username, senha, id_funcionario, permissoes) VALUES 
('Atendente', '2', 2, 'Atendente');


INSERT INTO vendas_header (data_hora, id_cliente, id_funcionario) VALUES 
('2025-09-12 20:11:00', 1, 1);

INSERT INTO vendas_itens (id_venda, id_cd, quantidade, subtotal) VALUES 
(1, 1, 1, 15.30),
(1, 2, 1, 15.60);