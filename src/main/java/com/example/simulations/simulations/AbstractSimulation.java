package com.example.simulations.simulations;

public abstract class AbstractSimulation implements Simulation {

    private final int width;
    private final int height;

    protected AbstractSimulation(int width, int height) {
        if (width < 1 || height < 1)
            throw new IllegalArgumentException("Width or height was less than one.");
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
