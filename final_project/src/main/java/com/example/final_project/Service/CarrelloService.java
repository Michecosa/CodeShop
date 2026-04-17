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

    public Carrello aggiungiProdotto(Long idUtente, Long idProdotto, int qtn) {
        Carrello carrello = carrelloRepository.findByUtente_Id(idUtente).orElseGet(() -> {
            Carrello c = new Carrello();
            c.setUtente(utenteRepository.findById(idUtente).orElseThrow());
            return c;
        });

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

    public Carrello rimuoviProdotto(Long idUtente, Long idProdotto) {
        Carrello carrello = carrelloRepository.findByUtente_Id(idUtente).orElseThrow();
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

    public Carrello svuotaCarrello(Long idUtente) {
        Carrello carrello = carrelloRepository.findByUtente_Id(idUtente).orElseThrow();
        carrello.getItems().clear();
        return carrelloRepository.save(carrello);
    }

    public Carrello SalvaCarrello() {
        Carrello carrello = new Carrello();
        return carrelloRepository.save(carrello);
    }

}
