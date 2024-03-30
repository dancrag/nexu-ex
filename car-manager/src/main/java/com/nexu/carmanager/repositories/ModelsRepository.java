package com.nexu.carmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexu.carmanager.models.Model;

@Repository
public interface ModelsRepository extends JpaRepository<Model, Long> {
}
