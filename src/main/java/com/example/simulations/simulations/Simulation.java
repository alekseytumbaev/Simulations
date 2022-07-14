package com.example.simulations.simulations;

import java.util.List;

public interface Simulation {

    void makeOneStep();

    void reset();

    List<Ball> getBalls();

    double getWidth();

    double getHeight();
}
