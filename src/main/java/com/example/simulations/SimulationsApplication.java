package com.example.simulations;

import com.example.simulations.simulations.sir.Person;
import com.example.simulations.simulations.sir.SIRSimulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimulationsApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Simulations!");

        SIRSimulation sir = new SIRSimulation(800,600,50);
        Canvas canvas = new Canvas(sir.getWidth(), sir.getHeight());
        StackPane stackPane = new StackPane(canvas);

        stage.setScene(new Scene(stackPane));

        stage.show();

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10),
                e -> drawSIRSimulation(canvas.getGraphicsContext2D(), sir)));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }

    private void drawSIRSimulation (GraphicsContext gc, SIRSimulation sir) {
        //draw field
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,sir.getWidth(),sir.getHeight());

        //draw people
        sir.getPeople().forEach(p -> {
            switch (p.getStatus()) {
                case SUSCEPTIBLE -> gc.setFill(Color.GRAY);
                case INFECTED -> gc.setFill(Color.RED);
                case RECOVERED -> gc.setFill(Color.BLUE);
            }
            gc.fillOval(p.getX(), p.getY(), Person.getRadius(), Person.getRadius());
        });

        sir.makeOneStep();
    }

    public static void main(String[] args) {
        launch();
    }

}