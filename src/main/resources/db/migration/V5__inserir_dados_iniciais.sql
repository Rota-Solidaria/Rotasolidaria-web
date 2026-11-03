-- Organizador para testar o login da área do organizador
INSERT INTO organizadores (email, senha)
VALUES ('admin@rotasolidaria.com', 'admin123');

-- Uma excursão de exemplo (a data fica sempre no futuro, a partir do dia em que o banco é criado)
INSERT INTO campanhas (titulo, descricao, data_campanha, horario_saida, local_saida, hemocentro, vagas, status)
VALUES
('Caravana Angatuba', 'Ônibus gratuito de ida e volta para os doadores de Angatuba',
 DATE_ADD(CURDATE(), INTERVAL 7 DAY), '04:30', 'Prefeitura Municipal de Angatuba', 'Colsan - Posto de Coleta Sorocaba', 40, 'ABERTA');

-- Duas inscrições de exemplo na excursão
INSERT INTO inscricoes (campanha_id, nome, telefone, email, cidade, observacao)
VALUES
(1, 'Andreza Damasceno', '(15) 99999-9999', 'andreza@email.com', 'Angatuba', ''),
(1, 'Gabriela Beato', '(15) 99999-0000', '', 'Angatuba', 'Primeira vez doando');
