// Painel de acessibilidade: tema, tamanho do texto e destaque de links (salvos no localStorage).
// Carregado no <head> para o tema escuro não "piscar" branco ao abrir a página.

(function () {
  var raiz = document.documentElement;

  var escuroNoSistema = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
  var padrao = {
    tema: escuroNoSistema ? 'escuro' : 'claro',
    fonte: '0',
    links: 'nao'
  };

  function ler(nome) {
    try {
      return localStorage.getItem('acess-' + nome) || padrao[nome];
    } catch (e) {
      return padrao[nome];
    }
  }

  function aplicar(nome, valor) {
    raiz.setAttribute('data-' + nome, valor);
  }

  for (var nome in padrao) {
    aplicar(nome, ler(nome));
  }

  document.addEventListener('DOMContentLoaded', function () {
    var botao = document.querySelector('.acess-botao');
    var painel = document.getElementById('acess-painel');
    if (!botao || !painel) {
      return;
    }

    function atualizarBotoes() {
      painel.querySelectorAll('[data-ajuste]').forEach(function (b) {
        var ativo = raiz.getAttribute('data-' + b.dataset.ajuste) === b.dataset.valor;
        b.setAttribute('aria-pressed', ativo ? 'true' : 'false');
      });
    }

    function abrir(sim) {
      painel.hidden = !sim;
      botao.setAttribute('aria-expanded', sim ? 'true' : 'false');
    }

    botao.addEventListener('click', function () {
      abrir(painel.hidden);
    });

    painel.querySelectorAll('[data-ajuste]').forEach(function (b) {
      b.addEventListener('click', function () {
        aplicar(b.dataset.ajuste, b.dataset.valor);
        try {
          localStorage.setItem('acess-' + b.dataset.ajuste, b.dataset.valor);
        } catch (e) { /* navegador sem localStorage: vale só nesta página */ }
        atualizarBotoes();
      });
    });

    painel.querySelector('.acess-restaurar').addEventListener('click', function () {
      for (var nome in padrao) {
        try { localStorage.removeItem('acess-' + nome); } catch (e) { }
        aplicar(nome, padrao[nome]);
      }
      atualizarBotoes();
    });

    document.addEventListener('keydown', function (e) {
      if (e.key === 'Escape' && !painel.hidden) {
        abrir(false);
        botao.focus();
      }
    });
    document.addEventListener('click', function (e) {
      if (!painel.hidden && !painel.contains(e.target) && !botao.contains(e.target)) {
        abrir(false);
      }
    });

    atualizarBotoes();
  });
})();
