package me.reclaite.bananosbackend.service;

import lombok.RequiredArgsConstructor;
import me.reclaite.bananosbackend.model.company.Company;
import me.reclaite.bananosbackend.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company addCompany(@RequestBody Company company) {
        return companyRepository.saveAndFlush(company);
    }

    public void deleteCompany(int id) {
        companyRepository.deleteById(id);
    }

    public Company getCompany(int id) {
        return companyRepository.getCompanyById(id);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }
}
