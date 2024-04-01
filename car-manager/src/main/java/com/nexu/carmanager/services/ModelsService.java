package com.nexu.carmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nexu.carmanager.models.Brand;
import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.repositories.BrandsRespository;
import com.nexu.carmanager.repositories.ModelsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModelsService {

    ModelsRepository modelsRepository;

    BrandsRespository brandsRespository;

    public ModelsService(ModelsRepository modelsRepository, BrandsRespository brandsRespository) {
        this.modelsRepository = modelsRepository;
        this.brandsRespository = brandsRespository;
    }

    public List<Model> getModelsByParams() {
        return modelsRepository.findAll();
    }

    public List<Model> getModelsByParams(int price, boolean isGreater) {
        return modelsRepository.findAll().stream().filter(model -> {
            if(isGreater) {
                return model.getAveragePrice() > price;
            }
            return model.getAveragePrice() < price;
        }).toList();
    }

    public ResponseEntity<Model> addModelInBrand(String id, Model newModel) {
        Optional<Brand> brandFound = brandsRespository.findById(Long.parseLong(id));

        if(brandFound.isPresent()) {
            newModel.setBrand(brandFound.get());
            Model savedModel = modelsRepository.save(newModel);
            
            log.info("component = ModelsService, op = updateModelInBrand, entity = {}", savedModel);
            
            return new ResponseEntity<>(savedModel, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
