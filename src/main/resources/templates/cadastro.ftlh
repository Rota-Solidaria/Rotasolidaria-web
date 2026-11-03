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
    <h2>Criar conta</h2>
    <p>Com a conta, seus dados já vêm preenchidos nas próximas inscrições e você acompanha tudo em um só lugar.</p>

    <#if erro??>
      <div class="alerta alerta-erro">${erro}</div>
    </#if>

    <form class="formulario" method="post" action="/cadastro">
      <label for="nome">Nome completo *</label>
      <input id="nome" name="nome" value="${nome!}" required>

      <label for="email">E-mail *</label>
      <input id="email" name="email" type="email" value="${email!}" required>

      <label for="telefone">Telefone / WhatsApp *</label>
      <input id="telefone" name="telefone" value="${telefone!}" placeholder="(15) 99999-9999" required>

      <label for="cidade">Cidade *</label>
      <input id="cidade" name="cidade" value="${cidade!}" required>

      <label for="senha">Senha *</label>
      <input id="senha" name="senha" type="password" minlength="6" required>
      <p class="ajuda">Pelo menos 6 caracteres.</p>

      <label for="confirmarSenha">Repita a senha *</label>
      <input id="confirmarSenha" name="confirmarSenha" type="password" minlength="6" required>

      <button class="btn btn-vermelho" type="submit">Criar conta</button>
    </form>

    <p style="margin-top: 18px;">Já tem conta? <a href="/entrar">Entrar</a></p>
  </div>
</main>

<#include "footer.ftlh">
</body>
</html>
