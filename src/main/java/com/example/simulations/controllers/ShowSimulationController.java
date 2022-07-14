package com.example.simulations.controllers;

import com.example.simulations.simulations.Simulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ShowSimulationController {

    private Simulation simulation;
    private Stage stage;
    private Timeline timeline;

    @FXML
    private Label infoLabel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Canvas canvas;

    public void showSimulation (Simulation simulation) {
        this.simulation = simulation;

        canvas.setWidth(simulation.getWidth());
        canvas.setHeight(simulation.getHeight());
        borderPane.setPrefSize(simulation.getWidth(), simulation.getHeight() + 40);

        timeline = new Timeline(new KeyFrame(Duration.millis(10),
                e -> drawSIRSimulation(canvas.getGraphicsContext2D(), simulation)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void drawSIRSimulation (GraphicsContext gc, Simulation simulation) {
        //draw field
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,simulation.getWidth(),simulation.getHeight());

        //draw people
        simulation.getBalls().forEach(p -> {
            gc.setFill(p.getColor());
            gc.fillOval(p.getX(), p.getY(),  p.getRadius(), p.getRadius());
        });

        infoLabel.setText(simulation.getCurrentInfo());
        simulation.makeOneStep();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void onClickChooseAnotherSim() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/simulations/choose-simulation-view.fxml"));
        Parent root;
        try {
            root = loader.load();
            timeline.stop();
        } catch (IOException | IllegalStateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't open window for choosing simulations." +
                    "\nError message: " + e.getMessage());
            alert.showAndWait();
            return;
        }

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onClickReset() {
        simulation.reset();
    }
}
