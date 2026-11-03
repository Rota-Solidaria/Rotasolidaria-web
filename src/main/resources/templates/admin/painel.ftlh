<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <title>Rota Solidária</title>
  <#include "../head.ftlh">
</head>
<body>
<#include "../header.ftlh">

<main class="secao">
  <div class="container">
    <div class="barra-admin">
      <div>
        <h1>Excursões</h1>
      </div>
      <div class="botoes">
        <a class="btn btn-vermelho" href="/organizador/campanha/nova">+ Nova excursão</a>
        <a class="btn" href="/organizador/sair">Sair</a>
      </div>
    </div>

    <#if campanhas?size == 0>
      <div class="alerta alerta-aviso">Nenhuma excursão cadastrada ainda.</div>
    <#else>
      <div class="tabela-caixa">
        <table>
          <tr>
            <th>Excursão</th>
            <th>Data</th>
            <th>Inscritos</th>
            <th>Ações</th>
          </tr>
          <#list campanhas as c>
            <tr>
              <td>
                <strong>${c.titulo}</strong><br>
                De ${c.localSaida} para ${c.hemocentro}
              </td>
              <td>${c.dataFormatada}<br>${c.horarioSaida}</td>
              <td>${c.inscritos} / ${c.vagas}</td>
              <td>
                <div class="acoes">
                  <a class="btn btn-pequeno btn-vermelho" href="/organizador/inscritos?id=${c.id?c}">Ver inscritos</a>
                  <a class="btn btn-pequeno" href="/organizador/campanha/editar?id=${c.id?c}">Editar</a>

                  <form method="post" action="/organizador/campanha/status">
                    <input type="hidden" name="id" value="${c.id?c}">
                    <#if c.status == "ABERTA">
                      <input type="hidden" name="status" value="ENCERRADA">
                      <button class="btn btn-pequeno" type="submit">Encerrar inscrições</button>
                    <#else>
                      <input type="hidden" name="status" value="ABERTA">
                      <button class="btn btn-pequeno" type="submit">Reabrir</button>
                    </#if>
                  </form>

                  <form method="post" action="/organizador/campanha/excluir"
                        onsubmit="return confirm('Excluir esta excursão e todas as inscrições dela?');">
                    <input type="hidden" name="id" value="${c.id?c}">
                    <button class="btn btn-pequeno btn-perigo" type="submit">Excluir</button>
                  </form>
                </div>
              </td>
            </tr>
          </#list>
        </table>
      </div>
    </#if>
  </div>
</main>

<#include "../footer.ftlh">
</body>
</html>
