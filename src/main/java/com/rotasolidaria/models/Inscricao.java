package com.rotasolidaria.models;

// Uma inscrição é uma pessoa que vai na excursão.
public class Inscricao {

    private Long id;
    private Long campanhaId;
    private String nome;
    private String telefone;
    private String email;
    private String cidade;
    private String observacao;
    private String status;         // CONFIRMADA ou CANCELADA
    private String dataInscricao;  // 2026-10-31 18:40:00
    private Long doadorId;         // null quando a pessoa se inscreveu sem conta

    // Não é coluna da tabela: usado na tela "Minhas inscrições".
    private Campanha campanha;

    public Inscricao() {
    }

    public String getDataInscricaoFormatada() {
        if (dataInscricao == null || dataInscricao.length() < 10) {
            return "";
        }
        String ano = dataInscricao.substring(0, 4);
        String mes = dataInscricao.substring(5, 7);
        String dia = dataInscricao.substring(8, 10);
        return dia + "/" + mes + "/" + ano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampanhaId() {
        return campanhaId;
    }

    public void setCampanhaId(Long campanhaId) {
        this.campanhaId = campanhaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(String dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Long getDoadorId() {
        return doadorId;
    }

    public void setDoadorId(Long doadorId) {
        this.doadorId = doadorId;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }
}
