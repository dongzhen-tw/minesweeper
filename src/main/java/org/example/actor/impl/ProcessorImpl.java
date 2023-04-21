package org.example.actor.impl;

import org.example.actor.Processor;
import org.example.entity.GameStatus;
import org.example.entity.Grid;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import static org.example.entity.GameStatus.FAIL;
import static org.example.entity.GameStatus.NOTDONE;
import static org.example.entity.GameStatus.SUCCESS;
import static org.example.entity.impl.GridImpl.MINE;
import static org.example.entity.impl.GridImpl.UNCOVER;

@Component
public class ProcessorImpl implements Processor {
    private static int[][] offsets = {
            {-1,-1},
            {-1,0},
            {-1,1},
            {0,-1},
            {0,1},
            {1,-1},
            {1,0},
            {1,1},
    };
    @Override
    public void createMines(Grid grid, int n) {
        int size = grid.getSize();
        int total = size * size;
        Random random = new Random();
        Set<Integer> mines = new HashSet<>();
        IntStream.range(0, n).forEach(i -> {
            int index = random.nextInt(total);
            while (mines.contains(index)) {
                index = random.nextInt(total);
            }
            mines.add(index);
            // System.out.println(index);
            grid.buryMine(index / size, index % size);
        });
    }

    @Override
    public Optional<Integer> getValidNum(String index) {
        int size;
        try {
            size = Integer.parseInt(index);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(size);
    }

    @Override
    public boolean isGameOver(Grid grid) {
        return grid.getMineNum() == grid.getUncoverNum();
    }

    @Override
    public GameStatus openMine(Grid grid, int[] index) {
        int value = grid.getValue(index[0], index[1]);

        if (value != UNCOVER && value != MINE) return NOTDONE;
        if (value == MINE) return FAIL;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(index);
        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int newValue = 0;
            for (int[] offset : offsets) {
                if (! grid.isValidIndex(idx[0]+offset[0], idx[1]+offset[1])) continue;

                if (grid.getValue(idx[0]+offset[0], idx[1]+offset[1]) == MINE) {
                    newValue++;
                }
            }
            grid.setValue(newValue, idx[0], idx[1]);
            if (newValue == 0) {
                for (int[] offset : offsets) {
                    if (! grid.isValidIndex(idx[0]+offset[0], idx[1]+offset[1])) continue;

                    if (grid.getValue(idx[0]+offset[0], idx[1]+offset[1]) == UNCOVER) {
                        queue.offer(new int[] {idx[0]+offset[0], idx[1]+offset[1]});
                    }
                }
            }
        }
//        System.out.println("uncover: " + grid.getUncoverNum() + ", minenum: " + grid.getMineNum());
        if (grid.getUncoverNum() == grid.getMineNum()) return SUCCESS;

        return NOTDONE;
    }

    @Override
    public Optional<int[]> getValidIndex(String index) {
        int[] ret = new int[2];
        if (index == null || index.length() < 2 || index.length() > 3) return Optional.empty();

        int rIdx = index.charAt(0) - 'A';
        if (rIdx < 0 || rIdx > 9) return Optional.empty();
        ret[0] = rIdx;

        String cIdx = index.substring(1);
        if (cIdx.charAt(0) == '0' && cIdx.length() !=1 ) return Optional.empty();
        try {
            ret[1] = Integer.parseInt(cIdx) - 1;
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(ret);
    }
}
