package org.ag.base;

import org.ag.config.Config;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class Cell {
    private int row;
    private int col;
    private int value;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = 0;
    }

    public Cell() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increment() {
        this.value++;
    }

    public int[] getNeighbor(int dir) {
        if (dir == 0) {
            return nullOr(left());
        } else if (dir == 1) {
            return nullOr(up());
        } else if (dir == 2) {
            return nullOr(right());
        } else if (dir == 3) {
            return nullOr(down());
        } else {
            return null;
        }
    }

    @Nullable
    @Contract(pure = true)
    private int[] nullOr(int[] coordinates) {
        return coordinates[0] < 0 || coordinates[0] > Config.DIM - 1 || coordinates[1] < 0 || coordinates[1] > Config.DIM - 1 ? null : coordinates;
    }

    @Contract(" -> !null")
    private int[] left() {
        return this.value % 2 == 0 ? new int[]{this.row, this.col - 1} : new int[]{this.row + 1, this.col - 1};
    }

    @Contract(" -> !null")
    private int[] up() {
        return this.value % 2 == 0 ? new int[]{this.row - 1, this.col} : new int[]{this.row - 1, this.col - 1};
    }

    @Contract(" -> !null")
    private int[] right() {
        return this.value % 2 == 0 ? new int[]{this.row, this.col + 1} : new int[]{this.row - 1, this.col + 1};
    }

    @Contract(" -> !null")
    private int[] down() {
        return this.value % 2 == 0 ? new int[]{this.row + 1, this.col} : new int[]{this.row + 1, this.col + 1};
    }
}
