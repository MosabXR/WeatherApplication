/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.weatherapplication;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update();
}

class WeatherDataObservable {
    private final List<Observer> observers = new ArrayList<>();
    private Data data;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setData(Data newData) {
        this.data = newData;
        notifyObservers();
    }

    public Data getData() {
        return data;
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}