module com.mycompany.weatherapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson; 

    opens com.mycompany.weatherapplication to com.google.gson, javafx.fxml;
    exports com.mycompany.weatherapplication;
}
