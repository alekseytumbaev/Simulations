package com.example.simulations.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.function.UnaryOperator;

public class ChooseSimulationController {

    @FXML
    private TextField sirWidth;

    @FXML
    private TextField sirHeight;

    @FXML
    private TextField sirPopulation;

    @FXML
    private CheckBox sirComputePopulation;

    @FXML
    private Slider sirPercentOfPeoplePracticingSocialDistancing;

    @FXML
    private Button sirStartNewSimulation;

    @FXML
    public void initialize() {
        UnaryOperator<TextFormatter.Change> digitsFilter = change -> {
            String text = change.getControlNewText();
            return text.matches("\\d*") ? change : null;
        };

        sirWidth.setTextFormatter(new TextFormatter<String>(digitsFilter));
        sirHeight.setTextFormatter(new TextFormatter<String>(digitsFilter));
        sirPopulation.setTextFormatter(new TextFormatter<String>(digitsFilter));

        sirPopulation.disableProperty().bind(sirComputePopulation.selectedProperty());

        sirStartNewSimulation.disableProperty().bind(
                sirWidth.textProperty().isEmpty().
                or(sirHeight.textProperty().isEmpty()).
                or(sirPopulation.textProperty().isEmpty().and(sirComputePopulation.selectedProperty().not())));
    }

    @FXML
    public void sirTryToStartNewSimulation() {
        int width = 0, height = 0, population = 0;
        try {
            width = Integer.parseInt(sirWidth.getText());
            height = Integer.parseInt(sirHeight.getText());
            population = !sirComputePopulation.isSelected() ? Integer.parseInt(sirPopulation.getText()) : 0;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "SIR width, height or population is too big.");
            alert.showAndWait();
        }

        if (sirComputePopulation.isSelected()) {
            if (width == 0 || height == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "SIR width or height must be positive.");
                alert.showAndWait();
            } else {
                // new SIRSimulation(width,height,(int) sirPercentOfPeoplePracticingSocialDistancing.getValue());
            }

        } else {
            if (width == 0 || height == 0 || population == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "SIR width, height or population must be positive.");
                alert.showAndWait();
            } else {
                // new SIRSimulation(width,height,population,(int) sirPercentOfPeoplePracticingSocialDistancing.getValue());
            }
        }
    }
}
