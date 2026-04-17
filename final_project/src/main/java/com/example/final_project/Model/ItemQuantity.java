package com.example.final_project.Model;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;

/**
 * Fix applicato:
 * - aggiunto @Check constraint a livello DB: un item deve appartenere
 *   ESCLUSIVAMENTE a un carrello oppure a un ordine, mai a entrambi né a nessuno.
 * - aggiunto @AssertTrue per la stessa validazione lato Java (Bean Validation).
 */
@Entity
@Table(name = "item_quantity")
@Check(constraints =
    "(id_carrello IS NOT NULL AND id_ordine IS NULL) OR " +
    "(id_carrello IS NULL AND id_ordine IS NOT NULL)"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemQuantity extends BaseEntity {

    private int qtn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_carrello")
    @JsonIgnore
    private Carrello carrello;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ordine")
    @JsonIgnore
    private Ordine ordine;

    /**
     * Validazione lato applicazione: un ItemQuantity deve appartenere
     * ESATTAMENTE a un carrello o a un ordine, non a entrambi né a nessuno.
     */
    @AssertTrue(message = "Un item deve appartenere esattamente a un carrello o a un ordine")
    @Transient
    public boolean isAssociazioneValida() {
        return (carrello != null) ^ (ordine != null);
    }
}