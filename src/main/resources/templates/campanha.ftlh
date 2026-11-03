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
    <h1>${campanha.titulo}</h1>
    <p>${campanha.dataFormatada}, saída às ${campanha.horarioSaida}</p>
  </div>
</section>

<main class="secao">
  <div class="container duas-colunas">

    <div class="cartao">
      <h2>Sobre a excursão</h2>
      <#if campanha.descricao?has_content><p>${campanha.descricao}</p></#if>
      <ul class="info">
        <li><strong>Data</strong>${campanha.dataFormatada}</li>
        <li><strong>Horário de saída</strong>${campanha.horarioSaida}</li>
        <li><strong>Local de saída do ônibus</strong>${campanha.localSaida}</li>
        <li><strong>Hemocentro (destino)</strong>${campanha.hemocentro}</li>
        <li><strong>Vagas restantes</strong>${campanha.vagasRestantes} de ${campanha.vagas}</li>
      </ul>
    </div>

    <div class="cartao">
      <h2>Quero participar</h2>

      <#if campanha.aceitaInscricao>
        <#if erro??>
          <div class="alerta alerta-erro">${erro}</div>
        </#if>

        <#if doador??>
          <p class="ajuda">Você está na sua conta: a inscrição vai aparecer em <a href="/minhas-inscricoes">Minhas inscrições</a>.</p>
        <#else>
          <p class="ajuda">Tem conta? <a href="/entrar">Entre</a> para seus dados virem preenchidos e acompanhar a inscrição depois.</p>
        </#if>

        <form class="formulario" method="post" action="/inscrever">
          <input type="hidden" name="campanhaId" value="${campanha.id?c}">

          <label for="nome">Nome completo *</label>
          <input id="nome" name="nome" value="${nome!}" required>

          <div class="linha">
            <div>
              <label for="telefone">Telefone / WhatsApp *</label>
              <input id="telefone" name="telefone" value="${telefone!}" placeholder="(15) 99999-9999" required>
            </div>
            <div>
              <label for="email">E-mail (opcional)</label>
              <input id="email" name="email" type="email" value="${email!}">
            </div>
          </div>

          <label for="cidade">Cidade de onde você sai *</label>
          <input id="cidade" name="cidade" value="${cidade!}" required>

          <label for="observacao">Observação (opcional)</label>
          <textarea id="observacao" name="observacao" rows="3">${observacao!}</textarea>

          <button class="btn btn-vermelho" type="submit">Enviar inscrição</button>
        </form>
      <#else>
        <div class="alerta alerta-aviso">
          Essa excursão não está aceitando inscrições (vagas esgotadas ou inscrições encerradas).
        </div>
        <a class="btn" href="/campanhas">Ver outras excursões</a>
      </#if>
    </div>

  </div>
</main>

<#include "footer.ftlh">
</body>
</html>
