/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.weatherapplication;



class Data {
    public Location location;
    public Current current;
    public Alerts alerts;
}

class Location {
    String name;
    String region;
    String country;
}

class Current {
    double temp_c;
    double temp_f;
    double humidity;
    double wind_kph;
    double wind_mph;
    Condition condition;
}

class Condition {
    String icon;
    String text;
}
class Alerts{
    public Alert[] alert;
}

class Alert {
    public String headline;
    public String desc;
    public String instruction;
}