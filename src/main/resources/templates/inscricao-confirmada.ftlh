<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <title>Rota Solidária</title>
  <#include "head.ftlh">
</head>
<body>
<#include "header.ftlh">

<main class="container caixa-centro">
  <div class="cartao">
    <div class="alerta alerta-ok">Inscrição recebida!</div>
    <h2>Obrigado, ${inscricao.nome}!</h2>
    <p>Sua inscrição na <strong>${campanha.titulo}</strong> foi registrada e
       <strong>sua vaga já está garantida</strong>.
       Se precisar, o organizador entra em contato pelo telefone <strong>${inscricao.telefone}</strong>.</p>

    <ul class="info">
      <li><strong>Número da inscrição</strong>#${inscricao.id?c}</li>
      <li><strong>Data</strong>${campanha.dataFormatada}</li>
      <li><strong>Horário de saída</strong>${campanha.horarioSaida}</li>
      <li><strong>Local de saída</strong>${campanha.localSaida}</li>
      <li><strong>Destino</strong>${campanha.hemocentro}</li>
    </ul>

    <div class="botoes" style="margin-top: 18px;">
      <a class="btn btn-vermelho" href="/como-doar">Como me preparar</a>
      <a class="btn" href="/">Voltar ao início</a>
    </div>
  </div>
</main>

<#include "footer.ftlh">
</body>
</html>
