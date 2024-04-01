package com.nexu.carmanager.services;

import java.util.ArrayList;
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

    public ResponseEntity<List<Brand>> getBrands(){
        List<Brand> brandsFound = brandsRespository.findAll();

        brandsFound.stream().forEach(brand -> {

            Optional<Double> totalAveragePrice = brand.getModel().stream().map(Model::getAveragePrice).reduce((acc, i) -> acc + i);
            
            if(totalAveragePrice.isPresent()) {
                Double brandAveragePrice = totalAveragePrice.get() / brand.getModel().stream().count();
                brand.setAveragePrice(brandAveragePrice);
            }
        });
        
        return new ResponseEntity<>(brandsFound, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Model>> getModelsByBrand(String id) {
        Optional<Brand> brandFound = brandsRespository.findById(Long.parseLong(id));

        if(brandFound.isPresent()) {
            return new ResponseEntity<>(brandFound.get().getModel(), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Brand> addNewBrand(Brand newBrand) {
        newBrand.setModel(new ArrayList<>());
        Brand savedBrand = brandsRespository.save(newBrand);

        return new ResponseEntity<>(savedBrand, HttpStatus.ACCEPTED);
    }
}
