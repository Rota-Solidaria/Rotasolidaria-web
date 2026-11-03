-- Quem pode entrar na área do organizador.
CREATE TABLE organizadores (
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(160) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);
