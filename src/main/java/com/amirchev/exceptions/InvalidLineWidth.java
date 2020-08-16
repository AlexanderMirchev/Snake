package com.amirchev.exceptions;

public class InvalidLineWidth extends InvalidTerrainFormat {

    public InvalidLineWidth(final int actualWidth, final int expectedWidth, final int line, final String filename) {
        super(String.format("Invalid line width: %d when %d is expected", actualWidth, expectedWidth), line, filename);
    }
}
