package com.henry;

import com.google.common.collect.Iterables;
import com.henry.model.company.Company;
import com.henry.repository.company.CompanyRepository;
import com.henry.service.CompanyServiceImpl;
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
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void testSaveCompany() {
        Company company =
                Company.builder()
                        .id(1L)
                        .name("Test Corp")
                        .build();

        when(companyRepository.save(any())).thenReturn(company);

        Company saved = companyService.save(company);

        assertEquals(company, saved);
        verify(companyRepository).save(any());
    }

    @Test
    void testFindAllCompanies() {
        Company comp1 = Company.builder()
                .id(1L)
                .name("Test Corp")
                .build();
        Company comp2 = Company.builder()
                .id(2L)
                .name("Demo Corp")
                .build();

        when(companyRepository.findAll()).thenReturn(Arrays.asList(comp1, comp2));

        Iterable<Company> result = companyService.findAll();


        assertEquals(2, Iterables.size(result));
        verify(companyRepository).findAll();
    }

    @Test
    void testFindCompanyById() {
        Company company = Company.builder()
                .id(1L)
                .name("Test Corp")
                .build();

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        Company result = companyService.findById(1L);

        assertEquals(company, result);
        verify(companyRepository).findById(1L);
    }
}