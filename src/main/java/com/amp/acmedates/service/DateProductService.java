package com.amp.acmedates.service;

import com.amp.acmedates.domain.DateProduct;
import com.amp.acmedates.repository.DateProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DateProductService implements IDateProductService {

    @Autowired
    private DateProductRepository repository;

    @Override
    public List<DateProduct> findAll() {
        return (List<DateProduct>) repository.findAll();
    }

    @Override
    public DateProduct save(DateProduct dateProduct) {
        return repository.save(dateProduct);
    }
}
