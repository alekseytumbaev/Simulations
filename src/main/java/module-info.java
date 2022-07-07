module com.example.simulations {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.simulations to javafx.fxml;
    exports com.example.simulations;
    exports com.example.simulations.simulations.predatorprey;
    opens com.example.simulations.simulations.predatorprey to javafx.fxml;
    exports com.example.simulations.controllers;
    opens com.example.simulations.controllers to javafx.fxml;
}