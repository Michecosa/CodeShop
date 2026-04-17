package com.example.final_project.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrello extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "carrello", orphanRemoval = true)
	private List<ItemQuantity> items = new ArrayList<>();

	public double getTotale() {
		return items.stream().mapToDouble(i -> i.getQtn() * i.getProdotto().getPrezzo()).sum();
	}

	public ItemQuantity productAlreadyPresent(Prodotto p) {
		for (ItemQuantity i : items)
			if (i.getProdotto().equals(p))
				return i;
		return null;
	}
}