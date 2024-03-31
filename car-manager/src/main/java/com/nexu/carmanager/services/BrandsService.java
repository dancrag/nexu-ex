package com.nexu.carmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexu.carmanager.models.Brand;
import com.nexu.carmanager.repositories.BrandsRespository;

@Service
public class BrandsService {

    @Autowired
    BrandsRespository brandsRespository;

    public List<Brand> getBrands(){
        List<Brand> brandsFound = brandsRespository.findAll();
        brandsFound.stream().flatMap(brand -> brand.getModel());
        return brandsFound;
    }
}
