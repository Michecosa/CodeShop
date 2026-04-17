package com.example.final_project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.final_project.Model.Carrello;
import com.example.final_project.Model.ItemQuantity;
import com.example.final_project.Repository.CarrelloRepository;
import com.example.final_project.Repository.ProdottoRepository;
import com.example.final_project.Repository.UtenteRepository;

@Service
public class CarrelloService {

    @Autowired
    CarrelloRepository carrelloRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    ProdottoRepository prodottoRepository;

    public Carrello aggiungiProdotto(String username, Long idProdotto, int qtn) {
        Carrello carrello = carrelloRepository.findByUtente_Username(username);
        if (carrello == null) {
            carrello = new Carrello();
            carrello.setUtente(utenteRepository.findByUsername(username));
        }

        ItemQuantity item = carrello.productAlreadyPresent(prodottoRepository.findById(idProdotto).orElseThrow());
        if (item != null) {
            item.setQtn(item.getQtn() + qtn);
        } else {
            item = new ItemQuantity();
            item.setProdotto(prodottoRepository.findById(idProdotto).orElseThrow());
            item.setQtn(qtn);
            item.setCarrello(carrello);
            carrello.getItems().add(item);
        }
        return carrelloRepository.save(carrello);
    }

    public Carrello rimuoviProdotto(String username, Long idProdotto) {
        Carrello carrello = carrelloRepository.findByUtente_Username(username);
        if (carrello == null) throw new RuntimeException("Carrello non trovato");
        ItemQuantity item = carrello.productAlreadyPresent(prodottoRepository.findById(idProdotto).orElseThrow());
        if (item != null) {
            carrello.getItems().remove(item);
            return carrelloRepository.save(carrello);
        }
        return carrello;
    }

    public List<ItemQuantity> getItemsByCarrelloId(Long idCarrello) {
        Carrello carrello = carrelloRepository.findById(idCarrello).orElseThrow();
        return carrello.getItems();
    }

    public Carrello svuotaCarrello(String username) {
        Carrello carrello = carrelloRepository.findByUtente_Username(username);
        if (carrello == null) throw new RuntimeException("Carrello non trovato");
        carrello.getItems().clear();
        return carrelloRepository.save(carrello);
    }

    public Carrello getCarrelloByUsername(String username) {
        Carrello carrello = carrelloRepository.findByUtente_Username(username);
        if (carrello == null) throw new RuntimeException("Carrello non trovato");
        return carrello;
    }

    public Carrello salvaCarrello() {
        Carrello carrello = new Carrello();
        return carrelloRepository.save(carrello);
    }

}
