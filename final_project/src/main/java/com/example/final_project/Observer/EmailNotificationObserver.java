package com.example.final_project.Observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.final_project.Model.Ordine;
import com.example.final_project.Model.Utente;

@Component
public class EmailNotificationObserver implements OrderObserver, UserObserver, RegistrationObserver {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Override
    public void update(Utente utente) {
        if (mailSender == null) {
            System.out.println(
                    "JavaMailSender non configurato. Notifica cambio password simulata per: " + utente.getUsername());
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(utente.getMail());
            message.setSubject("Sicurezza Account: Password Modificata");
            message.setText("Gentile " + utente.getUsername() + ",\n\n" +
                    "Ti confermiamo che la password del tuo account è stata modificata con successo.\n" +
                    "Se non hai richiesto tu questa modifica, ti preghiamo di contattare immediatamente il supporto.\n\n"
                    +
                    "Cordiali saluti,\nLo staff di Final Project");

            mailSender.send(message);
            System.out.println("Email di cambio password inviata a: " + utente.getMail());
        } catch (Exception e) {
            System.err.println("Errore durante l'invio dell'email: " + e.getMessage());
        }
    }

    @Override
    public void onRegistrazione(Utente utente) {
        if (mailSender == null) {
            System.out.println("JavaMailSender non configurato. Benvenuto simulato per: " + utente.getUsername());
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(utente.getMail());
            message.setSubject("Benvenuto su Final Project!");
            message.setText("Gentile " + utente.getUsername() + ",\n\n" +
                    "La registrazione al nostro sito è avvenuta con successo.\n" +
                    "Ora puoi accedere al tuo account e iniziare a fare acquisti.\n\n" +
                    "Cordiali saluti,\nLo staff di Final Project");

            mailSender.send(message);
            System.out.println("Email di benvenuto inviata a: " + utente.getMail());
        } catch (Exception e) {
            System.err.println("Errore durante l'invio dell'email di benvenuto: " + e.getMessage());
        }
    }

    @Override
    public void update(Ordine ordine) {
        if (mailSender == null) {
            System.out.println("JavaMailSender non configurato. Notifica simulata per l'ordine #" + ordine.getId());
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(ordine.getUtente().getMail());
            message.setSubject("Conferma Ordine #" + ordine.getId());
            message.setText("Gentile " + ordine.getUtente().getUsername() + ",\n\n" +
                    "Il tuo ordine è stato ricevuto con successo!\n" +
                    "Indirizzo di consegna: " + ordine.getIndirizzo() + "\n" +
                    "Data prevista: " + ordine.getConsegna() + "\n" +
                    "Totale ordine: €" + String.format("%.2f", ordine.getTotale()) + "\n\n" +
                    "Grazie per averci scelto!");

            mailSender.send(message);
            System.out.println("Email inviata con successo a: " + ordine.getUtente().getMail());
        } catch (Exception e) {
            System.err.println("Errore durante l'invio dell'email: " + e.getMessage());
        }
    }
}
