package com.example.buoi1.service;

import com.example.buoi1.model.Company;
import com.example.buoi1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company saveOrUpdate(Company company) {
        return companyRepository.save(company);
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }
}
