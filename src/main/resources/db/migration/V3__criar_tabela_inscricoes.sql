-- Pessoas inscritas em uma excursão. A inscrição já vale no momento em que é feita.
-- doador_id fica vazio quando a pessoa se inscreve sem login.
CREATE TABLE inscricoes (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    campanha_id    BIGINT       NOT NULL,
    doador_id      BIGINT       NULL,
    nome           VARCHAR(120) NOT NULL,
    telefone       VARCHAR(20)  NOT NULL,
    email          VARCHAR(160),
    cidade         VARCHAR(100) NOT NULL,
    observacao     TEXT,
    status         VARCHAR(20)  NOT NULL DEFAULT 'CONFIRMADA',
    data_inscricao DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (campanha_id) REFERENCES campanhas (id) ON DELETE CASCADE,
    FOREIGN KEY (doador_id)   REFERENCES doadores (id)  ON DELETE SET NULL
);
