package com.nexu.carmanager.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.repositories.ModelsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@ExtendWith(MockitoExtension.class)
class ModelsServiceTests {

    @InjectMocks
    private ModelsService modelsService;

    @Mock
    private ModelsRepository modelsRepository;

    private List<Model> models = new ArrayList<>();

    private Model model;

    @Mock
    private EntityManagerFactory emf;

    private EntityManager em;

    @BeforeEach
    void init() {
        model = new Model(Long.valueOf(1), "Civic", null, 1000000);
        models.add(model);
    }

    @Test
    void testGetModels(){
        Mockito.when(modelsRepository.findAll()).thenReturn(models);
        Assertions.assertNotNull(modelsService.getModelsByParams());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testGetModelsByParams(boolean param){
        Mockito.when(modelsRepository.findAll()).thenReturn(models);
        Assertions.assertNotNull(modelsService.getModelsByParams(100000, param));
    }

    @Test
    void testEditModelAveragePriceWhenLessThan(){
        model.setAveragePrice(99999);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE), modelsService.editModelAveragePrice("1", model));
    }
    
}
