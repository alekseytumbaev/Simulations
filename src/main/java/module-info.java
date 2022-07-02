module com.example.simulations {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.simulations to javafx.fxml;
    exports com.example.simulations;
}