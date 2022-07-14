package com.example.simulations.simulations.sir;

import com.example.simulations.simulations.AbstractBall;
import javafx.scene.paint.Color;

class Person extends AbstractBall {

    private static int susceptibleCount;
    private static int infectedCount;
    private static int recoveredCount;

    private PersonStatus status;
    private int recoveryTime;

    Person(double radius, double x, double y, boolean practiceSocialDistancing) {
        super (radius, x, y, Color.GRAY);

        reset();

        if (!practiceSocialDistancing) {
            // 0.4 <= velocityX < 0.7 or -0.4 <= velocityX < -0.7
            velocityX = Math.random() * (0.7 - 0.4) + 0.4;
            if (Math.random() > 0.5)
                invertVelocityX();

            // 0.4 <= velocityY < 0.7 or -0.4 <= velocityY < -0.7
            velocityY = Math.random() * (0.7 - 0.4) + 0.4;
            if (Math.random() > 0.5)
                invertVelocityY();
        }
    }

    void collision(Person p) {
        //if there is a collision between two people
        if (Math.abs(getX() - p.getX()) < getRadius() && Math.abs(getY() - p.getY()) < getRadius()) {
            //and one of them is infected - other one gets infected too
            if(this.status == PersonStatus.INFECTED && p.status == PersonStatus.SUSCEPTIBLE)
                if (Math.random() < 0.7)
                    p.setStatus(PersonStatus.INFECTED);

            else if (p.status == PersonStatus.INFECTED && this.status == PersonStatus.SUSCEPTIBLE)
                if (Math.random() < 0.7)
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
            case SUSCEPTIBLE -> {
                this.status = PersonStatus.SUSCEPTIBLE;
                setColor(Color.GRAY);
                susceptibleCount++;
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    void reset() {
        setStatus(PersonStatus.SUSCEPTIBLE);
        recoveryTime = (int) (Math.random() * (700 - 500 + 1) + 500);
    }

    static void resetCounts() {
        susceptibleCount = 0;
        infectedCount = 0;
        recoveredCount = 0;
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
