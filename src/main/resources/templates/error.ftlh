<#-- Página de erro do site (o Spring Boot usa este template no lugar da "Whitelabel Error Page").
     "status" vem do Spring: 404 = página não encontrada; os outros são problemas no endereço ou no servidor. -->
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <title><#if status?? && status?c == "404">Página não encontrada<#else>Não foi possível abrir a página</#if> – Rota Solidária</title>
  <#include "head.ftlh">
</head>
<body>
<#include "header.ftlh">

<main class="container caixa-centro">
  <div class="cartao">
    <#if status?? && status?c == "404">
      <h1>Não encontramos esta página</h1>
      <p>O endereço pode ter sido digitado errado ou a excursão pode ter sido removida.</p>
    <#else>
      <h1>Não conseguimos abrir esta página</h1>
      <p>Algo deu errado do nosso lado ou no endereço do link. Seus dados não foram perdidos: volte e tente de novo.</p>
    </#if>
    <div class="botoes" style="margin-top: 18px;">
      <a class="btn btn-vermelho" href="/campanhas">Ver excursões abertas</a>
      <a class="btn" href="/">Voltar ao início</a>
    </div>
  </div>
</main>

<#include "footer.ftlh">
</body>
</html>
