package com.example.simulations.controllers;

import com.example.simulations.simulations.sir.Person;
import com.example.simulations.simulations.sir.SIRSimulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ShowSimulationController {

    public void showSimulation (SIRSimulation simulation) {
        Canvas canvas = new Canvas(simulation.getWidth(), simulation.getHeight());
        StackPane stackPane = new StackPane(canvas);

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10),
                e -> drawSIRSimulation(canvas.getGraphicsContext2D(), simulation)));
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
}
