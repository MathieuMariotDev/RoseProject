package com.example.rosaproject.controller;

import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.service.EntrepriseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/entreprises")
public class EntrepriseController {

    private EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @GetMapping("/all")
    public String displayAllEntreprises(Model model) {
        List<Entreprise> entrepriseList = entrepriseService.getAllEntreprises();
        model.addAttribute("entreprises", entrepriseList);
        return "entreprisesListView";
    }

    @GetMapping("/add")
    public String createEntrepriseForm(Model model) {
        model.addAttribute("entreprise", new Entreprise());
        return "createEntrepriseForm";
    }

    @PostMapping("/add")
    public RedirectView createBook(Entreprise createEntreprise) {
        entrepriseService.createEntreprise(createEntreprise);
        return new RedirectView("/entreprises/all");
    }

}
