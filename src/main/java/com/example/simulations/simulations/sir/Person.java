package com.example.simulations.simulations.sir;

import com.example.simulations.simulations.AbstractBall;
import javafx.scene.paint.Color;

class Person extends AbstractBall {

    private static int susceptibleCount = 0;
    private static int infectedCount = 0;
    private static int recoveredCount = 0;

    private PersonStatus status;
    private final boolean practiceSocialDistancing;
    private int recoveryTime;

    Person(int simulationWidth, int simulationHeight, boolean practiceSocialDistancing) {
        super (15,
                Math.random() * (simulationWidth - 15),  // 0 <= x < (width - radius)
                Math.random() * (simulationHeight - 15), // 0 <= y < (height - radius)
                Color.GRAY);

        setStatus(PersonStatus.SUSCEPTIBLE);
        recoveryTime = (int) (Math.random() * (700 - 500 + 1) + 500);
        this.practiceSocialDistancing = practiceSocialDistancing;


        // 0.4 <= velocityX < 0.7 or -0.4 <= velocityX < -0.7
        velocityX = Math.random() * (0.7 - 0.4) + 0.4;
        if (Math.random() > 0.5)
            invertVelocityX();

        // 0.4 <= velocityY < 0.7 or -0.4 <= velocityY < -0.7
        velocityY = Math.random() * (0.7 - 0.4) + 0.4;
        if (Math.random() > 0.5)
            invertVelocityY();
    }

    void collision(Person p) {
        //if there is a collision between two people
        if(Math.abs(getX() - p.getX()) < getRadius() && Math.abs(getY() - p.getY()) < getRadius()) {
            //and one of them is infected - other one gets infected too
            if(this.status == PersonStatus.INFECTED && p.status == PersonStatus.SUSCEPTIBLE)
                p.setStatus(PersonStatus.INFECTED);
            else if (p.status == PersonStatus.INFECTED && this.status == PersonStatus.SUSCEPTIBLE)
                this.setStatus(PersonStatus.INFECTED);
        }
    }

    void move() {
        if (status == PersonStatus.INFECTED) {
            if (recoveryTime == 0)
                setStatus(PersonStatus.RECOVERED);
            else
                recoveryTime--;
        }

        if (practiceSocialDistancing)
            return;

        moveAlongX();
        moveAlongY();
    }

    void invertVelocityX() {
        velocityX *= -1;
    }

    void invertVelocityY() {
        velocityY *= -1;
    }

    boolean setStatus(PersonStatus status) {
        switch (status) {
            case INFECTED -> {
                if (this.status != PersonStatus.SUSCEPTIBLE)
                    return false;
                this.status = PersonStatus.INFECTED;
                setColor(Color.RED);
                infectedCount++;
                susceptibleCount--;
                return true;
            }
            case RECOVERED -> {
                if (this.status != PersonStatus.INFECTED)
                    return false;
                this.status = PersonStatus.RECOVERED;
                setColor(Color.BLUE);
                recoveredCount++;
                infectedCount--;
                return true;
            }
            default -> {
                if (this.status != null) //if simulation is not new
                    return false;
                this.status = PersonStatus.SUSCEPTIBLE;
                setColor(Color.GRAY);
                susceptibleCount++;
                return true;
            }
        }
    }

    static int getSusceptibleCount() {
        return susceptibleCount;
    }

    static int getInfectedCount() {
        return infectedCount;
    }

    static int getRecoveredCount() {
        return recoveredCount;
    }
}
