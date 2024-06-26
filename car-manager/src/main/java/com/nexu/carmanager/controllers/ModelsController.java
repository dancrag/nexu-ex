package com.nexu.carmanager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.services.ModelsService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ModelsController {

    ModelsService modelsService;

    public ModelsController(ModelsService modelsService) {
        this.modelsService = modelsService;
    }

    @GetMapping("/")
    public String getHello() {
        return "Hello Nexu!";
    }

    @GetMapping("/models")
    public ResponseEntity<List<Model>> getModelsByParams(@RequestParam(required = false) Integer greater, @RequestParam(required = false) Integer lower){
        
        if(greater != null) {
            return modelsService.getModelsByParams(greater, true);
        }

        if(lower != null) {
            return modelsService.getModelsByParams(lower, false);
        }

        return modelsService.getModelsByParams();
    }

    @PutMapping("/models/{id}")
    public ResponseEntity<Model> editModelAveragePrice(@PathVariable String id, @RequestBody Model model) {
        return modelsService.editModelAveragePrice(id, model);
    }
}
