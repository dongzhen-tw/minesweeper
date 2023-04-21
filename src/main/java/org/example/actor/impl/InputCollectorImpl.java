package org.example.actor.impl;

import org.example.actor.InputCollector;
import org.example.actor.Processor;
import org.example.entity.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class InputCollectorImpl implements InputCollector {
    @Autowired
    Processor processor;

    @Override
    public int collectSize() {
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        boolean done = false;
        int size = 0;
        while (! done) {
            System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
            try {
                String line = buffer.readLine();
                Optional<Integer> validSize = processor.getValidNum(line);

                if (validSize.isEmpty()) {
                    System.out.println("Incorrect input.");
                    continue;
                }
                else if (validSize.get() < 3) {
                    System.out.println("Minimum size of grid is 3.");
                    continue;
                }
                else if (validSize.get() > 10) {
                    System.out.println("Maximum size of grid is 10.");
                    continue;
                }
                done = true;
                size = validSize.get();
            } catch(Exception e) {}
        }
        return size;
    }

    @Override
    public int collectMinesNum(int total) {
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        boolean done = false;
        int mineNum = 0;
        while (! done) {
            System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
            try {
                String line = buffer.readLine();
                Optional<Integer> validMineNum = processor.getValidNum(line);

                if (validMineNum.isEmpty()) {
                    System.out.println("Incorrect input.");
                    continue;
                }
                else if (validMineNum.get() < 1) {
                    System.out.println("There must be at least 1 mine.");
                    continue;
                }
                else if (validMineNum.get() > total * 0.35) {
                    System.out.println("Maximum number is 35% of total sqaures.");
                    continue;
                }
                done = true;
                mineNum = validMineNum.get();
            } catch(Exception e) {}
        }
        return mineNum;
    }

    @Override
    public int[] collectIndex(Grid grid) {
        BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
        boolean done = false;
        int[] index = new int[2];
        while (! done) {
            System.out.println("Select a square to reveal (e.g. A1):");
            try {
                String line = buffer.readLine();
                Optional<int[]> validIndex = processor.getValidIndex(line);

                if (validIndex.isEmpty()) {
                    System.out.println("Incorrect input.");
                    continue;
                }
                done = true;
                index = validIndex.get();
            } catch(Exception e) {}
        }
        return index;
    }
}
