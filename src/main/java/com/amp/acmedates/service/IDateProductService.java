package com.amp.acmedates.service;

import com.amp.acmedates.domain.DateProduct;

import java.util.List;

public interface IDateProductService {
    List<DateProduct> findAll();

    DateProduct save(DateProduct dateProduct);
}
