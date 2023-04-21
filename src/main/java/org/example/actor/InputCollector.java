package org.example.actor;

import org.example.entity.Grid;

public interface InputCollector {
    int collectSize();
    int collectMinesNum(int total);
    int[] collectIndex(Grid grid);
}
