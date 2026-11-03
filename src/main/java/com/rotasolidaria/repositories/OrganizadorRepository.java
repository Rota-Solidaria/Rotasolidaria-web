package com.rotasolidaria.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// Repositório da tabela "organizadores" (quem pode entrar na área do organizador).
@Repository
public class OrganizadorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Senha em texto puro: aceitável só em ambiente de estudo.
    public boolean loginValido(String email, String senha) {
        String sql = "SELECT COUNT(*) FROM organizadores WHERE email = ? AND senha = ?";
        int total = jdbcTemplate.queryForObject(sql, Integer.class, email, senha);
        return total > 0;
    }
}
