package com.henry.controller;

import com.henry.model.brand.Brand;
import com.henry.service.DefaultService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3")
public class BrandController {

    private final DefaultService<Brand, Long> defaultService;

    public BrandController(DefaultService<Brand, Long> defaultService) {
        this.defaultService = defaultService;
    }

    @PostMapping("/brands")
    public Brand createEmployee(@RequestBody Brand brand) {
        return defaultService.save(brand);
    }

}
