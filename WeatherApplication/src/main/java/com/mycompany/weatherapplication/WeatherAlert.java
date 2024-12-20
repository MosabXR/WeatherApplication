package com.mycompany.weatherapplication;

/**
 * Represents a weather alert with details such as type, headline, description, and instructions.
 * Uses the Builder design pattern to facilitate the creation of WeatherAlert objects.
 */
class WeatherAlert {

    // Final fields for the immutable WeatherAlert object
    private final String type;
    private final String headline;
    private final String description;
    private final String instruction;   

    /**
     * Private constructor to enforce the use of the builder for object creation.
     * @param builder The builder object containing the data to construct the WeatherAlert.
     */
    private WeatherAlert(AlertWeatherBuilder builder) {
        this.type = builder.type;
        this.headline = builder.headline;
        this.description = builder.description;
        this.instruction = builder.instruction;
    }

    /**
     * Retrieves the type of the weather alert.
     * @return Type of the alert.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the headline of the weather alert.
     * @return Headline of the alert.
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * Retrieves the description of the weather alert.
     * @return Description of the alert.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the instruction associated with the weather alert.
     * @return Instructions for the alert.
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Provides a concise string representation of the WeatherAlert.
     * @return A string with the headline and description.
     */
    @Override
    public String toString() {
        return "Alert: " + headline + "\nDescription: " + description;
    }

    /**
     * Builder class for creating WeatherAlert instances.
     */
    public static class AlertWeatherBuilder {
        // Fields for the builder class matching WeatherAlert's fields
        private String type;
        private String headline;
        private String description;
        private String instruction;

        /**
         * Sets the type of the weather alert.
         * @param type Type of the alert (e.g., storm, flood).
         * @return The current instance of the builder for method chaining.
         */
        public AlertWeatherBuilder setType(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the headline of the weather alert.
         * @param headline Headline of the alert.
         * @return The current instance of the builder for method chaining.
         */
        public AlertWeatherBuilder setHeadline(String headline) {
            this.headline = headline;
            return this;
        }

        /**
         * Sets the description of the weather alert.
         * @param description Description of the alert.
         * @return The current instance of the builder for method chaining.
         */
        public AlertWeatherBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the instruction for the weather alert.
         * @param instruction Instructions for the alert.
         * @return The current instance of the builder for method chaining.
         */
        public AlertWeatherBuilder setInstruction(String instruction) {
            this.instruction = instruction;
            return this;
        }

        /**
         * Builds the WeatherAlert object using the data provided to the builder.
         * @return A new instance of WeatherAlert.
         */
        public WeatherAlert build() {
            return new WeatherAlert(this);
        }
    }
}
