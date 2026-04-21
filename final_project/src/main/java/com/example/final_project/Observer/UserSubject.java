package com.example.final_project.Observer;

import com.example.final_project.Model.Utente;

public interface UserSubject {
    void addUserObserver(UserObserver observer);
    void removeUserObserver(UserObserver observer);
    void notifyUserObservers(Utente utente);
}
