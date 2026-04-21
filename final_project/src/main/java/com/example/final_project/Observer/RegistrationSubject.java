package com.example.final_project.Observer;

import com.example.final_project.Model.Utente;

public interface RegistrationSubject {
    void addRegistrationObserver(RegistrationObserver observer);
    void removeRegistrationObserver(RegistrationObserver observer);
    void notifyRegistrationObservers(Utente utente);
}
