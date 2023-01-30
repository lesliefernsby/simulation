package entities;

import config.Signs;
import org.apache.log4j.Logger;
import service.impl.BFSPathFinderService;

import java.util.ArrayDeque;
import java.util.Random;


public class Carnivore extends Creature{


    final static Logger logger = Logger.getLogger(Carnivore.class);
    private int attack;
    public Carnivore(int hp, int speed, int attack, Map map) {
        this.pathFinderService = new BFSPathFinderService(map);
        this.map = map;
        this.currentPath = new ArrayDeque<>();
        this.sign = Signs.CARNIVORE[new Random().nextInt(Signs.CARNIVORE.length)];
        this.hp = hp;
        this.speed = speed;
        this.attack = attack;
    }

    @Override
    public void makeMove(Cell currentCell) {
        logger.info("Making move from cell x: " + currentCell.getX() + ", y: " + currentCell.getY());

        for (int step = 0; step < this.speed; step++) {

            //check if there is grass nearby;
            var preyOnCell = this.pathFinderService.isPreyNear(currentCell);

            if (preyOnCell != null) {
                logger.info("Carnivore caught prey on cell x: " + preyOnCell.getX() + " y:" + preyOnCell.getY() + " and attacked it. Arrrgh!");
                Hervibore prey = (Hervibore) map.entities.get(preyOnCell);
                prey.hp -= this.attack;
                if (!prey.isAlive()) {
                    logger.info("Carnivore killed prey on cell x: " + preyOnCell.getX() + " y:" + preyOnCell.getY() + " and ate it. Yum!");
                    map.entities.put(preyOnCell, null);
                }
                //rerender
                this.map.render();
                break;
            }

            //follow the path
            Cell movingTo = this.currentPath.pollFirst();
            //if there isn't current path, add path
            if (movingTo == null) {
                currentPath.addAll(pathFinderService.findPathToPrey(currentCell));
                movingTo = this.currentPath.pollFirst();
            }

            this.map.entities.put(movingTo, this);
            this.map.entities.put(currentCell, null);
            currentCell = movingTo;
            logger.info("Carnivore moved to cell x: " + currentCell.getX() + " y:" + currentCell.getY() + ". Stomp!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.map.render();
        }
    }

}
