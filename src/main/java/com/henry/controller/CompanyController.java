package com.henry.controller;

import com.henry.model.company.Company;
import com.henry.model.user.User;
import com.henry.service.DefaultService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class CompanyController {

    private final DefaultService<Company,Long> defaultService;

    public CompanyController(DefaultService<Company, Long> defaultService) {
        this.defaultService = defaultService;
    }

    @PostMapping("/companies")
    public Company createEmployee(@RequestBody Company company) {
        return defaultService.save(company);
    }
}
