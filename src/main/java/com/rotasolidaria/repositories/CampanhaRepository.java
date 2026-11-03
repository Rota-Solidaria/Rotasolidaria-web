package com.rotasolidaria.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rotasolidaria.models.Campanha;

// Repositório da tabela "campanhas".
@Repository
public class CampanhaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Campanha> findAll() {
        String sql = "SELECT * FROM campanhas ORDER BY data_campanha DESC";
        return converterLista(jdbcTemplate.queryForList(sql));
    }

    public List<Campanha> findAbertas() {
        String sql = "SELECT * FROM campanhas WHERE status = 'ABERTA' AND data_campanha >= CURDATE() "
                + "ORDER BY data_campanha";
        return converterLista(jdbcTemplate.queryForList(sql));
    }

    public Campanha findById(Long id) {
        String sql = "SELECT * FROM campanhas WHERE id = ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, id);
        if (linhas.isEmpty()) {
            return null;
        }
        return deMap(linhas.get(0));
    }

    public void salvar(Campanha c) {
        String sql = "INSERT INTO campanhas (titulo, descricao, data_campanha, horario_saida, local_saida, "
                + "hemocentro, vagas, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, c.getTitulo(), c.getDescricao(), c.getData(), c.getHorarioSaida(),
                c.getLocalSaida(), c.getHemocentro(), c.getVagas(), c.getStatus());
    }

    public void atualizar(Campanha c) {
        String sql = "UPDATE campanhas SET titulo = ?, descricao = ?, data_campanha = ?, horario_saida = ?, "
                + "local_saida = ?, hemocentro = ?, vagas = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql, c.getTitulo(), c.getDescricao(), c.getData(), c.getHorarioSaida(),
                c.getLocalSaida(), c.getHemocentro(), c.getVagas(), c.getStatus(), c.getId());
    }

    public void alterarStatus(Long id, String status) {
        String sql = "UPDATE campanhas SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, id);
    }

    // As inscrições são apagadas junto (ON DELETE CASCADE)
    public void excluir(Long id) {
        String sql = "DELETE FROM campanhas WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private List<Campanha> converterLista(List<Map<String, Object>> linhas) {
        List<Campanha> campanhas = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            campanhas.add(deMap(linha));
        }
        return campanhas;
    }

    private Campanha deMap(Map<String, Object> linha) {
        Campanha c = new Campanha();
        c.setId(((Number) linha.get("id")).longValue());
        c.setTitulo((String) linha.get("titulo"));
        c.setDescricao((String) linha.get("descricao"));
        c.setData(linha.get("data_campanha").toString());
        // TIME chega como "07:00:00"; fica só "07:00"
        c.setHorarioSaida(linha.get("horario_saida").toString().substring(0, 5));
        c.setLocalSaida((String) linha.get("local_saida"));
        c.setHemocentro((String) linha.get("hemocentro"));
        c.setVagas(((Number) linha.get("vagas")).intValue());
        c.setStatus((String) linha.get("status"));
        return c;
    }
}
