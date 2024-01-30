package com.example.crypttest.service;

public interface IGeneralService<T> {
    T getById(Long id);

    Iterable<T> getAll();

    T create(T t);


}
