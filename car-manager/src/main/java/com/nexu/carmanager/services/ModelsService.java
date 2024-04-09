package com.nexu.carmanager.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nexu.carmanager.models.Model;
import com.nexu.carmanager.repositories.ModelsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModelsService {

    ModelsRepository modelsRepository;

    EntityManagerFactory emf;

    public ModelsService(ModelsRepository modelsRepository, EntityManagerFactory emf) {
        this.modelsRepository = modelsRepository;
        this.emf = emf;
    }

    public ResponseEntity<List<Model>>  getModelsByParams() {
        return new ResponseEntity<>(modelsRepository.findAll(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Model>> getModelsByParams(int price, boolean isGreater) {
        log.info("op = getModelsByParams");

        List<Model> models = modelsRepository.findAll().stream().filter(model -> {
            return isGreater ? model.getAveragePrice() > price : model.getAveragePrice() < price;
        }).toList();

        return new ResponseEntity<>(models, HttpStatus.ACCEPTED); 
    }

    public ResponseEntity<Model> editModelAveragePrice(String id, Model model) {

        if(model.getAveragePrice() < 100000) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Model modelFound = em.find(Model.class, id);
        modelFound.setAveragePrice(model.getAveragePrice());

        em.getTransaction().commit();
        em.close();

        log.info("op = editModelAveragePrice, id = {}, model = {}", id, model);

        return new ResponseEntity<>(modelFound, HttpStatus.ACCEPTED);
    }

}
