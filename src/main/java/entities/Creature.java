package entities;

import service.PathFinderService;

import java.util.Deque;
import java.util.List;

public abstract class Creature extends Entity {
    protected int speed;
    protected int hp;
    protected PathFinderService pathFinderService;
    protected Map map;
    Deque<Cell> currentPath;

    abstract public void makeMove(Cell currentCell);

    public boolean isAlive() {
        return hp > 0;
    }
}
