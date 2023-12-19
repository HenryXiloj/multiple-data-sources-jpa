package com.henry.service;

import com.henry.model.company.Company;
import com.henry.repository.company.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public final class CompanyServiceImpl implements DefaultService<Company, Long> {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company save(Company obj) {
        return companyRepository.save(obj);
    }

    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).get();
    }
}
