package com.nexu.carmanager.controllers;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.services.ModelsService;

@ExtendWith(MockitoExtension.class)
public class ModelsControllerTests {

    @InjectMocks
    private ModelsController modelsController;

    @Mock
    private ModelsService modelsService;

    @Test
    void testHelloWorld(){
        Assertions.assertEquals("Hello Nexu!", modelsController.getHello());
    }

    @Test
    void testGetModelsByParamsGreaterIsPresent() {
        ResponseEntity<List<Model>> responseEntity = new ResponseEntity<>(null, HttpStatus.ACCEPTED);

        Mockito.when(modelsService.getModelsByParams(100000, true)).thenReturn(responseEntity);
        Assertions.assertEquals(responseEntity, modelsController.getModelsByParams(100000, null));
    }

    @Test
    void testGetModelsByParamsLowerIsPresent() {
        ResponseEntity<List<Model>> responseEntity = new ResponseEntity<>(null, HttpStatus.ACCEPTED);

        Mockito.when(modelsService.getModelsByParams(100000, false)).thenReturn(responseEntity);
        Assertions.assertEquals(responseEntity, modelsController.getModelsByParams(null, 100000));
    }

    @Test
    void testGetModelsByParamsNonePresent() {
        ResponseEntity<List<Model>> responseEntity = new ResponseEntity<>(null, HttpStatus.ACCEPTED);

        Mockito.when(modelsService.getModelsByParams()).thenReturn(responseEntity);
        Assertions.assertEquals(responseEntity, modelsController.getModelsByParams(null, null));
    }
}
