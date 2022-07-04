package com.example.simulations.simulations.sir;

import com.example.simulations.simulations.Simulation;

import java.util.ArrayList;
import java.util.List;

public class SIRSimulation extends Simulation {

    private final List<Person> people;

    public SIRSimulation(int width, int height, int population, int percentOfPeoplePracticingSocialDistancing) {
        if (width < 1 || height < 1)
            throw new IllegalArgumentException("Width, height was less than one.");
        if (population < 1)
            throw new IllegalArgumentException("Population was less than one.");

        this.width = width;
        this.height = height;

        people = new ArrayList<>(population);

        int pOPPSD = percentOfPeoplePracticingSocialDistancing;
        if (pOPPSD > 100) //if percent > 100 -> percent = 100
            pOPPSD = 100;
        else // if percent < 0 -> percent = 0
            pOPPSD = Math.max(0, pOPPSD);

        for (int i = 0; i < population; i++) {
            people.add(new Person(width, height, i < population * pOPPSD / 100));
        }
        people.get(people.size() - 1).setStatus(PersonStatus.INFECTED);
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

            if(p.getX() > width - Person.getRadius() || p.getX() < 0)
                p.invertVelocityX();

            if(p.getY() > height - Person.getRadius() || p.getY() < 0)
                p.invertVelocityY();

            people.forEach(p2 -> {
                if (p != p2)
                    p.collision(p2);
            });
        });
    }

    public List<Person> getPeople() {
        return people;
    }
}
