package com.example.rosaproject.controller;

import com.example.rosaproject.service.DashboardService;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public String displayDashboard(Model model) {

        long nbContacts = dashboardService.countAllContacts();
        model.addAttribute("nbContacts", nbContacts);

        long nbProspects = dashboardService.countAllProspects();
        model.addAttribute("nbProspects", nbProspects);

        long nbClients = dashboardService.countAllClients();
        model.addAttribute("nbClients", nbClients);

        long nbEntreprises = dashboardService.countAllEntreprises();
        model.addAttribute("nbEntreprises", nbEntreprises);

        Map<String, Long> entreprisesbyNotesCount = dashboardService.entreprisesbyNotesCount();
        /*for (Map.Entry<String, Long> entry : entreprisesbyNotesCount.entrySet()) {

        }*/

        model.addAttribute("entreprisesbyNotesCount", new JSONObject(entreprisesbyNotesCount));

        //model.addAllAttributes(entreprisesbyNotesCount);

        /*Map<String, Long> nbContactsByEntreprise = dashboardService.countContactsByEntreprise();
        model.addAttribute("nbContactsByEntreprise", nbContactsByEntreprise);*/

        return "dashboard";
    }
}
