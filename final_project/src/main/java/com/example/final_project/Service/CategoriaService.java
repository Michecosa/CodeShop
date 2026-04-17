package com.example.final_project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.final_project.Model.Categoria;
import com.example.final_project.Repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> trovaTutte() {
        return categoriaRepository.findAll();
    }

    public Categoria trovaPerID(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con id: " + id));
    }

    public Categoria crea(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("Il nome della categoria è obbligatorio");
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria aggiorna(Long id, Categoria datiAggiornati) {
        Categoria esistente = trovaPerID(id);
        if (datiAggiornati.getNome() != null && !datiAggiornati.getNome().isBlank()) {
            esistente.setNome(datiAggiornati.getNome());
        }
        return categoriaRepository.save(esistente);
    }

    public void elimina(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria non trovata con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
