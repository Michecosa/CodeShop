package com.example.final_project.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.final_project.Model.Ordine;
import com.example.final_project.Service.OrdineService;

import lombok.RequiredArgsConstructor;

// Controller per la gestione degli ordini, con endpoint per visualizzare i propri ordini e operazioni CRUD
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdineController {

    private final OrdineService ordineService;

    // Endpoint per ottenere tutti gli ordini dell'utente autenticato
    @GetMapping
    public List<Ordine> getMiei(Authentication authentication) {
        List<Ordine> ordini = ordineService.trovaTuttiPerUtente(authentication.getName());

        // Logica per il limite di download (14 giorni)
        // Gli amministratori (ROLE_ADMIN) non hanno limiti temporali.
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            LocalDate limite = LocalDate.now().minusDays(14);
            for (Ordine ordine : ordini) {
                if (ordine.getData() != null && ordine.getData().isBefore(limite)) {
                    // Se l'ordine è più vecchio di 14 giorni, rimuoviamo i link di download per l'utente normale
                    ordine.getItems().forEach(item -> {
                        if (item.getProdotto() != null) {
                            item.getProdotto().setLinkDownload(null);
                        }
                    });
                }
            }
        }

        return ordini;
    }

    // Endpoint per ottenere un ordine specifico tramite ID
    @GetMapping("/{id}")
    public Ordine getById(@PathVariable Long id) {
        return ordineService.trovaPerID(id);
    }

    @PostMapping
    public Ordine create(@RequestParam String indirizzo,
            Authentication authentication) {
        return ordineService.creaOrdine(authentication.getName(), indirizzo);
    }

    // Endpoint per aggiornare un ordine esistente tramite ID
    @PutMapping("/{id}")
    public Ordine update(@PathVariable Long id, @RequestBody Ordine ordine) {
        return ordineService.aggiorna(id, ordine);
    }

    // Endpoint per eliminare un ordine tramite ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ordineService.elimina(id);
    }
}
