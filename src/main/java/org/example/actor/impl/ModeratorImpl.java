package org.example.actor.impl;

import org.example.actor.InputCollector;
import org.example.actor.Moderator;
import org.example.actor.Processor;
import org.example.entity.GameStatus;
import org.example.entity.Grid;
import org.example.entity.impl.GridImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static org.example.entity.GameStatus.FAIL;
import static org.example.entity.GameStatus.NOTDONE;
import static org.example.entity.GameStatus.SUCCESS;

@Component
public class ModeratorImpl implements Moderator {

    @Autowired
    private InputCollector inputCollector;

    @Autowired
    private Processor processor;

    @Autowired
    private Grid grid;

    @Override
    public void start() {
        while(true) {
            System.out.println("Welcome to Minesweeper!");

            // Collect input from user
            int size = inputCollector.collectSize();
            int minesNum = inputCollector.collectMinesNum(size * size);

            // create grid
            grid.initGrid(size);
            processor.createMines(grid, minesNum);

            System.out.println("Here is your minefield:");
            System.out.println(grid.getGridString());

            GameStatus status = NOTDONE;
            Scanner scanner = new Scanner(System.in);
            while (status == NOTDONE) {
                int[] index = inputCollector.collectIndex(grid);
                // System.out.println(index[0] + " ," + index[1]);
                status = processor.openMine(grid, index);
                if (status == SUCCESS) {
                    System.out.println("Congratulations, you have won the game!");
                    System.out.println("Press any key to play again...");
                    String next = scanner.next();
                } else if (status == NOTDONE) {
                    System.out.println("Here is your updated minefield:");
                    System.out.println(grid.getGridString());
                } else {
                    System.out.println("Oh no, you detonated a mine! Game over.");
                    System.out.println("Press any key to play again...");
                    String next = scanner.next();
                }
            }
        }
    }

}
