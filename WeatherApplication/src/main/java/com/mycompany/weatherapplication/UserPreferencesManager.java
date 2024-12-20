/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.weatherapplication;


public class UserPreferencesManager {

    private static UserPreferencesManager instance = new UserPreferencesManager();;
    public static int temperatureUnit = 0;
    public static int windSpeedUnit = 0;

    private UserPreferencesManager() {
        // Private constructor to prevent instantiation
    }

    public static UserPreferencesManager getInstance() {
        return instance;
    }

}

