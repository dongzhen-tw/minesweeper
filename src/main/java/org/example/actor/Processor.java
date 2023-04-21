package org.example.actor;


import org.example.entity.GameStatus;
import org.example.entity.Grid;

import java.util.Optional;

public interface Processor {
    void createMines(Grid grid, int n);

    // A1 -> [0, 0]
    Optional<int[]> getValidIndex(String index);

    Optional<Integer> getValidNum(String size);

    boolean isGameOver(Grid grid);

    GameStatus openMine(Grid grid, int[] index);
}
