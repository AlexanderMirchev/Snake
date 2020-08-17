package com.amirchev.exceptions;

public class InvalidTerrainFormat extends Exception {

    public InvalidTerrainFormat(final String errorMessage, final int line, final String filename) {
        super(String.format("%s on line %d in %s", errorMessage, line, filename));
    }
}
