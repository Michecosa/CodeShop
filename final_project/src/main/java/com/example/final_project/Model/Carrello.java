package com.example.final_project.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrello extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "carrello")
	private List<ItemAmmazzon> items = new ArrayList<>();

	public double getTotale() {
		return items.stream().mapToDouble(i -> i.getQtn() * i.getProdotto().getPrezzo()).sum();
	}

	/**
	 * Restituisce un Item se è già presente il prodotto, null se non è presente
	 * 
	 * @return
	 */
	public ItemAmmazzon productAlreadyPresent(Prodotto p) {
		// scorro gli item e restituisco quello con prodotto uguale al parametro
		for (ItemAmmazzon i : items)
			if (i.getProdotto().equals(p))
				return i;
		// se non esiste da null
		return null;

		// return
		// items.stream().filter(i->i.getProdotto().equals(p)).findFirst().orElse(null);
	}
}
