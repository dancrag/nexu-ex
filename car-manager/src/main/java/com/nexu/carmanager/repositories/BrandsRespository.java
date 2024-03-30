package com.nexu.carmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexu.carmanager.models.Brand;

@Repository
public interface BrandsRespository extends JpaRepository<Brand, Long> {

}
