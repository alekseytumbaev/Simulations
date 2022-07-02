package com.example.simulations;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimulationsApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Simulations!");

        Canvas canvas = new Canvas(400,400);
        StackPane stackPane = new StackPane(canvas);

        stage.setScene(new Scene(stackPane));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}