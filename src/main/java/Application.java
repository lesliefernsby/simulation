import entities.Map;
import org.apache.log4j.Logger;
import service.impl.ConsoleRenderService;

import java.util.Scanner;

public class Application {
    final static Logger logger = Logger.getLogger(Simulation.class);


    public static void main(String[] args) {
        logger.info("Starting simulation");
        Simulation simulation = new Simulation(new Map(10,30, new ConsoleRenderService()));
        simulation.map.render();

        simulation.startSimulation();
        Scanner scanner = new Scanner(System.in);
        var ch = scanner.nextLine();

        if (ch != null) {
            simulation.pauseSimulation();
        }
        scanner.close();

    }
}
