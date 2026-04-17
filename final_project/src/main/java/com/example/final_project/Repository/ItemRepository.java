package com.example.final_project.Repository;

import com.generation.ammazzon.model.entities.ItemAmmazzon;
import com.generation.ammazzon.model.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<ItemAmmazzon, Long> {
}
