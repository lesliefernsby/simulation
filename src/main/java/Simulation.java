import entities.*;
import org.apache.log4j.Logger;


public class Simulation extends Thread {


    public Map map;
    final static Logger logger = Logger.getLogger(Simulation.class);
    private boolean running = false;

    public Simulation(Map map) {
        this.map = map;
    }


    @Override
    public void run() {
        while (running) {
            this.nextTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    private void nextTurn() {
        logger.warn("NEW TURN!");
        this.map.entities.forEach(((cell, entity) -> {
            if (entity != null && entity instanceof Creature) {
                ((Creature) entity).makeMove(cell);
            }
        }));
    }

    public void startSimulation() {
        this.running = true;
        this.start();
    }

    public void pauseSimulation() {
        this.running = false;
    }


}
