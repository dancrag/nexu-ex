package com.nexu.carmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.repositories.ModelsRepository;

@Service
public class ModelsService {

    ModelsRepository modelsRepository;

    public ModelsService(ModelsRepository modelsRepository) {
        this.modelsRepository = modelsRepository;
    }

    public List<Model> getModelsByParams() {
        return modelsRepository.findAll();
    }

    public List<Model> getModelsByParams(int price, boolean isGreater) {
        return modelsRepository.findAll().stream().filter((model) -> {
            if(isGreater) {
                return model.getAveragePrice() > price;
            }
            return model.getAveragePrice() < price;
        }).toList();
    }

}
