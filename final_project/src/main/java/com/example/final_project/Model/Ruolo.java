package com.example.final_project.Model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Ruolo applicativo assegnato a un utente.
 * ogni ruolo è ora una riga
 * dedicata nella tabella utente_ruolo, rispettando la 1NF.
 */
@Entity
@Table(name = "utente_ruolo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruolo extends BaseEntity {

    /** Es. "ROLE_ADMIN", "ROLE_USER" */
    @Column(nullable = false, length = 50)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utente", nullable = false)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Utente utente;
}