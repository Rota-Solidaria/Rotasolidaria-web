<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <title>Rota Solidária</title>
  <#include "head.ftlh">
</head>
<body>
<#include "header.ftlh">

<section class="faixa">
  <div class="container">
    <h1>Olá, ${doador.primeiroNome}!</h1>
  </div>
</section>

<main class="secao">
  <div class="container">
    <div class="botoes" style="margin-bottom: 24px;">
      <a class="btn btn-vermelho" href="/campanhas">Ver excursões abertas</a>
      <a class="btn" href="/sair">Sair da conta</a>
    </div>

    <#if inscricoes?size == 0>
      <div class="alerta alerta-aviso">Você ainda não se inscreveu em nenhuma excursão.</div>
    <#else>
      <div class="grade">
        <#list inscricoes as i>
          <div class="cartao">
            <#if i.status == "CANCELADA">
              <span class="status status-cancelada">Cancelada</span>
            <#else>
              <span class="status status-confirmada">Vaga garantida</span>
            </#if>
            <h3>${i.campanha.titulo}</h3>
            <p><strong>Data:</strong> ${i.campanha.dataFormatada}, saída às ${i.campanha.horarioSaida}</p>
            <p><strong>Saída:</strong> ${i.campanha.localSaida}</p>
            <p><strong>Destino:</strong> ${i.campanha.hemocentro}</p>
            <p><small>Inscrição #${i.id?c}, feita em ${i.dataInscricaoFormatada}</small></p>

            <#if i.status != "CANCELADA">
              <form method="post" action="/minhas-inscricoes/cancelar"
                    onsubmit="return confirm('Quer mesmo cancelar sua inscrição nesta excursão?');">
                <input type="hidden" name="id" value="${i.id?c}">
                <button class="btn btn-pequeno btn-perigo" type="submit">Cancelar inscrição</button>
              </form>
            </#if>
          </div>
        </#list>
      </div>
    </#if>
  </div>
</main>

<#include "footer.ftlh">
</body>
</html>
