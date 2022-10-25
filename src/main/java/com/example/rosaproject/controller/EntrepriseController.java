package com.example.rosaproject.controller;

import com.example.rosaproject.controller.dto.CreateEntrepriseDto;
import com.example.rosaproject.controller.entity.Entreprise;
import com.example.rosaproject.service.EntrepriseService;
import com.example.rosaproject.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/entreprises")
public class EntrepriseController {

    private EntrepriseService entrepriseService;

    private StorageService storageService;

    public EntrepriseController(EntrepriseService entrepriseService, StorageService storageService) {
        this.entrepriseService = entrepriseService;
        this.storageService = storageService;
    }

    @GetMapping("/all")
    public String displayAllEntreprises(Model model, @RequestParam(value = "search", required = false) String searchValue) {
        if (searchValue != null) {
            List<Entreprise> entrepriseList = entrepriseService.getEntrepriseByKeywords(searchValue, searchValue);
            model.addAttribute("entreprises", entrepriseList);
        } else{
            List<Entreprise> entrepriseList = entrepriseService.getAllEntreprises();
            model.addAttribute("entreprises", entrepriseList);
        }
        return "entreprisesListView";
    }



    @GetMapping("/details/{id}")
    public String displaySpecificEntreprise(@PathVariable("id") long id, Model model) {
        Entreprise entreprise = entrepriseService.getSpecificEntreprise(id);
        model.addAttribute("entreprise", entreprise);
        return "entrepriseDetailsContacts";
    }

    @GetMapping("/add")
    public String createEntrepriseForm(Model model) {
        model.addAttribute("entreprise", new CreateEntrepriseDto());
        return "createEntrepriseForm";
    }

    @PostMapping("/add")
    public RedirectView createBook(CreateEntrepriseDto createEntreprise) {
        entrepriseService.createEntreprise(createEntreprise);
        return new RedirectView("/entreprises/all");
    }

    @GetMapping("/edit/{id}")
    public String editEntrepriseForm(@PathVariable("id") long id, Model model) {
        CreateEntrepriseDto entrepriseDto = CreateEntrepriseDto.toEntrepriseDto(entrepriseService.getSpecificEntreprise(id));
        model.addAttribute("entreprise", entrepriseDto);
        return "editEntrepriseForm";
    }

    @PostMapping("/edit/{id}")
    public RedirectView editEntreprise(@PathVariable("id") long id, CreateEntrepriseDto editEntreprise) {
        entrepriseService.editEntreprise(id, editEntreprise);
        return new RedirectView("/entreprises/all");
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteEntreprise(@PathVariable("id") long id) {
        entrepriseService.deleteEntreprise(id);
        return new RedirectView("/entreprises/all");
    }

}
