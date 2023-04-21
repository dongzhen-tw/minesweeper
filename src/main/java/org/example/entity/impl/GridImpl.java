package org.example.entity.impl;

import org.example.entity.Grid;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.IntStream;

@Component
public class GridImpl implements Grid {
    public static int UNCOVER = 10;
    public static int MINE = 9;
    int size;
    int minesNum;
    int uncoverNum;
    int[][] values;


    @Override
    public String toString() {
        return getGridString();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getMineNum() {
        return minesNum;
    }

    @Override
    public int getUncoverNum() {
        return uncoverNum;
    }

    @Override
    public void initGrid(int size) {
        this.size = size;
        uncoverNum = size * size;
        minesNum = 0;
        values = new int[size][size];
        for (int[] row : values) {
            Arrays.fill(row, UNCOVER);
        }
    }

    @Override
    public void buryMine(int row, int col) {
        this.setValue(MINE, row, col);
        minesNum++;
//        System.out.println("new minenum: " + minesNum);
    }

    @Override
    public int getValue(int row, int col) {
        return values[row][col];
    }

    @Override
    public void setValue(int val, int row, int col) {
        if (UNCOVER == values[row][col] && UNCOVER != val && MINE != val) {
            uncoverNum--;
        }
        values[row][col] = val;
    }

    @Override
    public boolean isMine(int row, int col) {
        return MINE == getValue(row, col);
    }

    @Override
    public boolean isValidIndex(int row, int col) {
        return row > -1 && row < size && col > -1 && col < size;
    }

    @Override
    public String getHeaderString() {
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        IntStream.range(0, size).forEach(i -> {
            sb.append(' ');
            sb.append(Integer.toString(i+1));
        });
        return sb.toString();
    }

    @Override
    public String getBodyString() {
        StringBuilder body = new StringBuilder();
        IntStream.range(0, size).forEach(r -> {
            StringBuilder row = new StringBuilder();
            row.append((char)('A' + r));
            IntStream.range(0, size).forEach(c -> {
                row.append(' ');
                int v = this.getValue(r, c);
                if (v == UNCOVER || v == MINE) {
                    row.append('_');
                }
                else {
                    row.append((char)('0' + v));
                }
            });
            body.append(row);
            body.append(System.lineSeparator());
        });
        return body.toString();
    }

    @Override
    public String getGridString() {
        return getHeaderString() + System.lineSeparator() + getBodyString();
    }
}
