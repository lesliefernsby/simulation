package entities;

import service.RenderService;
import utils.EntityFactory;

import java.util.HashMap;

public class Map implements Renderable{
    private RenderService renderService;
    public HashMap<Cell, Entity> entities;
    public int x;
    public int y;


    public Map(int x, int y, RenderService renderService) {
        this.renderService = renderService;
        this.entities = new HashMap<>();
        this.x = x;
        this.y = y;
        EntityFactory factory = new EntityFactory(this);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                entities.put(new Cell(i, j), factory.getRandomEntity());
            }
        }
    }

    @Override
    public void render() {
        renderService.render(this);
    }
}
