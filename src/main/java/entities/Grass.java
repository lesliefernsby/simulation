package entities;

import config.Signs;

import java.util.Random;

public class Grass extends Entity{

    public Grass() {
        this.sign = Signs.GRASS[new Random().nextInt(Signs.GRASS.length)];
    }
}
