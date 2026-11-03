package com.rotasolidaria.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rotasolidaria.models.Inscricao;

// Repositório da tabela "inscricoes".
@Repository
public class InscricaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Inscricao> findByCampanha(Long campanhaId) {
        String sql = "SELECT * FROM inscricoes WHERE campanha_id = ? ORDER BY cidade, nome";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, campanhaId);
        List<Inscricao> inscricoes = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            inscricoes.add(deMap(linha));
        }
        return inscricoes;
    }

    public List<Inscricao> findByDoador(Long doadorId) {
        String sql = "SELECT * FROM inscricoes WHERE doador_id = ? ORDER BY data_inscricao DESC";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, doadorId);
        List<Inscricao> inscricoes = new ArrayList<>();
        for (Map<String, Object> linha : linhas) {
            inscricoes.add(deMap(linha));
        }
        return inscricoes;
    }

    public Inscricao findById(Long id) {
        String sql = "SELECT * FROM inscricoes WHERE id = ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, id);
        if (linhas.isEmpty()) {
            return null;
        }
        return deMap(linhas.get(0));
    }

    // As canceladas não ocupam vaga
    public int contarPorCampanha(Long campanhaId) {
        String sql = "SELECT COUNT(*) FROM inscricoes WHERE campanha_id = ? AND status <> 'CANCELADA'";
        return jdbcTemplate.queryForObject(sql, Integer.class, campanhaId);
    }

    public boolean existeTelefone(Long campanhaId, String telefone) {
        String sql = "SELECT COUNT(*) FROM inscricoes WHERE campanha_id = ? AND telefone = ? "
                + "AND status <> 'CANCELADA'";
        int total = jdbcTemplate.queryForObject(sql, Integer.class, campanhaId, telefone);
        return total > 0;
    }

    public void salvar(Inscricao i) {
        String sql = "INSERT INTO inscricoes (campanha_id, nome, telefone, email, cidade, "
                + "observacao, doador_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'CONFIRMADA')";
        jdbcTemplate.update(sql, i.getCampanhaId(), i.getNome(), i.getTelefone(), i.getEmail(),
                i.getCidade(), i.getObservacao(), i.getDoadorId());
    }

    public Long ultimoIdDoTelefone(Long campanhaId, String telefone) {
        String sql = "SELECT MAX(id) FROM inscricoes WHERE campanha_id = ? AND telefone = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, campanhaId, telefone);
    }

    public void alterarStatus(Long id, String status) {
        String sql = "UPDATE inscricoes SET status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, id);
    }

    private Inscricao deMap(Map<String, Object> linha) {
        Inscricao i = new Inscricao();
        i.setId(((Number) linha.get("id")).longValue());
        i.setCampanhaId(((Number) linha.get("campanha_id")).longValue());
        i.setNome((String) linha.get("nome"));
        i.setTelefone((String) linha.get("telefone"));
        i.setEmail((String) linha.get("email"));
        i.setCidade((String) linha.get("cidade"));
        i.setObservacao((String) linha.get("observacao"));
        i.setStatus((String) linha.get("status"));
        i.setDataInscricao(linha.get("data_inscricao").toString());
        if (linha.get("doador_id") != null) {
            i.setDoadorId(((Number) linha.get("doador_id")).longValue());
        }
        return i;
    }
}
