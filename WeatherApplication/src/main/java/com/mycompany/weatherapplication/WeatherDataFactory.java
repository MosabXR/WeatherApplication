/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.weatherapplication;

/**
 * A marker interface representing weather-related data types.
 */
interface WeatherData {
}

// Class representing temperature data in Celsius and Fahrenheit
class Temperature implements WeatherData {

    private double[] value = {0, 0}; // Index 0: Celsius, Index 1: Fahrenheit

    /**
     * Constructor for Temperature.
     * @param temp_c Temperature in Celsius.
     * @param temp_f Temperature in Fahrenheit.
     */
    public Temperature(double temp_c, double temp_f) {
        this.value[0] = temp_c;
        this.value[1] = temp_f;
    }

    /**
     * Retrieves the temperature value.
     * @param index 0 for Celsius, 1 for Fahrenheit.
     * @return The temperature value.
     */
    public double getValue(int index) {
        return value[index];
    }
}

// Class representing humidity data as a percentage
class Humidity implements WeatherData {

    private double value; // Humidity percentage

    /**
     * Constructor for Humidity.
     * @param value Humidity value as a percentage.
     */
    public Humidity(double value) {
        this.value = value;
    }

    /**
     * Retrieves the humidity value.
     * @return Humidity percentage.
     */
    public double getValue() {
        return value;
    }
}

// Class representing wind speed in kilometers and miles per hour
class WindSpeed implements WeatherData {

    private double[] value = {0, 0}; // Index 0: km/h, Index 1: mph

    /**
     * Constructor for WindSpeed.
     * @param wind_kph Wind speed in kilometers per hour.
     * @param wind_mph Wind speed in miles per hour.
     */
    public WindSpeed(double wind_kph, double wind_mph) {
        this.value[0] = wind_kph;
        this.value[1] = wind_mph;
    }

    /**
     * Retrieves the wind speed value.
     * @param index 0 for km/h, 1 for mph.
     * @return The wind speed value.
     */
    public double getValue(int index) {
        return value[index];
    }
}

// Class representing weather condition information
class WeatherCondition implements WeatherData {

    private String weatherCondition; // Description of the weather condition
    private String weatherConditionImgUrl; // URL of the condition's image

    /**
     * Constructor for WeatherCondition.
     * @param weatherCondition Description of the weather condition.
     * @param weatherConditionImgUrl URL of the condition's image.
     */
    public WeatherCondition(String weatherCondition, String weatherConditionImgUrl) {
        this.weatherCondition = weatherCondition;
        this.weatherConditionImgUrl = weatherConditionImgUrl;
    }

    /**
     * Retrieves the weather condition description.
     * @return The weather condition description.
     */
    public String getWeatherCondition() {
        return this.weatherCondition;
    }

    /**
     * Retrieves the URL of the weather condition's image.
     * @return The image URL.
     */
    public String getWeatherConditionImgUrl() {
        return this.weatherConditionImgUrl;
    }
}

// Class representing a weather location
class WeatherLocation implements WeatherData {

    private String name;    // Name of the location
    private String region;  // Region of the location
    private String country; // Country of the location

    /**
     * Constructor for WeatherLocation.
     * @param name Name of the location.
     * @param region Region of the location.
     * @param country Country of the location.
     */
    WeatherLocation(String name, String region, String country) {
        this.name = name;
        this.region = region;
        this.country = country;
    }

    /**
     * Retrieves the name of the location.
     * @return The location name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the region of the location.
     * @return The location region.
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * Retrieves the country of the location.
     * @return The location country.
     */
    public String getCountry() {
        return this.country;
    }
}

// Factory class for creating WeatherData objects based on type
class WeatherDataFactory {

    /**
     * Creates a WeatherData object based on the specified type.
     * @param type The type of weather data to create (e.g., "Temperature").
     * @param data A data object containing the relevant weather information.
     * @return A WeatherData object of the specified type, or null if the type is invalid.
     */
    public static WeatherData createWeatherData(String type, Data data) {
        switch (type) {
            case "Temperature":
                return new Temperature(data.current.temp_c, data.current.temp_f);
            case "Humidity":
                return new Humidity(data.current.humidity);
            case "WindSpeed":
                return new WindSpeed(data.current.wind_kph, data.current.wind_mph);
            case "WeatherCondition":
                return new WeatherCondition(data.current.condition.text, data.current.condition.icon);
            case "WeatherLocation":
                return new WeatherLocation(data.location.name, data.location.region, data.location.country);
            default:
                System.out.println("Invalid Weather Datatype");
                return null;
        }
    }
}
