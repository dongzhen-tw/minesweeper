package org.example.entity;

public interface Grid {
    int getSize();
    int getMineNum();
    int getUncoverNum();
    void initGrid(int size);
    void buryMine(int row, int col);
    int getValue(int row, int col);
    void setValue(int val, int row, int col);
    boolean isMine(int row, int col);
    boolean isValidIndex(int row, int col);
    String getHeaderString();
    String getBodyString();
    String getGridString();
}
