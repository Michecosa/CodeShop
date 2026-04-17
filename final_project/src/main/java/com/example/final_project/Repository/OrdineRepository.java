package com.example.final_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.final_project.Model.Ordine;

public interface OrdineRepository extends JpaRepository<Ordine, Long> {
    List<Ordine> findByUtente_Username(String username);
}
