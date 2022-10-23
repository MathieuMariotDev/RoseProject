package com.example.rosaproject.controller;

import com.example.rosaproject.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "dashboard";
    }
}
