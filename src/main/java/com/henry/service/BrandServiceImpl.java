package com.henry.service;

import com.henry.model.brand.Brand;
import com.henry.repository.brand.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public final class BrandServiceImpl implements DefaultService<Brand, Long> {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public Brand save(Brand obj) {
        return brandRepository.save(obj);
    }

    @Override
    public Iterable<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).get();
    }
}
