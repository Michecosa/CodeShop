package com.example.final_project.Model;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Prodotto extends BaseEntity {
	private String nome;
	private double prezzo;

	@ManyToMany
	@JoinTable(name = "prodotto_to_categoria", joinColumns = @JoinColumn(name = "id_prodotto"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
	private List<Categoria> categorie = new ArrayList<>();
}
