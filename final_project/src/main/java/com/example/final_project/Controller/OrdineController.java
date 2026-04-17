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

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdineController {

    private final OrdineService ordineService;

    @GetMapping
    public List<Ordine> getMiei(Authentication authentication) {
        return ordineService.trovaTuttiPerUtente(authentication.getName());
    }

    @GetMapping("/{id}")
    public Ordine getById(@PathVariable Long id) {
        return ordineService.trovaPerID(id);
    }

    @PostMapping
    public Ordine create(@RequestParam String indirizzo,
                         @RequestParam(required = false) LocalDate consegna,
                         Authentication authentication) {
        return ordineService.creaOrdine(authentication.getName(), indirizzo,
                consegna != null ? consegna : LocalDate.now().plusDays(3));
    }

    @PutMapping("/{id}")
    public Ordine update(@PathVariable Long id, @RequestBody Ordine ordine) {
        return ordineService.aggiorna(id, ordine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ordineService.elimina(id);
    }
}
