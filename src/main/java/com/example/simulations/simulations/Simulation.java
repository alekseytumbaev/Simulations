package com.example.simulations.simulations;

import java.util.List;

public interface Simulation {

    void makeOneStep();

    List<Ball> getBalls();

    double getWidth();

    double getHeight();
}
