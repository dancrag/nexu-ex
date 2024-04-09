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
import jakarta.persistence.EntityTransaction;

@ExtendWith(MockitoExtension.class)
class ModelsServiceTests {

    @InjectMocks
    private ModelsService modelsService;

    @Mock
    private ModelsRepository modelsRepository;

    private List<Model> models = new ArrayList<>();

    @Mock
    private EntityManagerFactory emf;

    @Mock
    private EntityManager em;

    @Mock
    private EntityTransaction et;

    private Model civic;

    private Model civicCoupe;

    @BeforeEach
    void init() {
        civic = new Model(Long.valueOf(1), "Civic", null, 1000000);
        civicCoupe = new Model(Long.valueOf(2), "Civic Coupé", null, 2000000);
        models.add(civic);
        models.add(civicCoupe);
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
        civic.setAveragePrice(99999);
        Assertions.assertEquals(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE), modelsService.editModelAveragePrice("1", civic));
    }
    
    @Test
    void testEditModelAveragePriceWhenMoreThan(){
        civic.setAveragePrice(100000);
        Mockito.when(emf.createEntityManager()).thenReturn(em);
        Mockito.when(em.find(Model.class, "1")).thenReturn(civic);
        Mockito.when(em.getTransaction()).thenReturn(et);
        Assertions.assertEquals(new ResponseEntity<>(civic, HttpStatus.ACCEPTED), modelsService.editModelAveragePrice("1", civic));
    }
}
