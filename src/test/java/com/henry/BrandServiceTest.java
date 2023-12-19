package com.henry;

import com.google.common.collect.Iterables;
import com.henry.model.brand.Brand;
import com.henry.repository.brand.BrandRepository;
import com.henry.service.BrandServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void testSaveBrand() {
        Brand brand = Brand.builder()
                .id(1L)
                .name("Test Brand")
                .build();

        when(brandRepository.save(any())).thenReturn(brand);

        Brand saved = brandService.save(brand);

        assertEquals(brand, saved);
        verify(brandRepository).save(any());
    }

    @Test
    void testFindAllBrands() {
        Brand brand1 = Brand.builder()
                .id(1L)
                .name("Test Brand")
                .build();
        Brand brand2 = Brand.builder()
                .id(2L)
                .name("Demo Brand")
                .build();

        when(brandRepository.findAll()).thenReturn(Arrays.asList(brand1, brand2));

        Iterable<Brand> result = brandService.findAll();

        assertEquals(2, Iterables.size(result));
        verify(brandRepository).findAll();
    }

    @Test
    void testFindBrandById() {
        Brand brand = Brand.builder()
                .id(1L)
                .name("Demo Brand")
                .build();

        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));

        Brand result = brandService.findById(1L);

        assertEquals(brand, result);
        verify(brandRepository).findById(1L);
    }
}