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
    <h2>Entrar</h2>
    <p>Entre na sua conta para ver e cancelar as suas inscrições.</p>

    <#if erro??>
      <div class="alerta alerta-erro">${erro}</div>
    </#if>

    <form class="formulario" method="post" action="/entrar">
      <label for="email">E-mail</label>
      <input id="email" name="email" type="email" value="${email!}" required>

      <label for="senha">Senha</label>
      <input id="senha" name="senha" type="password" required>

      <button class="btn btn-vermelho" type="submit">Entrar</button>
    </form>

    <p style="margin-top: 18px;">Ainda não tem conta? <a href="/cadastro">Criar conta</a></p>  </div>
</main>

<#include "footer.ftlh">
</body>
</html>
