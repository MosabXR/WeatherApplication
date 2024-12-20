package com.mycompany.weatherapplication;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller class responsible for handling UI events and displaying weather data.
 * Implements the Observer pattern to update the UI when weather data changes.
 */
public class Controller implements Initializable, Observer {

    // FXML UI elements
    @FXML
    private TextField cityName; // User input for the city name
    @FXML
    private Label cityLabel; // Label displaying the city name
    @FXML
    private Label regionLabel; // Label displaying the region and country
    @FXML
    private Label conditionLabel; // Label displaying the weather condition
    @FXML
    private Label temperatureLabel; // Label displaying the temperature
    @FXML
    private Label humidityLabel; // Label displaying the humidity
    @FXML
    private Label windSpeedLabel; // Label displaying the wind speed
    @FXML
    private Label weatherAlertLabel; // Label displaying weather alert headline
    @FXML
    private Label weatherAlertDataLabel; // Label displaying weather alert description
    @FXML
    private ImageView conditionImage; // ImageView displaying the weather condition icon
    @FXML
    private Button updateTemperatureUnitBtn; // Button to toggle temperature units
    @FXML
    private Button updateWindSpeedUnitBtn; // Button to toggle wind speed units

    // Observable pattern setup
    private final WeatherDataObservable observable = new WeatherDataObservable();
    private UnitStrategy temperatureStrategy = new CelsiusStrategy(); // Default unit for temperature
    private UnitStrategy windSpeedStrategy = new KphStrategy(); // Default unit for wind speed

    // Gson for parsing the weather data from the API
    Gson gson = new Gson();
    Data data = null; 
    Temperature temperature = null;
    WindSpeed windSpeed = null; 
    Humidity humidity = null; 
    WeatherCondition weatherCondition = null;
    WeatherLocation weatherLocation = null; 
    Image weatherConditionImage = null;

    /**
     * Initializes the controller, sets up the observer, and fetches the initial weather data.
     * @param url The URL to the FXML file.
     * @param rb The ResourceBundle for the FXML file.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        observable.addObserver(this); // Add this controller as an observer
        fetchData(); // Fetch the weather data
    }

    /**
     * Fetches the weather data from the API based on the city name input.
     * If no city is entered, defaults to "asyut".
     */
    public void fetchData() {
        Gson gson = new Gson();
        Data data;

        // Fetch data based on the city name entered by the user
        if (cityName.getText().isEmpty()) {
            data = gson.fromJson(WeatherDataFetcher.fetchWeatherData("asyut"), Data.class);
        } else {
            data = gson.fromJson(WeatherDataFetcher.fetchWeatherData(cityName.getText()), Data.class);
        }

        // Log the raw API response for debugging
       // System.out.println("Raw API Response: " + WeatherDataFetcher.fetchWeatherData(cityName.getText()));
        
        // Set the data in the observable, which will notify the observers (this controller)
        observable.setData(data);
    }

    /**
     * Updates the UI elements when the observable data changes.
     * This method is called automatically when the observable is updated.
     */
    @Override
    public void update() {
        Data data = observable.getData();

        if (data != null) {
            // Update the city and region information
            cityLabel.setText(data.location.name);
            regionLabel.setText(data.location.region + ", " + data.location.country);

            // Update the temperature using the current unit strategy
            temperatureLabel.setText(temperatureStrategy.convert(data));

            // Update the humidity, wind speed, and weather condition
            humidityLabel.setText(data.current.humidity + " %");
            windSpeedLabel.setText(windSpeedStrategy.convert(data));
            conditionLabel.setText(data.current.condition.text);
            conditionImage.setImage(new Image("https:" + data.current.condition.icon));

            // If there are weather alerts, display them; otherwise, hide the alert labels
            if (data.alerts != null && data.alerts.alert != null && data.alerts.alert.length > 0) {
                WeatherAlert weatherAlert = new WeatherAlert.AlertWeatherBuilder()
                        .setHeadline(data.alerts.alert[0].headline)
                        .setDescription(data.alerts.alert[0].description)
                        .setInstruction(data.alerts.alert[0].instruction)
                        .build();

                // Show weather alert information
                weatherAlertLabel.setVisible(true);
                weatherAlertDataLabel.setVisible(true);
                weatherAlertLabel.setText(weatherAlert.getHeadline());
                weatherAlertDataLabel.setText(weatherAlert.getDescription());
            } else {
                // Hide weather alert information if no alert is present
                weatherAlertLabel.setVisible(false);
                weatherAlertDataLabel.setVisible(false);
            }
        }
    }

    /**
     * Toggles the temperature unit between Celsius and Fahrenheit when the button is clicked.
     */
    public void updateTemperatureUnit() {
        // Switch between Celsius and Fahrenheit strategies
        if (temperatureStrategy instanceof CelsiusStrategy) {
            temperatureStrategy = new FahrenheitStrategy();
            updateTemperatureUnitBtn.setText("Fahrenheit");
        } else {
            temperatureStrategy = new CelsiusStrategy();
            updateTemperatureUnitBtn.setText("Celsius");
        }
        // Update the temperature label with the new unit
        temperatureLabel.setText(temperatureStrategy.convert(observable.getData()));
    }

    /**
     * Toggles the wind speed unit between KPH and MPH when the button is clicked.
     */
    public void updateWindSpeedUnit() {
        // Switch between KPH and MPH strategies
        if (windSpeedStrategy instanceof KphStrategy) {
            windSpeedStrategy = new MphStrategy();
            updateWindSpeedUnitBtn.setText("MPH");
        } else {
            windSpeedStrategy = new KphStrategy();
            updateWindSpeedUnitBtn.setText("KPH");
        }
        // Update the wind speed label with the new unit
        windSpeedLabel.setText(windSpeedStrategy.convert(observable.getData()));
    }
}


//    public void fetchData() {
//
//        if (data == null) {
//            data = gson.fromJson(WeatherDataFetcher.fetchWeatherData("asyut"), Data.class);
//        } else {
//            data = gson.fromJson(WeatherDataFetcher.fetchWeatherData(cityName.getText()), Data.class);
//        }
//
//        WeatherData uncastedTemperature = WeatherDataFactory.createWeatherData("Temperature", data);
//        temperature = (Temperature) uncastedTemperature;
////        System.out.println(temperature.getValue(0));
//
//        WeatherData uncastedHumidity = WeatherDataFactory.createWeatherData("Humidity", data);
//        humidity = (Humidity) uncastedHumidity;
////        System.out.println(humidity.getValue());
//
//        WeatherData uncastedWindSpeed = WeatherDataFactory.createWeatherData("WindSpeed", data);
//        windSpeed = (WindSpeed) uncastedWindSpeed;
////        System.out.println(windSpeed.getValue(0));
//
//        WeatherData uncastedWeatherCondition = WeatherDataFactory.createWeatherData("WeatherCondition", data);
//        weatherCondition = (WeatherCondition) uncastedWeatherCondition;
////        System.out.println(weatherCondition.getWeatherCondition());
//
//        WeatherData uncastedWeatherLocation = WeatherDataFactory.createWeatherData("WeatherLocation", data);
//        weatherLocation = (WeatherLocation) uncastedWeatherLocation;
////        System.out.println(weatherLocation.getName());
////        System.out.println(weatherLocation.getRegion());
////        System.out.println(weatherLocation.getCountry());
//
//        cityName.setText("");
//        cityLabel.setText(weatherLocation.getName());
//        regionLabel.setText(weatherLocation.getRegion() + ", " + weatherLocation.getCountry());
//        temperatureLabel.setText(temperature.getValue(UserPreferencesManager.temperatureUnit) + "° C");
//        humidityLabel.setText(humidity.getValue() + " %");
//        windSpeedLabel.setText(temperature.getValue(UserPreferencesManager.windSpeedUnit) + " KPH");
//        conditionLabel.setText(weatherCondition.getWeatherCondition());
//        weatherConditionImage = new Image("https:" + weatherCondition.getWeatherConditionImgUrl());
//        conditionImage.setImage(weatherConditionImage);
//    }
//
//    public void updateTemperatureUnit() {
//        if (UserPreferencesManager.temperatureUnit == 0) {
//            UserPreferencesManager.temperatureUnit = 1;
//            temperatureLabel.setText("" + temperature.getValue(UserPreferencesManager.temperatureUnit) + "° F");
//            updateTemperatureUnitBtn.setText("Fahrenheit");
//        } else if (UserPreferencesManager.temperatureUnit == 1) {
//            UserPreferencesManager.temperatureUnit = 0;
//            temperatureLabel.setText("" + temperature.getValue(UserPreferencesManager.temperatureUnit) + "° C");
//            updateTemperatureUnitBtn.setText("Celsuis");
//        }
//    }
//
//    public void updateWindSpeedUnit() {
//        if (UserPreferencesManager.windSpeedUnit == 0) {
//            UserPreferencesManager.windSpeedUnit = 1;
//            windSpeedLabel.setText("" + temperature.getValue(UserPreferencesManager.windSpeedUnit) + "° MPH");
//            updateWindSpeedUnitBtn.setText("MPH");
//        } else if (UserPreferencesManager.windSpeedUnit == 1) {
//            UserPreferencesManager.windSpeedUnit = 0;
//            windSpeedLabel.setText("" + windSpeed.getValue(UserPreferencesManager.windSpeedUnit) + "° KPH");
//            updateWindSpeedUnitBtn.setText("KPH");
//
//        }
//    }

