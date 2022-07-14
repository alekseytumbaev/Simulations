package com.example.simulations.simulations.predatorprey;

class PPConfig {

    public static double WIDTH() {return 800;}
    public static double HEIGHT() {return 500;}

//*********************************************WOLF**********************************************
    public static double W_RADIUS() {return 10;}
    public static double W_VELOCITY() {return Math.random() * (0.9 - 0.5) + 0.5;}
    public static int W_LIFETIME() {return 700;}
    public static int W_BREEDING_PERIOD() {return 100;}
    public static int W_CUBS_PER_BIRTH() {return (int) (Math.random() * (3 - 2) + 2);}
    public static int W_SATIETY() {return 300;}

//********************************************DEER************************************************
    public static double D_RADIUS() {return 10;}
    public static double D_VELOCITY() {return Math.random() * (0.8 - 0.4) + 0.4;}
    public static int D_LIFETIME() {return 700;}
    public static int D_BREEDING_PERIOD() {return 100;}
    public static int D_CUBS_PER_BIRTH() {return (int) (Math.random() * (2 - 1) + 1);}
    public static int D_CALORIES() {return 5;}
}
