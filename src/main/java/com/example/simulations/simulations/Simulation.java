package com.example.simulations.simulations;

public abstract class Simulation {

    protected int width;
    protected int height;

    public abstract void makeOneStep();

    //setters
    public boolean setWidth(int width) {
        if (width < 1)
            return false;

        this.width = width;
        return true;
    }

    public boolean setHeight(int height) {
        if (width < 1)
            return false;

        this.height = height;
        return true;
    }

    //getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}