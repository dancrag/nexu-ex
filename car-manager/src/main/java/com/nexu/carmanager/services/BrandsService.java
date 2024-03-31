package com.nexu.carmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nexu.carmanager.models.Brand;
import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.repositories.BrandsRespository;

@Service
public class BrandsService {

    BrandsRespository brandsRespository;

    public BrandsService(BrandsRespository brandsRespository) {
        this.brandsRespository = brandsRespository;
    }

    public List<Brand> getBrands(){
        List<Brand> brandsFound = brandsRespository.findAll();
        return brandsFound;
    }

    public ResponseEntity<List<Model>> getModelsByBrand(String id) {
        Optional<Brand> brandFound = brandsRespository.findById(Long.parseLong(id));

        if(brandFound.isPresent()) {
            return new ResponseEntity<>(brandFound.get().getModel(), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
