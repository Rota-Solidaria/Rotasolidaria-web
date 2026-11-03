package com.rotasolidaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rotasolidaria.models.Campanha;
import com.rotasolidaria.repositories.CampanhaRepository;
import com.rotasolidaria.repositories.InscricaoRepository;

// Páginas institucionais: início e guia "Como doar".
@Controller
public class HomeController {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @GetMapping("/")
    public String inicio(Model model) {
        List<Campanha> campanhas = campanhaRepository.findAbertas();

        if (campanhas.size() > 3) {
            campanhas = campanhas.subList(0, 3);
        }

        for (Campanha c : campanhas) {
            c.setInscritos(inscricaoRepository.contarPorCampanha(c.getId()));
        }

        model.addAttribute("campanhas", campanhas);
        model.addAttribute("pagina", "inicio");
        return "index";
    }

    @GetMapping("/como-doar")
    public String comoDoar(Model model) {
        model.addAttribute("pagina", "como-doar");
        return "como-doar";
    }
}
