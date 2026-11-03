<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <title>Rota Solidária</title>
  <#include "head.ftlh">
</head>
<body>
<#include "header.ftlh">

<main>
  <section class="hero">
    <div class="container hero-grade">
      <div class="hero-texto">
        <h1>O hemocentro fica longe? O ônibus leva você.</h1>
        <p>Excursões gratuitas que saem da sua cidade, pertinho de você. É fácil: escolha a data e reserve sua vaga.</p>
        <a class="btn btn-vermelho btn-grande" href="/campanhas">Ver excursões perto de mim</a>
        <a class="link-discreto" href="#como-funciona">Como funciona</a>
      </div>
      <div class="hero-foto">
        <img src="/images/doacao-coracao.webp" alt="Pessoa doando sangue apertando uma bolinha vermelha em forma de coração">
        <span class="hero-selo">Ida e volta<br><strong>de graça</strong></span>
      </div>
    </div>
  </section>

  <section class="secao secao-cinza" id="como-funciona">
    <div class="container">
      <h2>Três passos simples</h2>
      <p class="subtitulo">Você só precisa se inscrever. A organização da viagem fica com a gente.</p>
      <div class="grade">
        <div class="cartao">
          <h3>Escolha a excursão</h3>
          <p>Veja as datas, o local de saída do ônibus e o hemocentro de destino.</p>
        </div>
        <div class="cartao">
          <h3>Faça sua inscrição</h3>
          <p>Preencha nome, telefone e a cidade de onde você sai. Não precisa criar conta.</p>
        </div>
        <div class="cartao">
          <h3>Pronto, vaga garantida</h3>
          <p>Sua inscrição já vale. É só estar no ponto de saída no dia e horário marcados.</p>
        </div>
      </div>
    </div>
  </section>

  <section class="secao">
    <div class="container centro">
      <h2>Doações</h2>
      <div class="grade depoimentos">
        <figure class="depoimento">
          <div class="depoimento-foto" style="background-image: url('/images/depoimentos/1.jpg');" role="img" aria-label="Braço de um doador apoiado na cadeira, segurando uma bolinha vermelha, com a bolsa de sangue enchendo"></div>
        </figure>
        <figure class="depoimento">
          <div class="depoimento-foto" style="background-image: url('/images/depoimentos/2.jpg');" role="img" aria-label="Mão apertando uma bolinha vermelha durante a doação de sangue"></div>
        </figure>
        <figure class="depoimento">
          <div class="depoimento-foto" style="background-image: url('/images/depoimentos/3.png');" role="img" aria-label="Doador sentado na cadeira enquanto um profissional identifica a bolsa de sangue"></div>
        </figure>
      </div>
    </div>
  </section>

  <section class="secao secao-cinza">
    <div class="container">
      <h2>Próximas excursões</h2>

      <#if campanhas?size == 0>
        <p>Nenhuma excursão aberta no momento.</p>
      <#else>
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
              <p><strong>Vagas restantes:</strong> ${c.vagasRestantes}</p>
              <a class="btn btn-vermelho" href="/campanha?id=${c.id?c}">Ver e se inscrever</a>
            </div>
          </#list>
        </div>
      </#if>

      <p style="margin-top: 24px;"><a href="/campanhas">Ver todas as excursões</a></p>
    </div>
  </section>
</main>

<#include "footer.ftlh">
</body>
</html>
