package com.nexu.carmanager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nexu.carmanager.models.Brand;

@Repository
public interface BrandsRespository extends CrudRepository<Brand, Long> {

}
