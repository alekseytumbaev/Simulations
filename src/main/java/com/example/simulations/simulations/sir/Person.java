package com.example.simulations.simulations.sir;

import com.example.simulations.simulations.Ball;
import javafx.scene.paint.Color;

public class Person extends Ball {

    private static int susceptibleCount = 0;
    private static int infectedCount = 0;
    private static int recoveredCount = 0;

    private PersonStatus status;
    public final boolean practiceSocialDistancing;
    private int recoveryTime;

    Person(int simulationWidth, int simulationHeight, boolean practiceSocialDistancing) {
        super (15);

        if (simulationWidth < 1 || simulationHeight < 1)
            throw new IllegalArgumentException("Width or height was less than one.");

        setStatus(PersonStatus.SUSCEPTIBLE);
        recoveryTime = (int) (Math.random() * (700 - 500 + 1) + 500);
        this.practiceSocialDistancing = practiceSocialDistancing;

        //coordinates and velocity
        x = Math.random() * (simulationWidth - radius);  // 0 <= x < (width - radius)
        y = Math.random() * (simulationHeight - radius); // 0 <= y < (height - radius)

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
        if(Math.abs(this.x - p.x) < radius && Math.abs(this.y - p.y) < radius) {
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

        x += velocityX;
        y += velocityY;
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
                color = Color.RED;
                infectedCount++;
                susceptibleCount--;
                return true;
            }
            case RECOVERED -> {
                if (this.status != PersonStatus.INFECTED)
                    return false;
                this.status = PersonStatus.RECOVERED;
                color = Color.BLUE;
                recoveredCount++;
                infectedCount--;
                return true;
            }
            default -> {
                if (this.status != null) //if simulation is not new
                    return false;
                this.status = PersonStatus.SUSCEPTIBLE;
                color = Color.GRAY;
                susceptibleCount++;
                return true;
            }
        }
    }

    //getters
    public PersonStatus getStatus() {
        return status;
    }

    public static int getSusceptibleCount() {
        return susceptibleCount;
    }

    public static int getInfectedCount() {
        return infectedCount;
    }

    public static int getRecoveredCount() {
        return recoveredCount;
    }
}
