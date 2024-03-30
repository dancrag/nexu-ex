package com.nexu.carmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.repositories.ModelsRepository;

@Service
public class ModelsService {

    @Autowired
    ModelsRepository modelsRepository;

    public List<Model> getModelsByParams() {
        List<Model> modelsFound = modelsRepository.findAll();
        return modelsFound;
    }

}
