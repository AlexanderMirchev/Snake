package com.amirchev.exceptions;

public class InvalidTerrainDimensionsException extends InvalidTerrainFormat{
    public InvalidTerrainDimensionsException(final String dimension, final int line, final String filename) {
        super(String.format("Invalid %s initialisation: expected integer value", dimension), line, filename);
    }
}
