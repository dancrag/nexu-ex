package com.nexu.carmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexu.carmanager.models.Brand;
import com.nexu.carmanager.services.BrandsService;

@RestController
public class BrandsController {

    @Autowired
    BrandsService brandsService;

    @GetMapping("/brands")
    @ResponseBody
    public List<Brand> getBrands() {
        List<Brand> brands = brandsService.getBrands();
        return brands;
    }

}
