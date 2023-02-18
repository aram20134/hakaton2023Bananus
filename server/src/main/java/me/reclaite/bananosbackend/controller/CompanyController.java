package me.reclaite.bananosbackend.controller;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.company.Company;
import me.reclaite.bananosbackend.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/company")
    public Company getCompany(@RequestParam("id") long companyId) {
        return companyService.getCompanyRepository().findById(companyId).orElse(null);
    }

    @GetMapping("/companies")
    public List<Company> getCompanies() {
        return companyService.getCompanyRepository().findAll();
    }
}
