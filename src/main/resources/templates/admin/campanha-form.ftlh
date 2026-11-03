<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <title>Rota Solidária</title>
  <#include "../head.ftlh">
</head>
<body>
<#include "../header.ftlh">

<main class="container" style="max-width: 720px; padding: 40px 0;">
  <a href="/organizador">Voltar para as excursões</a>

  <div class="cartao" style="margin-top: 14px;">
    <#if campanha.id == 0>
      <h1>Nova excursão</h1>
    <#else>
      <h1>Editar excursão</h1>
    </#if>

    <#if erro??>
      <div class="alerta alerta-erro">${erro}</div>
    </#if>

    <form class="formulario" method="post" action="/organizador/campanha/salvar">
      <#-- id = 0 significa "nova"; o controller decide entre INSERT e UPDATE -->
      <input type="hidden" name="id" value="${campanha.id?c}">

      <label for="titulo">Título *</label>
      <input id="titulo" name="titulo" value="${campanha.titulo!}" placeholder="Ex.: Excursão Angatuba" required>

      <label for="descricao">Descrição</label>
      <textarea id="descricao" name="descricao" rows="3">${campanha.descricao!}</textarea>

      <div class="linha">
        <div>
          <label for="data">Data *</label>
          <input id="data" name="data" type="date" value="${campanha.data!}" required>
        </div>
        <div>
          <label for="horarioSaida">Horário de saída *</label>
          <input id="horarioSaida" name="horarioSaida" type="time" value="${campanha.horarioSaida!}" required>
        </div>
      </div>

      <label for="localSaida">Local de saída do ônibus *</label>
      <input id="localSaida" name="localSaida" value="${campanha.localSaida!}" placeholder="Ex.: Praça da Matriz - Angatuba" required>

      <label for="hemocentro">Hemocentro (destino) *</label>
      <input id="hemocentro" name="hemocentro" value="${campanha.hemocentro!}" placeholder="Ex.: Hemonúcleo de Botucatu" required>

      <div class="linha">
        <div>
          <label for="vagas">Vagas no ônibus *</label>
          <input id="vagas" name="vagas" type="number" min="1" value="${campanha.vagas?c}" required>
        </div>
        <div>
          <label for="status">Inscrições</label>
          <select id="status" name="status">
            <option value="ABERTA" <#if campanha.status == "ABERTA">selected</#if>>Abertas</option>
            <option value="ENCERRADA" <#if campanha.status == "ENCERRADA">selected</#if>>Encerradas</option>
          </select>
        </div>
      </div>

      <button class="btn btn-vermelho" type="submit">Salvar excursão</button>
    </form>
  </div>
</main>

<#include "../footer.ftlh">
</body>
</html>
