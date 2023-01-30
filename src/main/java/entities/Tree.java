package entities;

import config.Signs;

import java.util.List;
import java.util.Random;

public class Tree extends Entity{

    public Tree() {
        this.sign = Signs.TREE[new Random().nextInt(Signs.TREE.length)];
    }
}
