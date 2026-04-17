package com.example.final_project.Repository;

public interface CarrelloRepository extends JpaRepository<Carrello, Long> {
    // Metodo che restituisce un carrello prendendo il ingresso lo username
    // dell'utente a cui appartiene
    Carrello findByUtente_Username(String username);
}
