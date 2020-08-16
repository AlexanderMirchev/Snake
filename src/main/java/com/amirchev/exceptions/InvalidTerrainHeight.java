package com.amirchev.exceptions;

public class InvalidTerrainHeight extends InvalidTerrainFormat {

    public InvalidTerrainHeight(int expectedHeight, int line, String filename) {
        super(String.format("Invalid number of lines: %d when %d expected", line, expectedHeight), line, filename);
    }
}
