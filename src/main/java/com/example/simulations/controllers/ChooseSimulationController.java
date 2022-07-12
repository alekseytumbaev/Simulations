package com.example.simulations.controllers;

import com.example.simulations.simulations.Simulation;
import com.example.simulations.simulations.predatorprey.PPSimulation;
import com.example.simulations.simulations.sir.SIRSimulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.UnaryOperator;

public class ChooseSimulationController {
//*********************************SIR SIMULATION**********************************************************
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
//***********************************************************************************************************

//*********************************PREDATOR-PREY SIMULATION**********************************************************
    @FXML
    private Button ppStartNewSimulation;
//***********************************************************************************************************

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
    public void sirOnClickStartNewSimulation(ActionEvent event) {
        int width, height, population;
        boolean computeSelected = sirComputePopulation.isSelected();

        //check if width, height or population is too big for integer.
        try {
            width = Integer.parseInt(sirWidth.getText());
            height = Integer.parseInt(sirHeight.getText());
            population = !computeSelected ? Integer.parseInt(sirPopulation.getText()) : -1;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "SIR width, height or population is too big.");
            alert.showAndWait();
            return;
        }

        //check if smth is zero
        if (computeSelected) {
            if (width == 0 || height == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "SIR width and height must be positive.");
                alert.showAndWait();
                return;
            }
        } else {
            if (width == 0 || height == 0 || population == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "SIR width, height and population must be positive.");
                alert.showAndWait();
                return;
            }
        }

        Simulation sir;
        if (computeSelected)
            sir = new SIRSimulation(width,height, (int) sirPercentOfPeoplePracticingSocialDistancing.getValue());
        else
            sir = new SIRSimulation(width,height,population,(int) sirPercentOfPeoplePracticingSocialDistancing.getValue());

        openShowSimulationView(sir, event);
    }

    @FXML
    public void ppOnClickStartNewSimulation(ActionEvent event) {
        openShowSimulationView(new PPSimulation(500,400), event);

    }

    private void openShowSimulationView(Simulation simulation, ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/simulations/show-simulation-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException | IllegalStateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't open simulation." +
                    "\nError message: " + e.getMessage());
            alert.showAndWait();
            return;
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        ShowSimulationController showSimulation = loader.getController();
        showSimulation.showSimulation(simulation);
        showSimulation.setStage(stage);

        stage.setScene(new Scene(root));
        stage.show();
    }
}
