package com.example.final_project.Observer;

import com.example.final_project.Model.Ordine;

public interface OrderSubject {
    void addObserver(OrderObserver observer);

    void removeObserver(OrderObserver observer);

    void notifyObservers(Ordine ordine);
}
