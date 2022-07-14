package com.example.simulations.simulations;

import java.util.List;

public interface Simulation {

    void makeOneStep();

    void reset();

    List<Ball> getBalls();

    String getCurrentInfo();

    double getWidth();

    double getHeight();
}
