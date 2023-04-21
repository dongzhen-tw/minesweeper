package org.example.entity.impl;

import org.example.entity.Grid;
import org.junit.jupiter.api.Test;

import static org.example.entity.impl.GridImpl.MINE;
import static org.example.entity.impl.GridImpl.UNCOVER;
import static org.junit.jupiter.api.Assertions.*;

class GridImplTest {

    @Test
    void testInitGrid() {
        Grid grid = new GridImpl();
        grid.initGrid(2);
        for (int r=0; r<2; r++) {
            for (int c=0; c<2; c++) {
                assertEquals(UNCOVER, grid.getValue(r, c));
            }
        }
    }

    @Test
    void testInitGridThrowsExForWrongArrayIndex() {
        Grid grid = new GridImpl();
        grid.initGrid(2);
        assertDoesNotThrow(() -> grid.getValue(1,1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> grid.getValue(0,2));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> grid.getValue(2,0));
    }

    @Test
    void testBuryMine() {
        Grid grid = new GridImpl();
        grid.initGrid(2);
        assertEquals(UNCOVER, grid.getValue(0, 0));
        grid.buryMine(0,0);
        assertEquals(MINE, grid.getValue(0, 0));
    }

    @Test
    void testIsMine() {
        Grid grid = new GridImpl();
        grid.initGrid(2);
        grid.buryMine(0,0);
        assertTrue(grid.isMine(0,0));
    }

    @Test
    void testGetHeaderString() {
        Grid grid = new GridImpl();
        grid.initGrid(2);
        assertEquals("  1 2" ,grid.getHeaderString());
    }

    @Test
    void testGetBodyString() {
        Grid grid = new GridImpl();
        grid.initGrid(3);
        String expectedBody =
                "A _ _ _" + System.lineSeparator() + "B _ _ _" + System.lineSeparator() + "C _ _ _" + System.lineSeparator();
        assertEquals(expectedBody, grid.getBodyString());
    }

    @Test
    void testGetGridString() {
        Grid grid = new GridImpl();
        grid.initGrid(2);
        String expectedHeader = "  1 2";
        String expectedBody =
                "A _ _" + System.lineSeparator() + "B _ _" + System.lineSeparator();
        assertEquals(expectedHeader + System.lineSeparator() + expectedBody, grid.getGridString());
    }
}
