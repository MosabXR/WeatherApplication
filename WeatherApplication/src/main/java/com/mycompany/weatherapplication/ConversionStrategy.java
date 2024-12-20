/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.weatherapplication;

/**
 * Interface for temperature conversion strategies.
 * Defines a contract for converting temperature data from the `Data` object.
 */
interface TemperatureConversionStrategy {
    /**
     * Converts the temperature data using the implemented strategy.
     * @param data The `Data` object containing the current temperature readings.
     * @return The converted temperature value as a double.
     */
    double convert(Data data);
}

/**
 * Strategy for converting temperature to Celsius.
 */
class CelsiusConversionStrategy implements TemperatureConversionStrategy {
    @Override
    public double convert(Data value) {
        // Returns the temperature in Celsius from the `Data` object
        return value.current.temp_c;
    }
}

/**
 * Strategy for converting temperature to Fahrenheit.
 */
class FahrenheitConversionStrategy implements TemperatureConversionStrategy {
    @Override
    public double convert(Data value) {
        // Returns the temperature in Fahrenheit from the `Data` object
        return value.current.temp_f;
    }
}

/**
 * Placeholder class for organizing and managing different conversion strategies.
 * This class can be extended or refactored to handle additional conversion logic if needed.
 */
public class ConversionStrategy {
    // Currently, this class is empty but serves as a placeholder for future extensions.
}
