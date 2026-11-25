<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <title>Excursões abertas – Rota Solidária</title>
  <#include "head.ftlh">
</head>
<body>
<#include "header.ftlh">

<section class="faixa">
  <div class="container">
    <h1>Excursões para doação de sangue</h1>
    <p>Transporte gratuito de ida e volta.</p>
  </div>
</section>

<main class="secao">
  <div class="container">
    <#if campanhas?size == 0>
      <div class="alerta alerta-aviso" role="status">Nenhuma excursão aberta no momento. Novas datas aparecem aqui assim que o organizador publicar. Enquanto isso, veja <a href="/como-doar">quem pode doar</a>.</div>
    <#else>
      <#-- Título só para leitores de tela: mantém a ordem h1 > h2 > h3 -->
      <h2 class="so-leitor">Excursões abertas</h2>
      <div class="grade">
        <#list campanhas as c>
          <div class="cartao">
            <#if c.aceitaInscricao>
              <span class="status status-aberta">Inscrições abertas</span>
            <#else>
              <span class="status status-lotada">Vagas esgotadas</span>
            </#if>
            <h3>${c.titulo}</h3>
            <p><strong>Data:</strong> ${c.dataFormatada}, saída às ${c.horarioSaida}</p>
            <p><strong>Saída:</strong> ${c.localSaida}</p>
            <p><strong>Destino:</strong> ${c.hemocentro}</p>
            <p><strong>Vagas restantes:</strong> ${c.vagasRestantes} de ${c.vagas}</p>
            <a class="btn btn-vermelho" href="/campanha?id=${c.id?c}">Ver e se inscrever</a>
          </div>
        </#list>
      </div>
    </#if>
  </div>
</main>

<#include "footer.ftlh">
</body>
</html>
