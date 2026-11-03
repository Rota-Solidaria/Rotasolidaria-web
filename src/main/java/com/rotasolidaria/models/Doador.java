package com.rotasolidaria.models;

// Pessoa com conta no site. A senha não fica aqui, só (como hash) no banco.
public class Doador {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cidade;

    public Doador() {
    }

    public String getPrimeiroNome() {
        if (nome == null) {
            return "";
        }
        return nome.trim().split(" ")[0];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
