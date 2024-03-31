package com.nexu.carmanager.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexu.carmanager.models.Brand;
import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.services.BrandsService;

@RestController
public class BrandsController {

    BrandsService brandsService;

    public BrandsController(BrandsService brandsService) {
        this.brandsService = brandsService;
    }

    /**
     * List all brands, showing the average price of models
     * @return A list of brands
     */
    @GetMapping("/brands")
    public List<Brand> getBrands() {
        List<Brand> brands = brandsService.getBrands();
        return brands;
    }

    /**
     * Returns a list of models by brand
     * @param id the id of the brand
     * @return a list of models for the specific brand
     */
    @GetMapping("/brands/{id}/models")
    public ResponseEntity<List<Model>> getModelsByBrand(@PathVariable String id) {
        return brandsService.getModelsByBrand(id);
    }

    @PostMapping("/brands")
    public Brand addNewBrand(Brand newBrand) {
        return null;
    }

    @PostMapping("/brands/{id}/models")
    public Brand addNewModelToBrand(@PathVariable String id, Model newModel) {
        return null;
    }

}
