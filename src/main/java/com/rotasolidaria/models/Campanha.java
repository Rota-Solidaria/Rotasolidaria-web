package com.rotasolidaria.models;

// Uma campanha é uma excursão (ônibus) que leva os doadores até o hemocentro.
public class Campanha {

    private Long id;
    private String titulo;
    private String descricao;
    private String data;          // 2026-11-20
    private String horarioSaida;  // 07:00
    private String localSaida;
    private String hemocentro;
    private int vagas;
    private String status;        // ABERTA ou ENCERRADA

    // Não é coluna da tabela: o controller preenche contando as inscrições.
    private int inscritos;

    public Campanha() {
    }

    public String getDataFormatada() {
        if (data == null || data.length() < 10) {
            return "";
        }
        String ano = data.substring(0, 4);
        String mes = data.substring(5, 7);
        String dia = data.substring(8, 10);
        return dia + "/" + mes + "/" + ano;
    }

    public int getVagasRestantes() {
        int restantes = vagas - inscritos;
        if (restantes < 0) {
            return 0;
        }
        return restantes;
    }

    public boolean isAceitaInscricao() {
        return "ABERTA".equals(status) && getVagasRestantes() > 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(String horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public String getLocalSaida() {
        return localSaida;
    }

    public void setLocalSaida(String localSaida) {
        this.localSaida = localSaida;
    }

    public String getHemocentro() {
        return hemocentro;
    }

    public void setHemocentro(String hemocentro) {
        this.hemocentro = hemocentro;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }
}
