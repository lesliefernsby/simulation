package entities;

import config.Signs;

import java.util.Random;

public class Rock extends Entity{

    public Rock() {
        this.sign = Signs.ROCK[new Random().nextInt(Signs.ROCK.length)];
    }
}
