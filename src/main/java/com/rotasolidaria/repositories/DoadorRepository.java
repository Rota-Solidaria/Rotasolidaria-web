package com.rotasolidaria.repositories;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rotasolidaria.models.Doador;

// Repositório da tabela "doadores" (pessoas com conta no site).
@Repository
public class DoadorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean existeEmail(String email) {
        String sql = "SELECT COUNT(*) FROM doadores WHERE email = ?";
        int total = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return total > 0;
    }

    public void salvar(Doador d, String senha) {
        String sql = "INSERT INTO doadores (nome, email, telefone, cidade, senha) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, d.getNome(), d.getEmail(), d.getTelefone(), d.getCidade(), gerarHash(senha));
    }

    public Doador login(String email, String senha) {
        String sql = "SELECT * FROM doadores WHERE email = ?";
        List<Map<String, Object>> linhas = jdbcTemplate.queryForList(sql, email);
        if (linhas.isEmpty()) {
            return null;
        }
        Map<String, Object> linha = linhas.get(0);
        if (!senhaConfere(senha, (String) linha.get("senha"))) {
            return null;
        }
        return deMap(linha);
    }

    // A senha é guardada como hash PBKDF2 com um sal aleatório, no formato sal:hash.
    // Dá para conferir a senha digitada, mas não dá para descobrir a original.
    private static final int ITERACOES = 120000;

    private String gerarHash(String senha) {
        byte[] sal = new byte[16];
        new SecureRandom().nextBytes(sal);
        byte[] hash = calcular(senha, sal);
        Base64.Encoder b64 = Base64.getEncoder();
        return b64.encodeToString(sal) + ":" + b64.encodeToString(hash);
    }

    private boolean senhaConfere(String senha, String guardado) {
        String[] partes = guardado.split(":");
        if (partes.length != 2) {
            return false;
        }
        byte[] sal = Base64.getDecoder().decode(partes[0]);
        byte[] esperado = Base64.getDecoder().decode(partes[1]);
        return MessageDigest.isEqual(esperado, calcular(senha, sal));
    }

    private byte[] calcular(String senha, byte[] sal) {
        try {
            PBEKeySpec spec = new PBEKeySpec(senha.toCharArray(), sal, ITERACOES, 256);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException("Não foi possível calcular o hash da senha", e);
        }
    }

    private Doador deMap(Map<String, Object> linha) {
        Doador d = new Doador();
        d.setId(((Number) linha.get("id")).longValue());
        d.setNome((String) linha.get("nome"));
        d.setEmail((String) linha.get("email"));
        d.setTelefone((String) linha.get("telefone"));
        d.setCidade((String) linha.get("cidade"));
        return d;
    }
}
