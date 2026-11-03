package com.rotasolidaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotasolidaria.models.Campanha;
import com.rotasolidaria.models.Doador;
import com.rotasolidaria.models.Inscricao;
import com.rotasolidaria.repositories.CampanhaRepository;
import com.rotasolidaria.repositories.InscricaoRepository;

import jakarta.servlet.http.HttpSession;

// Parte pública: lista de excursões, detalhes de uma excursão e inscrição.
@Controller
public class CampanhaController {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @GetMapping("/campanhas")
    public String listar(Model model) {
        List<Campanha> campanhas = campanhaRepository.findAbertas();
        for (Campanha c : campanhas) {
            c.setInscritos(inscricaoRepository.contarPorCampanha(c.getId()));
        }
        model.addAttribute("campanhas", campanhas);
        model.addAttribute("pagina", "campanhas");
        return "campanhas";
    }

    @GetMapping("/campanha")
    public String detalhes(@RequestParam Long id, HttpSession session, Model model) {
        Campanha campanha = buscarComInscritos(id);
        if (campanha == null) {
            return "redirect:/campanhas";
        }
        model.addAttribute("campanha", campanha);

        // Doador logado: o formulário já vem preenchido com os dados da conta
        Doador doador = (Doador) session.getAttribute("doador");
        if (doador != null) {
            model.addAttribute("nome", doador.getNome());
            model.addAttribute("telefone", doador.getTelefone());
            model.addAttribute("email", doador.getEmail());
            model.addAttribute("cidade", doador.getCidade());
        }
        model.addAttribute("pagina", "campanhas");
        return "campanha";
    }

    @PostMapping("/inscrever")
    public String inscrever(@RequestParam Long campanhaId,
                            @RequestParam String nome,
                            @RequestParam String telefone,
                            @RequestParam(defaultValue = "") String email,
                            @RequestParam String cidade,
                            @RequestParam(defaultValue = "") String observacao,
                            HttpSession session,
                            Model model) {

        Campanha campanha = buscarComInscritos(campanhaId);
        if (campanha == null) {
            return "redirect:/campanhas";
        }

        String erro = null;
        if (nome.isBlank()) {
            erro = "Informe o seu nome.";
        } else if (telefone.isBlank() || telefone.length() < 8) {
            erro = "Informe um telefone (WhatsApp) válido, com DDD.";
        } else if (cidade.isBlank()) {
            erro = "Informe a cidade de onde você vai sair.";
        } else if (!campanha.isAceitaInscricao()) {
            erro = "Essa excursão não está mais aceitando inscrições.";
        } else if (inscricaoRepository.existeTelefone(campanhaId, telefone.trim())) {
            erro = "Já existe uma inscrição com esse telefone nesta excursão.";
        }

        if (erro != null) {
            model.addAttribute("campanha", campanha);
            model.addAttribute("pagina", "campanhas");
            model.addAttribute("erro", erro);
            model.addAttribute("nome", nome);
            model.addAttribute("telefone", telefone);
            model.addAttribute("email", email);
            model.addAttribute("cidade", cidade);
            model.addAttribute("observacao", observacao);
            return "campanha";
        }

        Inscricao inscricao = new Inscricao();
        inscricao.setCampanhaId(campanhaId);
        inscricao.setNome(nome.trim());
        inscricao.setTelefone(telefone.trim());
        inscricao.setEmail(email.trim());
        inscricao.setCidade(cidade.trim());
        inscricao.setObservacao(observacao.trim());

        // Doador logado: a inscrição fica ligada à conta dele
        Doador doador = (Doador) session.getAttribute("doador");
        if (doador != null) {
            inscricao.setDoadorId(doador.getId());
        }
        inscricaoRepository.salvar(inscricao);

        Long idNovo = inscricaoRepository.ultimoIdDoTelefone(campanhaId, telefone.trim());
        return "redirect:/inscricao?id=" + idNovo;
    }

    @GetMapping("/inscricao")
    public String confirmacao(@RequestParam Long id, Model model) {
        Inscricao inscricao = inscricaoRepository.findById(id);
        if (inscricao == null) {
            return "redirect:/campanhas";
        }
        Campanha campanha = campanhaRepository.findById(inscricao.getCampanhaId());
        model.addAttribute("inscricao", inscricao);
        model.addAttribute("campanha", campanha);
        model.addAttribute("pagina", "campanhas");
        return "inscricao-confirmada";
    }

    private Campanha buscarComInscritos(Long id) {
        Campanha campanha = campanhaRepository.findById(id);
        if (campanha != null) {
            campanha.setInscritos(inscricaoRepository.contarPorCampanha(id));
        }
        return campanha;
    }
}
