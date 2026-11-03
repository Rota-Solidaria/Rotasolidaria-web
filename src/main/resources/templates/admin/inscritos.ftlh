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
    <p class="nao-imprimir"><a href="/organizador">Voltar para as excursões</a></p>

    <div class="barra-admin">
      <div>
        <h1>${campanha.titulo}</h1>
        <p style="margin: 0;">${campanha.dataFormatada}, saída às ${campanha.horarioSaida}. De ${campanha.localSaida} para ${campanha.hemocentro}.</p>
      </div>
      <button class="btn nao-imprimir" type="button" onclick="window.print()">Imprimir lista</button>
    </div>

    <div class="resumo">
      <div><strong>${campanha.vagas}</strong>vagas no ônibus</div>
      <div><strong>${campanha.inscritos}</strong>inscritos</div>
      <div><strong>${canceladas}</strong>cancelados</div>
      <div><strong>${campanha.vagasRestantes}</strong>vagas livres</div>
    </div>

    <#if inscricoes?size == 0>
      <div class="alerta alerta-aviso">Ninguém se inscreveu nesta excursão ainda.</div>
    <#else>
      <div class="tabela-caixa">
        <table>
          <tr>
            <th>Nome</th>
            <th>Contato</th>
            <th>Cidade</th>
            <th>Observação</th>
            <th class="nao-imprimir">Organizar</th>
          </tr>
          <#list inscricoes as i>
            <tr>
              <td>
                <strong>${i.nome}</strong>
                <#if i.status == "CANCELADA"> <span class="status status-cancelada">Cancelada</span></#if>
                <br><small>Inscrito em ${i.dataInscricaoFormatada}</small>
              </td>
              <td>${i.telefone}<#if i.email?? && i.email != ""><br>${i.email}</#if></td>
              <td>${i.cidade}</td>
              <td>${i.observacao!}</td>
              <td class="nao-imprimir">
                <form method="post" action="/organizador/inscricao/status">
                  <input type="hidden" name="id" value="${i.id?c}">
                  <input type="hidden" name="campanhaId" value="${campanha.id?c}">
                  <#if i.status == "CANCELADA">
                    <input type="hidden" name="status" value="CONFIRMADA">
                    <button class="btn btn-pequeno" type="submit">Desfazer cancelamento</button>
                  <#else>
                    <input type="hidden" name="status" value="CANCELADA">
                    <button class="btn btn-pequeno btn-perigo" type="submit">Cancelar</button>
                  </#if>
                </form>
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
