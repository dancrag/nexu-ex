package com.nexu.carmanager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexu.carmanager.models.Brand;
import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.services.BrandsService;
import com.nexu.carmanager.services.ModelsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BrandsController {

    BrandsService brandsService;

    ModelsService modelsService;

    public BrandsController(BrandsService brandsService, ModelsService modelsService) {
        this.brandsService = brandsService;
        this.modelsService = modelsService;
    }

    /**
     * List all brands, showing the average price of models
     * @return A list of brands
     */
    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands() {
        log.info("op = get brands");
        return brandsService.getBrands();
    }

    /**
     * Returns a list of models by brand
     * @param id the id of the brand
     * @return a list of models for the specific brand
     */
    @GetMapping("/brands/{id}/models")
    public ResponseEntity<List<Model>> getModelsByBrand(@PathVariable String id) {
        log.info("op = get models by brand, id = {}", id);
        return brandsService.getModelsByBrand(id);
    }

    @PostMapping(value = "/brands")
    public ResponseEntity<Brand> addNewBrand(@RequestBody Brand newBrand) {
        log.info("op = add new brand, entity = {}", newBrand);
        return brandsService.addNewBrand(newBrand);
    }

    @PostMapping("/brands/{id}/models")
    public ResponseEntity<Model> addNewModelToBrand(@PathVariable String id, @RequestBody Model newModel) {
        log.info("op = add new model to a brand, id = {}, new model = {}", id, newModel);
        return modelsService.addModelInBrand(id, newModel);
    }

}
