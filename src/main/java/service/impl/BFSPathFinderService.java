package service.impl;

import config.Signs;
import entities.Cell;
import entities.Grass;
import entities.Hervibore;
import entities.Map;
import service.PathFinderService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BFSPathFinderService implements PathFinderService {

    private Map map;
    public BFSPathFinderService(Map map) {
        this.map = map;
    }

    @Override
    public Cell isGrassNear(Cell cell) {
        return cellsToCheck(cell).stream()
                .filter(el -> map.entities.get(el) != null && map.entities.get(el).getClass() == Grass.class)
                .findFirst().orElse(null);
    }

    @Override
    public Cell isPreyNear(Cell cell) {
        return cellsToCheck(cell).stream()
                .filter(el -> map.entities.get(el) != null && map.entities.get(el).getClass() == Hervibore.class)
                .findFirst().orElse(null);
    }

    @Override
    public List<Cell> emptyCellsNear(Cell cell) {
        return cellsToCheck(cell).stream()
                .filter(el -> map.entities.containsKey(el) && map.entities.get(el) == null)
                .collect(Collectors.toList());
    }

    @Override
    public Deque<Cell> findPathToGrass(Cell cell) {
        Deque<Cell> path = new ArrayDeque<>();

        Deque<Cell> toVisit = new ArrayDeque<>();
        toVisit.addAll(emptyCellsNear(cell));

        while (!toVisit.isEmpty()) {
            Cell visiting = toVisit.pollFirst();
            path.add(visiting);

            if (isGrassNear(visiting) != null) break;
            toVisit.addAll(emptyCellsNear(visiting));
        }

        return path;
    }

    @Override
    public Deque<Cell> findPathToPrey(Cell cell) {
        Deque<Cell> path = new ArrayDeque<>();

        Deque<Cell> toVisit = new ArrayDeque<>();
        toVisit.addAll(emptyCellsNear(cell));

        while (!toVisit.isEmpty()) {
            Cell visiting = toVisit.pollFirst();
            path.add(visiting);

            if (isPreyNear(visiting) != null) break;
            toVisit.addAll(emptyCellsNear(visiting));
        }

        return path;
    }

    private Set<Cell> cellsToCheck (Cell cell) {
        return Stream.of(
                        new Cell(cell.getX() - 1, cell.getY() - 1),
                        new Cell(cell.getX() - 1, cell.getY()),
                        new Cell(cell.getX() - 1, cell.getY() + 1),
                        new Cell(cell.getX(), cell.getY() + 1),
                        new Cell(cell.getX() + 1, cell.getY() + 1),
                        new Cell(cell.getX() + 1, cell.getY()),
                        new Cell(cell.getX() + 1, cell.getY() - 1),
                        new Cell(cell.getX(), cell.getY() - 1))
                .collect(Collectors.toCollection(HashSet::new));
    }
}
