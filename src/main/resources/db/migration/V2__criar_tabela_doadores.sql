-- Doadores com conta no site (para ver e cancelar as próprias inscrições).
-- A senha é guardada como hash (PBKDF2), nunca em texto puro.
CREATE TABLE doadores (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(120) NOT NULL,
    email    VARCHAR(160) NOT NULL UNIQUE,
    telefone VARCHAR(20)  NOT NULL,
    cidade   VARCHAR(100) NOT NULL,
    senha    VARCHAR(200) NOT NULL
);
