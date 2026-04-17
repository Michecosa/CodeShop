package com.example.final_project.Model;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Utente extends BaseEntity {

	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String mail;
	private String password;
	private String roles;// csv "ROLE_ADMIN,ROLE_USER"

	@OneToOne(mappedBy = "utente")
	private Carrello carrello;

	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Ordine> ordini = new ArrayList<>();
}
