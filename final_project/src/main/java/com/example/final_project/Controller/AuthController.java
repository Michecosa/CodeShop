package com.example.final_project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.final_project.Model.Carrello;
import com.example.final_project.Model.Ruolo;
import com.example.final_project.Model.Utente;
import com.example.final_project.Repository.CarrelloRepository;
import com.example.final_project.Repository.UtenteRepository;
import com.example.final_project.Security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder criptatore;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private CarrelloRepository carrelloRepository;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public void register(@RequestBody Utente utente) {
        utente.setPassword(criptatore.encode(utente.getPassword()));

        // Fix: invece di utente.setRoles("ROLE_USER") creiamo un oggetto Ruolo
        // Il cascade su Utente.ruoli propagherà il salvataggio automaticamente
        Ruolo ruoloUser = new Ruolo();
        ruoloUser.setNome("ROLE_USER");
        ruoloUser.setUtente(utente);
        utente.getRuoli().add(ruoloUser);

        utente = utenteRepository.save(utente);

        Carrello c = new Carrello();
        c.setUtente(utente);
        carrelloRepository.save(c);
    }

    @PostMapping("/login")
    public String login(@RequestBody Utente utente) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(utente.getUsername(), utente.getPassword()));
        return jwtService.generateToken(auth);
    }

    @GetMapping("/me")
    public Utente me(Authentication auth) {
        return utenteRepository.findByUsername(auth.getName());
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody java.util.Map<String, String> body, Authentication auth) {
        String currentPassword = body.get("currentPassword");
        String newPassword = body.get("newPassword");

        Utente utente = utenteRepository.findByUsername(auth.getName());
        if (!criptatore.matches(currentPassword, utente.getPassword())) {
            return ResponseEntity.badRequest().body("Password attuale non corretta");
        }

        utente.setPassword(criptatore.encode(newPassword));
        utenteRepository.save(utente);
        return ResponseEntity.ok("Password aggiornata");
    }
}