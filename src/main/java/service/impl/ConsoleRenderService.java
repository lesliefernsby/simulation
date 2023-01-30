package service.impl;

import entities.Cell;
import entities.Map;
import service.RenderService;

public class ConsoleRenderService implements RenderService {

    private Map map;
    public ConsoleRenderService() {
        this.map = map;
    }

    @Override
    public void render(Map map) {
        //clear console
        System.out.print("\033\143");

        //draw map
        for (int i = 0; i < map.x; i++) {
            for (int j = 0; j < map.y; j++) {
                var cell = map.entities.get(new Cell(i, j));
                System.out.print(cell == null ? " .. " : " " + cell.getSign() + " ");
            }
            System.out.println(System.lineSeparator());
        }
    }
}
