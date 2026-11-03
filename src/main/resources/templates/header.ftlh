<#-- "pagina" (enviada pelo controller) marca o link ativo do menu -->
<header class="topo">
  <div class="container">
    <a class="logo" href="/">
      <img class="logo-claro" src="/images/logo.png" alt="Rota Solidária">
      <img class="logo-escuro" src="/images/logo-escuro.png" alt="Rota Solidária">
    </a>
    <nav class="menu">
      <a href="/" <#if pagina?? && pagina == "inicio">class="ativo"</#if>>Início</a>
      <a href="/campanhas" <#if pagina?? && pagina == "campanhas">class="ativo"</#if>>Excursões</a>
      <a href="/como-doar" <#if pagina?? && pagina == "como-doar">class="ativo"</#if>>Como doar</a>
      <#-- "organizador" e "doador" vêm da sessão: só existem depois do login -->
      <#if organizador??>
        <a href="/organizador" <#if pagina?? && pagina == "organizador">class="ativo"</#if>>Painel</a>
      <#elseif doador??>
        <a href="/minhas-inscricoes" <#if pagina?? && pagina == "conta">class="ativo"</#if>>Minhas inscrições</a>
      <#else>
        <a href="/entrar" <#if pagina?? && pagina == "conta">class="ativo"</#if>>Entrar</a>
      </#if>
    </nav>

    <#-- Painel de acessibilidade (ver /js/acessibilidade.js) -->
    <div class="acess">
      <button class="acess-botao" type="button" aria-expanded="false" aria-controls="acess-painel" aria-label="Acessibilidade">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">
          <circle cx="12" cy="12" r="10"/>
          <path d="M3 7.5Q12 13 21 7.5M7 20.7L12 10.25L17 20.7"/>
          <g fill="currentColor" stroke="none">
            <circle cx="12" cy="5.8" r="2"/>
            <circle cx="3" cy="7.5" r="1.2"/>
            <circle cx="21" cy="7.5" r="1.2"/>
            <circle cx="7" cy="20.7" r="1.2"/>
            <circle cx="17" cy="20.7" r="1.2"/>
          </g>
        </svg>
      </button>
      <div class="acess-painel" id="acess-painel" role="dialog" aria-label="Acessibilidade" hidden>
        <h2>Acessibilidade</h2>

        <div class="acess-grupo">
          <span>Tema</span>
          <div class="acess-opcoes">
            <button type="button" data-ajuste="tema" data-valor="claro">Claro</button>
            <button type="button" data-ajuste="tema" data-valor="escuro">Escuro</button>
          </div>
        </div>

        <div class="acess-grupo">
          <span>Tamanho do texto</span>
          <div class="acess-opcoes">
            <button type="button" data-ajuste="fonte" data-valor="0" aria-label="Texto normal">A</button>
            <button type="button" data-ajuste="fonte" data-valor="1" aria-label="Texto grande" style="font-size: 16px;">A+</button>
            <button type="button" data-ajuste="fonte" data-valor="2" aria-label="Texto muito grande" style="font-size: 18px;">A++</button>
          </div>
        </div>

        <div class="acess-grupo">
          <span>Destacar links</span>
          <div class="acess-opcoes">
            <button type="button" data-ajuste="links" data-valor="nao">Não</button>
            <button type="button" data-ajuste="links" data-valor="sim">Sim</button>
          </div>
        </div>

        <button class="acess-restaurar" type="button">Restaurar padrão</button>
      </div>
    </div>
  </div>
</header>

<#-- Menu de baixo, só no celular -->
<nav class="menu-celular" aria-label="Menu principal">
  <a href="/" <#if pagina?? && pagina == "inicio">class="ativo" aria-current="page"</#if>>
    <span class="menu-icone">
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">
        <path d="M3 10.5L12 3l9 7.5V20a1 1 0 0 1-1 1h-5v-6h-6v6H4a1 1 0 0 1-1-1z"/>
      </svg>
    </span>
    Início
  </a>
  <a href="/campanhas" <#if pagina?? && pagina == "campanhas">class="ativo" aria-current="page"</#if>>
    <span class="menu-icone">
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">
        <rect x="3" y="5" width="18" height="16" rx="2"/>
        <path d="M3 10h18M8 3v4M16 3v4M8 14h.01M12 14h.01M16 14h.01M8 17.5h.01M12 17.5h.01"/>
      </svg>
    </span>
    Campanhas
  </a>
  <a href="<#if organizador??>/organizador<#elseif doador??>/minhas-inscricoes<#else>/entrar</#if>" <#if pagina?? && (pagina == "conta" || pagina == "organizador")>class="ativo" aria-current="page"</#if>>
    <span class="menu-icone">
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true">
        <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4M10 17l5-5-5-5M15 12H3"/>
      </svg>
    </span>
    <#if organizador??>Painel<#elseif doador??>Minha conta<#else>Entrar</#if>
  </a>
</nav>
