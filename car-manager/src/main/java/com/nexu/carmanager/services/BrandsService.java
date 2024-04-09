package com.nexu.carmanager.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nexu.carmanager.models.Brand;
import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.repositories.BrandsRespository;
import com.nexu.carmanager.repositories.ModelsRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandsService {

    BrandsRespository brandsRespository;

    ModelsRepository modelsRepository;

    public BrandsService(BrandsRespository brandsRespository, ModelsRepository modelsRepository) {
        this.brandsRespository = brandsRespository;
        this.modelsRepository = modelsRepository;
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

    public ResponseEntity<Set<Model>> getModelsByBrand(String id) {
        Optional<Brand> brandFound = brandsRespository.findById(Long.parseLong(id));

        if(brandFound.isPresent()) {
            return new ResponseEntity<>(brandFound.get().getModel(), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Brand> addNewBrand(@NonNull Brand newBrand) {

        try {
            Brand savedBrand = brandsRespository.save(newBrand);
            
            return new ResponseEntity<>(savedBrand, HttpStatus.ACCEPTED);
        } catch(IllegalStateException ex) {
            log.error("Entity can't save, cause = {}, message = {},", ex.getCause(), ex.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch(DataIntegrityViolationException ex) {
            log.error("SQL Error, cause = {}, message = {},", ex.getCause(), ex.getMessage());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<Model> addModelInBrand(String id, Model newModel) {

        Optional<Brand> brandFound = brandsRespository.findById(Long.parseLong(id));

        if(brandFound.isPresent()) {

            if(newModel.getAveragePrice() < 100000) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            newModel.setBrand(brandFound.get());
            Model modelSaved = modelsRepository.save(newModel);
            
            log.info("op = addModelInBrand, saved model = {}", modelSaved);
            
            return new ResponseEntity<>(modelSaved, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
