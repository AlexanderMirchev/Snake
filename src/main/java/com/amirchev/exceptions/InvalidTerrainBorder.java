package com.amirchev.exceptions;

public class InvalidTerrainBorder extends InvalidTerrainFormat {

    public InvalidTerrainBorder(char actual, char expected,int charNum, int line, String filename) {
        super(String.format(
                "Invalid border symbol: %c instead of %c on character %d",
                actual, expected, charNum), line, filename);
    }
}
