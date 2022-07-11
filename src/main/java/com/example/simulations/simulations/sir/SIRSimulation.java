package com.example.simulations.simulations.sir;

import com.example.simulations.simulations.AbstractSimulation;
import com.example.simulations.simulations.Ball;

import java.util.ArrayList;
import java.util.List;

public class SIRSimulation extends AbstractSimulation {

    //TODO отдавать новый список balls или один и тот же?
    private final List<Ball> balls;
    private final List<Person> people;

    public SIRSimulation(int width, int height, int population, int percentOfPeoplePracticingSocialDistancing) {
        super(width,height);
        if (population < 1)
            throw new IllegalArgumentException("Population was less than one.");


        //initializing people
        people = new ArrayList<>(population);

        int pOPPSD = percentOfPeoplePracticingSocialDistancing;
        if (pOPPSD > 100) //if percent > 100 -> percent = 100
            pOPPSD = 100;
        else // if percent < 0 -> percent = 0
            pOPPSD = Math.max(0, pOPPSD);

        for (int i = 0; i < population; i++) {
            people.add(new Person(width, height, i < population * pOPPSD / 100));
        }
        people.get(people.size() - 1).setStatus(PersonStatus.INFECTED); //infect the last person


        balls = new ArrayList<>(people);
    }

    public SIRSimulation(int width, int height, int percentOfPeoplePracticingSocialDistancing) {
        this(width,
             height,
             width * height < 3600 ? 1 : (width * height) / 3600,
             percentOfPeoplePracticingSocialDistancing);
    }

    @Override
    public void makeOneStep() {
        people.forEach( p -> {
            p.move();

            if(p.getX() > getWidth() - p.getRadius() || p.getX() < 0)
                p.invertVelocityX();

            if(p.getY() > getHeight() - p.getRadius() || p.getY() < 0)
                p.invertVelocityY();

            people.forEach(p2 -> {
                if (p != p2)
                    p.collision(p2);
            });
        });
    }

    @Override
    public List<Ball> getBalls() {
        return balls;
    }
}
