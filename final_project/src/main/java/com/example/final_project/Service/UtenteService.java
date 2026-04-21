package com.example.final_project.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.final_project.Exception.CreazioneUtenteMalformataException;
import com.example.final_project.Exception.UtenteNonEsistenteException;
import com.example.final_project.Model.Ruolo;
import com.example.final_project.Model.Utente;
import com.example.final_project.Repository.UtenteRepository;
import com.example.final_project.Observer.UserObserver;
import com.example.final_project.Observer.UserSubject;

@Service
public class UtenteService implements UserDetailsService, UserSubject {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    @org.springframework.context.annotation.Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private List<UserObserver> observers = new ArrayList<>();

    @Override
    public void addUserObserver(UserObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeUserObserver(UserObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyUserObservers(Utente utente) {
        for (UserObserver observer : observers) {
            observer.update(utente);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato: " + username);
        }

        List<SimpleGrantedAuthority> authorities = utente.getRuoli().stream()
                .map(r -> new SimpleGrantedAuthority(r.getNome()))
                .toList();

        return new User(utente.getUsername(), utente.getPassword(), authorities);
    }

    public List<Utente> trovaTutti() {
        return utenteRepository.findAll();
    }

    public Utente trovaPerUsername(String username) {
        Utente utente = utenteRepository.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonEsistenteException("Nessun utente con username: " + username);
        }
        return utente;
    }

    public Utente trovaPerID(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new UtenteNonEsistenteException("Nessun utente con id: " + id));
    }

    public Utente crea(Utente utente) {
        if (utente.getUsername() == null || utente.getUsername().isBlank()
                || utente.getMail() == null || utente.getMail().isBlank()
                || utente.getPassword() == null || utente.getPassword().isBlank()) {
            throw new CreazioneUtenteMalformataException("Username, mail e password sono obbligatori");
        }
        if (utenteRepository.existsByUsername(utente.getUsername())) {
            throw new CreazioneUtenteMalformataException("Username già in uso: " + utente.getUsername());
        }
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));

        if (utente.getRuoli() == null || utente.getRuoli().isEmpty()) {
            Ruolo ruoloDefault = new Ruolo();
            ruoloDefault.setNome("ROLE_USER");
            ruoloDefault.setUtente(utente);
            utente.getRuoli().add(ruoloDefault);
        }

        return utenteRepository.save(utente);
    }

    public Utente aggiorna(Long id, Utente datiAggiornati) {
        Utente esistente = trovaPerID(id);
        boolean pswCambiata = false;

        if (datiAggiornati.getMail() != null && !datiAggiornati.getMail().isBlank()) {
            esistente.setMail(datiAggiornati.getMail());
        }
        if (datiAggiornati.getPassword() != null && !datiAggiornati.getPassword().isBlank()) {
            esistente.setPassword(passwordEncoder.encode(datiAggiornati.getPassword()));
            pswCambiata = true;
        }

        Utente saved = utenteRepository.save(esistente);
        if (pswCambiata) {
            notifyUserObservers(saved);
        }
        return saved;
    }

    public void elimina(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new UtenteNonEsistenteException("Nessun utente con id: " + id);
        }
        utenteRepository.deleteById(id);
    }
}