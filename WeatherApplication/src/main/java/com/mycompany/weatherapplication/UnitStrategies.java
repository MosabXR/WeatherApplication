/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.weatherapplication;

/**
 * A container class for defining and organizing unit conversion strategies.
 * Strategies implement the `UnitStrategy` interface to handle different types of unit conversions.
 */
public class UnitStrategies {
}

/**
 * Interface representing a unit conversion strategy.
 */
interface UnitStrategy {
    String convert(Data data);
}

/**
 * A strategy for converting temperature values to Celsius format.
 */
class CelsiusStrategy implements UnitStrategy {
    @Override
    public String convert(Data data) {
        // Format the Celsius temperature with 2 decimal places
        return String.format("%.2f °C", data.current.temp_c);
    }
}

/**
 * A strategy for converting temperature values to Fahrenheit format.
 */
class FahrenheitStrategy implements UnitStrategy {
    @Override
    public String convert(Data data) {
        // Format the Fahrenheit temperature with 2 decimal places
        return String.format("%.2f °F", data.current.temp_f);
    }
}

/**
 * A strategy for converting wind speed values to kilometers per hour (KPH) format.
 */
class KphStrategy implements UnitStrategy {
    @Override
    public String convert(Data data) {
        // Format the wind speed in KPH with 2 decimal places
        return String.format("%.2f KPH", data.current.wind_kph);
    }
}

/**
 * A strategy for converting wind speed values to miles per hour (MPH) format.
 */
class MphStrategy implements UnitStrategy {
    @Override
    public String convert(Data data) {
        // Format the wind speed in MPH with 2 decimal places
        return String.format("%.2f MPH", data.current.wind_mph);
    }
}
