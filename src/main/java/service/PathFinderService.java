package service;

import entities.Cell;
import entities.Map;

import java.util.Deque;
import java.util.List;

public interface PathFinderService {

    //---Directions---//
    //  1     2     3
    //
    //  8     X     4
    //
    //  7     6     5
    //----------------//


    Cell isGrassNear(Cell cell);
    Cell isPreyNear(Cell cell);
    List<Cell> emptyCellsNear(Cell cell);

    Deque<Cell> findPathToGrass(Cell cell);

    Deque<Cell> findPathToPrey(Cell cell);
}
