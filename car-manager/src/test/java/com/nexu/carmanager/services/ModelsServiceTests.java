package com.nexu.carmanager.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.repositories.ModelsRepository;

@ExtendWith(MockitoExtension.class)
class ModelsServiceTests {

    @InjectMocks
    private ModelsService modelsService;

    @Mock
    private ModelsRepository modelsRepository;

    @Test
    void testGetModels(){
        List<Model> models = new ArrayList<>();
        models.add(new Model(Long.valueOf(1), "Civic", null, 1000000));
        
        Mockito.when(modelsRepository.findAll()).thenReturn(models);
        Assertions.assertNotNull(modelsService.getModelsByParams());
    }
    
}
