/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.weatherapplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A singleton class responsible for fetching weather data from a weather API.
 */
public class WeatherDataFetcher {

    // Singleton instance of the WeatherDataFetcher class
    public static WeatherDataFetcher instance = new WeatherDataFetcher();

    // Private constructor to enforce the singleton pattern
    private WeatherDataFetcher() {
    }

    public static WeatherDataFetcher getInstance() {
        return instance;
    }

    /**
     * Fetches weather data for the specified city by making an HTTP GET request to a weather API.
     * @param city The name of the city for which to fetch weather data.
     * @return A JSON string containing the weather data, or null if an error occurs.
     */
    public static String fetchWeatherData(String city) {
        try {
            // API key for the weather service
            String apiKey = "47fa130a980a43258cc222102242911";
            
            // Construct the API URL with the city and API key
            String urlString = "https://api.weatherapi.com/v1/forecast.json?key=" + apiKey 
                    + "&days=3&aqi=no&alerts=yes&lang=en&q=" + city;
            
            // Create a URL object from the constructed URL string
            URL url = new URL(urlString);
            
            // Open an HTTP connection to the API endpoint
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the API response using a BufferedReader
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            // Append each line of the response to the StringBuilder
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Return the API response as a string
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }
    }
}
