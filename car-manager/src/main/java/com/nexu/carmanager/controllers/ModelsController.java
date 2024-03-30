package com.nexu.carmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.services.ModelsService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ModelsController {

    @Autowired
    ModelsService modelsService;

    @GetMapping("/")
    public String getHello() {
        return new String("Hello World!!");
    }

    @PutMapping("/models/{id}")
    public Model putMethodName(@PathVariable String id, @RequestBody String entity) {
        return null;
    }

    @GetMapping("/models")
    @ResponseBody
    public List<Model> getModelsByParams(@RequestParam(required = false) Integer greater, @RequestParam(required = false) Integer lower){
        
        if(greater.equals(null)) {
            
        }

        return modelsService.getModelsByParams();
    }
}
