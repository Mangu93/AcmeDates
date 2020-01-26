package com.amp.acmedates.repository;

import com.amp.acmedates.domain.DateProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateProductRepository extends CrudRepository<DateProduct, Long> {
}
