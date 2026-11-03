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
    <h1>Guia do doador de sangue</h1>
    <p>O que você precisa saber antes de doar.</p>
  </div>
</section>

<main>
  <section class="secao">
    <div class="container">
      <h2>Quem pode doar?</h2>
      <#-- Fonte: Fundação Pró-Sangue
           https://www.portal.prosangue.sp.gov.br/fps/doacao_de_sangue__/requisitos_para_doacao___ -->
      <div class="tabela-caixa">
        <table>
          <tr><th>Requisito</th><th>Precisa</th></tr>
          <tr><td>Idade</td><td>16 a 69 anos</td></tr>
          <tr><td>Peso</td><td>50 kg ou mais</td></tr>
          <tr><td>Sono</td><td>5 horas ou mais</td></tr>
          <tr><td>Alimentação</td><td>Estar alimentado</td></tr>
          <tr><td>Documento</td><td>RG ou CNH com foto</td></tr>
        </table>
      </div>
    </div>
  </section>

  <section class="secao secao-cinza">
    <div class="container">
      <h2>De quanto em quanto tempo posso doar?</h2>
      <p class="subtitulo">Homens: 60 dias. Mulheres: 90 dias.</p>
      <a class="btn btn-vermelho" href="/campanhas">Ver excursões abertas</a>
    </div>
  </section>

  <section class="secao">
    <div class="container">
      <h2>Quando é preciso esperar?</h2>
      <div class="tabela-caixa">
        <table>
          <tr><th>Situação</th><th>Esperar</th></tr>
          <tr><td>Bebida alcoólica</td><td>12 horas</td></tr>
          <tr><td>Resfriado</td><td>3 dias</td></tr>
          <tr><td>Tatuagem ou piercing</td><td>7 dias</td></tr>
          <tr><td>Vacinas</td><td>48 horas a 4 semanas</td></tr>
          <tr><td>Dentista</td><td>7 dias</td></tr>
          <tr><td>Cirurgia</td><td>4 semanas</td></tr>
          <tr><td>Gravidez e parto</td><td>Até 180 dias após o parto</td></tr>
          <tr><td>Viagem ao exterior</td><td>30 dias</td></tr>
          <tr><td>Covid-19 ou dengue</td><td>10 dias a 4 semanas</td></tr>
        </table>
      </div>
      <p class="subtitulo" style="margin-top: 24px;">Veja mais no
        <a href="https://www.portal.prosangue.sp.gov.br/fps/doacao_de_sangue__/requisitos_para_doacao___" target="_blank" rel="noopener">site da Pró-Sangue</a>.</p>
    </div>
  </section>
</main>

<#include "footer.ftlh">
</body>
</html>
