package utils;

import config.Signs;
import entities.*;

import java.util.Random;

public class EntityFactory {

    private Map map;
    public EntityFactory(Map map) {
        this.map = map;
    }

    public Entity getRandomEntity() {
        int a = new Random().nextInt(20);

        switch (a) {
            case 0 -> {
                return new Grass();
            }
            case 1 -> {
                return new Rock();
            }
            case 2 -> {
                return new Tree();
            }
            case 3 -> {
                return new Hervibore(new Random().nextInt(5), new Random().nextInt(5), map);
            }
            case 4 -> {
                return new Carnivore(new Random().nextInt(5), new Random().nextInt(5), new Random().nextInt(5), map);
            }
            default -> {
                return null;
            }
        }
    }
}
