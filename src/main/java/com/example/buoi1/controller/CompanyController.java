package com.example.buoi1.controller;

import com.example.buoi1.model.Company;
import com.example.buoi1.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/addCompany")
    public String addCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "addCompany";
    }

    @PostMapping("/addCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.saveOrUpdate(company);
        return "redirect:/listCompany";
    }

    @GetMapping("/listCompany")
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "listCompany";
    }
}
