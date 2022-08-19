package com.henry.service;

public sealed interface DefaultService<T, G> permits UserServiceImpl, CompanyServiceImpl, BrandServiceImpl {

    T save(T obj);
    Iterable<T>  findAll();
    T findById(G id);
}