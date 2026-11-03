package com.rotasolidaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotasolidaria.models.Doador;
import com.rotasolidaria.models.Inscricao;
import com.rotasolidaria.repositories.CampanhaRepository;
import com.rotasolidaria.repositories.DoadorRepository;
import com.rotasolidaria.repositories.InscricaoRepository;
import com.rotasolidaria.repositories.OrganizadorRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// Conta do doador: cadastro, login e "Minhas inscrições".
// Depois do login, o doador fica guardado na sessão com o nome "doador".
@Controller
public class DoadorController {

    @Autowired
    private DoadorRepository doadorRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @GetMapping("/entrar")
    public String telaLogin(HttpSession session, Model model) {
        if (session.getAttribute("organizador") != null) {
            return "redirect:/organizador";
        }
        if (session.getAttribute("doador") != null) {
            return "redirect:/minhas-inscricoes";
        }
        model.addAttribute("pagina", "conta");
        return "entrar";
    }

    @PostMapping("/entrar")
    public String entrar(@RequestParam String email,
                         @RequestParam String senha,
                         HttpServletRequest request,
                         Model model) {
        // A mesma tela serve para o organizador: se for ele, vai para o painel
        if (organizadorRepository.loginValido(email.trim(), senha)) {
            trocarIdDaSessao(request);
            request.getSession().setAttribute("organizador", email.trim());
            return "redirect:/organizador";
        }

        Doador doador = doadorRepository.login(email.trim().toLowerCase(), senha);
        if (doador == null) {
            model.addAttribute("pagina", "conta");
            model.addAttribute("erro", "E-mail ou senha incorretos.");
            model.addAttribute("email", email);
            return "entrar";
        }
        guardarNaSessao(doador, request);
        return "redirect:/minhas-inscricoes";
    }

    @GetMapping("/sair")
    public String sair(HttpSession session) {
        session.removeAttribute("doador");
        return "redirect:/";
    }

    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        model.addAttribute("pagina", "conta");
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@RequestParam String nome,
                            @RequestParam String email,
                            @RequestParam String telefone,
                            @RequestParam String cidade,
                            @RequestParam String senha,
                            @RequestParam String confirmarSenha,
                            HttpServletRequest request,
                            Model model) {
        String emailLimpo = email.trim().toLowerCase();

        String erro = null;
        if (nome.isBlank()) {
            erro = "Informe o seu nome.";
        } else if (!emailLimpo.contains("@")) {
            erro = "Informe um e-mail válido.";
        } else if (telefone.isBlank() || telefone.length() < 8) {
            erro = "Informe um telefone (WhatsApp) válido, com DDD.";
        } else if (cidade.isBlank()) {
            erro = "Informe a sua cidade.";
        } else if (senha.length() < 6) {
            erro = "A senha precisa ter pelo menos 6 caracteres.";
        } else if (!senha.equals(confirmarSenha)) {
            erro = "As duas senhas não são iguais.";
        } else if (doadorRepository.existeEmail(emailLimpo)) {
            erro = "Já existe uma conta com esse e-mail. Tente entrar.";
        }

        if (erro != null) {
            model.addAttribute("pagina", "conta");
            model.addAttribute("erro", erro);
            model.addAttribute("nome", nome);
            model.addAttribute("email", email);
            model.addAttribute("telefone", telefone);
            model.addAttribute("cidade", cidade);
            return "cadastro";
        }

        Doador doador = new Doador();
        doador.setNome(nome.trim());
        doador.setEmail(emailLimpo);
        doador.setTelefone(telefone.trim());
        doador.setCidade(cidade.trim());
        doadorRepository.salvar(doador, senha);

        guardarNaSessao(doadorRepository.login(emailLimpo, senha), request);
        return "redirect:/minhas-inscricoes";
    }

    @GetMapping("/minhas-inscricoes")
    public String minhasInscricoes(HttpSession session, Model model) {
        Doador doador = (Doador) session.getAttribute("doador");
        if (doador == null) {
            return "redirect:/entrar";
        }
        List<Inscricao> inscricoes = inscricaoRepository.findByDoador(doador.getId());
        for (Inscricao i : inscricoes) {
            i.setCampanha(campanhaRepository.findById(i.getCampanhaId()));
        }
        model.addAttribute("inscricoes", inscricoes);
        model.addAttribute("pagina", "conta");
        return "minhas-inscricoes";
    }

    // O doador só consegue cancelar as inscrições que são dele
    @PostMapping("/minhas-inscricoes/cancelar")
    public String cancelar(@RequestParam Long id, HttpSession session) {
        Doador doador = (Doador) session.getAttribute("doador");
        if (doador == null) {
            return "redirect:/entrar";
        }
        Inscricao inscricao = inscricaoRepository.findById(id);
        if (inscricao != null && doador.getId().equals(inscricao.getDoadorId())) {
            inscricaoRepository.alterarStatus(id, "CANCELADA");
        }
        return "redirect:/minhas-inscricoes";
    }

    private void guardarNaSessao(Doador doador, HttpServletRequest request) {
        trocarIdDaSessao(request);
        request.getSession().setAttribute("doador", doador);
    }

    // Troca o id da sessão no login para evitar roubo de sessão
    private void trocarIdDaSessao(HttpServletRequest request) {
        request.getSession();
        request.changeSessionId();
    }
}
