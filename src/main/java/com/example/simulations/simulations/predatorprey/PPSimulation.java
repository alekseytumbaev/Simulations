package com.example.simulations.simulations.predatorprey;

import com.example.simulations.simulations.AbstractSimulation;
import com.example.simulations.simulations.Ball;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PPSimulation extends AbstractSimulation {

    private final List<Ball> balls = new LinkedList<>();
    private final List<Deer> deer = new LinkedList<>();
    private final List<Wolf> wolves = new LinkedList<>();

    public PPSimulation() {
        super(PPConfig.WIDTH(), PPConfig.HEIGHT());
        reset();
    }

    @Override
    public void makeOneStep() {

        wolves.forEach(w -> deer.forEach(w::hunt));

        //deer
        List<Deer> newbornDeer = new LinkedList<>();
        Iterator<Deer> dit = deer.iterator();

        while (dit.hasNext()) {
            Deer d = dit.next();

            d.move(deer,newbornDeer);

            if (d.getX() > getWidth() - d.getRadius() || d.getX() == 0)
                d.invertVelocityX();

            if (d.getY() > getHeight() - d.getRadius() || d.getY() == 0)
                d.invertVelocityY();

            if (d.isDead())
                dit.remove();
        }
        deer.addAll(newbornDeer);

        //wolves
        List<Wolf> newbornWolves = new LinkedList<>();
        Iterator<Wolf> wit = wolves.iterator();

        while (wit.hasNext()) {
            Wolf w = wit.next();

            w.move(newbornWolves);

            if (w.getX() > getWidth() - w.getRadius() || w.getX() == 0)
                w.invertVelocityX();

            if (w.getY() > getHeight() - w.getRadius() || w.getY() == 0)
                w.invertVelocityY();

            if (w.isDead())
                wit.remove();
        }
        wolves.addAll(newbornWolves);
    }

    @Override
    public void reset() {
        deer.clear();
        wolves.clear();

        //deer
        for (int i = 0; i < 20; i++)
            deer.add(new Deer(getWidth()/2.4,getHeight()/2.4));

        for (int i = 0; i < 20; i++)
            deer.add(new Deer(getWidth()/1.5,getHeight()/2.4));

        for (int i = 0; i < 20; i++)
            deer.add(new Deer(getWidth()/2.4,getHeight()/1.5));

        for (int i = 0; i < 20; i++)
            deer.add(new Deer(getWidth()/1.5,getHeight()/1.5));

        //wolves
        for (int i = 0; i < 10; i++)
            wolves.add(new Wolf(getWidth()/2, getHeight()/2));
    }

    @Override
    public List<Ball> getBalls() {
        balls.clear();
        balls.addAll(deer);
        balls.addAll(wolves);
        return balls;
    }
}
