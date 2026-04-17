package com.example.final_project.Model;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ItemAmmazzon extends BaseEntity {
	private int qtn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_prodotto")
	private Prodotto prodotto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_carrello")
	private Carrello carrello;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ordine")
	private Ordine ordine;
}
