package entities;

import config.Signs;
import org.apache.log4j.Logger;
import service.impl.BFSPathFinderService;

import java.util.ArrayDeque;
import java.util.Random;

public class Hervibore extends Creature{
    final static Logger logger = Logger.getLogger(Hervibore.class);
    public Hervibore(int hp, int speed, Map map) {
        this.pathFinderService = new BFSPathFinderService(map);
        this.map = map;
        this.sign = Signs.HERBIVORE[new Random().nextInt(Signs.HERBIVORE.length)];
        this.hp = hp;
        this.speed = speed;
        this.currentPath = new ArrayDeque<>();
    }

    @Override
    public void makeMove(Cell currentCell) {
        logger.info("Making move from cell x: " + currentCell.getX() + ", y: " + currentCell.getY());

        for (int step = 0; step < this.speed; step++) {

            //check if there is grass nearby;
            var grassOnCell = this.pathFinderService.isGrassNear(currentCell);

            if (grassOnCell != null) {
                logger.info("Herbivore found grass on cell x: " + grassOnCell.getX() + " y:" + grassOnCell.getY() + " and ate it. Yum!");
                map.entities.put(grassOnCell, null);
                //rerender
                this.map.render();
                break;
            }

            //follow the path
            Cell movingTo = this.currentPath.pollFirst();
            //if there isn't current path, add path
            if (movingTo == null) {
                currentPath.addAll(pathFinderService.findPathToGrass(currentCell));
                movingTo = this.currentPath.pollFirst();
            }

            this.map.entities.put(movingTo, this);
            this.map.entities.put(currentCell, null);
            currentCell = movingTo;
            logger.info("Herbivore moved to cell x: " + currentCell.getX() + " y:" + currentCell.getY() + ". Stomp!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.map.render();
        }
    }
}
