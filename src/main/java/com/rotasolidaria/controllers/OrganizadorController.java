package com.rotasolidaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotasolidaria.models.Campanha;
import com.rotasolidaria.models.Inscricao;
import com.rotasolidaria.repositories.CampanhaRepository;
import com.rotasolidaria.repositories.InscricaoRepository;

import jakarta.servlet.http.HttpSession;

// Área do organizador: cria as excursões e organiza os inscritos.
// Não tem link no site: o organizador entra pela mesma tela /entrar dos doadores.
// O login fica guardado na sessão (HttpSession) com o nome "organizador".
@Controller
public class OrganizadorController {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @GetMapping("/organizador/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/organizador")
    public String painel(HttpSession session, Model model) {
        if (naoEstaLogado(session)) {
            return "redirect:/entrar";
        }
        List<Campanha> campanhas = campanhaRepository.findAll();
        for (Campanha c : campanhas) {
            c.setInscritos(inscricaoRepository.contarPorCampanha(c.getId()));
        }
        model.addAttribute("campanhas", campanhas);
        model.addAttribute("pagina", "organizador");
        return "admin/painel";
    }

    @GetMapping("/organizador/campanha/nova")
    public String novaCampanha(HttpSession session, Model model) {
        if (naoEstaLogado(session)) {
            return "redirect:/entrar";
        }
        Campanha campanha = new Campanha();
        campanha.setId(0L);
        campanha.setStatus("ABERTA");
        campanha.setVagas(40);
        model.addAttribute("campanha", campanha);
        model.addAttribute("pagina", "organizador");
        return "admin/campanha-form";
    }

    @GetMapping("/organizador/campanha/editar")
    public String editarCampanha(@RequestParam Long id, HttpSession session, Model model) {
        if (naoEstaLogado(session)) {
            return "redirect:/entrar";
        }
        Campanha campanha = campanhaRepository.findById(id);
        if (campanha == null) {
            return "redirect:/organizador";
        }
        model.addAttribute("campanha", campanha);
        model.addAttribute("pagina", "organizador");
        return "admin/campanha-form";
    }

    // Serve para as duas situações: id = 0 cria uma nova, id > 0 atualiza
    @PostMapping("/organizador/campanha/salvar")
    public String salvarCampanha(@RequestParam(defaultValue = "0") Long id,
                                 @RequestParam String titulo,
                                 @RequestParam(defaultValue = "") String descricao,
                                 @RequestParam String data,
                                 @RequestParam String horarioSaida,
                                 @RequestParam String localSaida,
                                 @RequestParam String hemocentro,
                                 @RequestParam(defaultValue = "0") int vagas,
                                 @RequestParam(defaultValue = "ABERTA") String status,
                                 HttpSession session,
                                 Model model) {
        if (naoEstaLogado(session)) {
            return "redirect:/entrar";
        }

        Campanha campanha = new Campanha();
        campanha.setId(id);
        campanha.setTitulo(titulo.trim());
        campanha.setDescricao(descricao.trim());
        campanha.setData(data);
        campanha.setHorarioSaida(horarioSaida);
        campanha.setLocalSaida(localSaida.trim());
        campanha.setHemocentro(hemocentro.trim());
        campanha.setVagas(vagas);
        campanha.setStatus(status);

        String erro = null;
        if (titulo.isBlank()) {
            erro = "Informe o título da excursão.";
        } else if (data.isBlank()) {
            erro = "Informe a data.";
        } else if (horarioSaida.isBlank()) {
            erro = "Informe o horário de saída.";
        } else if (localSaida.isBlank()) {
            erro = "Informe o local de saída do ônibus.";
        } else if (hemocentro.isBlank()) {
            erro = "Informe o hemocentro de destino.";
        } else if (vagas < 1) {
            erro = "A quantidade de vagas precisa ser maior que zero.";
        }

        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("campanha", campanha);
            model.addAttribute("pagina", "organizador");
            return "admin/campanha-form";
        }

        if (id == 0) {
            campanhaRepository.salvar(campanha);
        } else {
            campanhaRepository.atualizar(campanha);
        }
        return "redirect:/organizador";
    }

    @PostMapping("/organizador/campanha/status")
    public String statusCampanha(@RequestParam Long id, @RequestParam String status, HttpSession session) {
        if (naoEstaLogado(session)) {
            return "redirect:/entrar";
        }
        campanhaRepository.alterarStatus(id, status);
        return "redirect:/organizador";
    }

    @PostMapping("/organizador/campanha/excluir")
    public String excluirCampanha(@RequestParam Long id, HttpSession session) {
        if (naoEstaLogado(session)) {
            return "redirect:/entrar";
        }
        campanhaRepository.excluir(id);
        return "redirect:/organizador";
    }

    @GetMapping("/organizador/inscritos")
    public String inscritos(@RequestParam Long id, HttpSession session, Model model) {
        if (naoEstaLogado(session)) {
            return "redirect:/entrar";
        }
        Campanha campanha = campanhaRepository.findById(id);
        if (campanha == null) {
            return "redirect:/organizador";
        }
        campanha.setInscritos(inscricaoRepository.contarPorCampanha(id));

        List<Inscricao> inscricoes = inscricaoRepository.findByCampanha(id);

        int canceladas = 0;
        for (Inscricao i : inscricoes) {
            if ("CANCELADA".equals(i.getStatus())) {
                canceladas++;
            }
        }

        model.addAttribute("campanha", campanha);
        model.addAttribute("inscricoes", inscricoes);
        model.addAttribute("canceladas", canceladas);
        model.addAttribute("pagina", "organizador");
        return "admin/inscritos";
    }

    @PostMapping("/organizador/inscricao/status")
    public String statusInscricao(@RequestParam Long id,
                                  @RequestParam Long campanhaId,
                                  @RequestParam String status,
                                  HttpSession session) {
        if (naoEstaLogado(session)) {
            return "redirect:/entrar";
        }
        inscricaoRepository.alterarStatus(id, status);
        return "redirect:/organizador/inscritos?id=" + campanhaId;
    }

    private boolean naoEstaLogado(HttpSession session) {
        return session.getAttribute("organizador") == null;
    }
}
