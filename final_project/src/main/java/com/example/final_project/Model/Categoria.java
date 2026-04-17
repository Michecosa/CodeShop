package com.example.final_project.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Categoria extends BaseEntity {
	private String nome;

	@ManyToMany(mappedBy = "categorie")
	private List<Prodotto> prodotti = new ArrayList<>();

}
