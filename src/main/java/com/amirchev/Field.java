package com.amirchev;

/**
 * Field class for representation of pair of row and columns
 */
public final class Field {
    private final int row;
    private final int col;

    public Field(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public final int getRow() {
        return row;
    }
    public final int getCol() {
        return col;
    }

}
