-- Cada campanha é uma excursão de ônibus até um hemocentro
CREATE TABLE campanhas (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo        VARCHAR(140) NOT NULL,
    descricao     TEXT,
    data_campanha DATE         NOT NULL,
    horario_saida TIME         NOT NULL,
    local_saida   VARCHAR(200) NOT NULL,
    hemocentro    VARCHAR(200) NOT NULL,
    vagas         INT          NOT NULL,
    status        VARCHAR(20)  NOT NULL DEFAULT 'ABERTA'
);
