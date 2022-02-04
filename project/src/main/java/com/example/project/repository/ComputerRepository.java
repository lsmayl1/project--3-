package com.example.project.repository;

import com.example.project.model.ModelComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<ModelComputer, Integer> {
}
