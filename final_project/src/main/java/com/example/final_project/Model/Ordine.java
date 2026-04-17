package com.example.final_project.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

/**
 * Fix applicato:
 * - aggiunto campo "dataPagamento": registra quando l'ordine è stato pagato,
 *   separando il flag booleano dall'informazione temporale (utile per audit e rimborsi).
 *   Se pagato=false, dataPagamento è null.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ordine extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    private boolean pagato;

    /** Data effettiva del pagamento. Null se l'ordine non è ancora stato pagato */
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    private LocalDate consegna;

    private String indirizzo;

    @OneToMany(mappedBy = "ordine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemQuantity> items = new ArrayList<>();

    public double getTotale() {
        return items.stream().mapToDouble(i -> i.getQtn() * i.getProdotto().getPrezzo()).sum();
    }

    /** Utility: segna l'ordine come pagato impostando anche la data odierna */
    public void setPagatoOggi() {
        this.pagato = true;
        this.dataPagamento = LocalDate.now();
    }
}