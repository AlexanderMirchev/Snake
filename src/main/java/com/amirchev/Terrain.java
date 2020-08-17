package com.amirchev;

import com.amirchev.exceptions.*;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class responsible for creation and management of the terrain the game is played on
 */
public final class Terrain {
    public static final char BRICK_SYMBOL='#';
    public static final char GRASS_SYMBOL=' ';

    private TextImage terrain;
    private String filename;

    /**
     * Deserializes string from file with filename - first pair of integers are the dimensions of the terrain,
     * while all other lines notify where bricks should be placed
     *
     * @param filename - should be existing file with 2 integers and according number of lines representing field
     * @throws FileNotFoundException on wrong file name
     * @throws InvalidTerrainFormat
     */
    public Terrain(final String filename) throws FileNotFoundException, InvalidTerrainFormat {

        try (Scanner in = new Scanner(new File(filename))) {
            this.terrain = initTerrain(in);
            this.filename = filename;

            int lineIndex = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();

                checkLineWidth(line, lineIndex);
                readLine(line, lineIndex);

                lineIndex++;
            }
            checkInputHeight(lineIndex);
        }
    }

    public final TextImage getTerrain() {
        return this.terrain;
    }

    public void setField(final Field field, final char symbol) {
        this.terrain.setCharacterAt(field.getCol(), field.getRow(), new TextCharacter(symbol));
    }

    public final char getField(final Field field) {
        return this.terrain.getCharacterAt(field.getCol(), field.getRow()).getCharacter();
    }

    public final int getWidth() {
        return this.terrain.getSize().getColumns();
    }

    public final int getHeight() {
        return this.terrain.getSize().getRows();
    }

    private TextImage initTerrain(Scanner scanner) throws InvalidTerrainDimensionsException {
        int width,height;

        try {
            width = scanner.nextInt();
        }
        catch (NoSuchElementException e) {
            throw new InvalidTerrainDimensionsException("width", 1, filename);
        }

        try {
            height = scanner.nextInt();
        }
        catch (NoSuchElementException e) {
            throw new InvalidTerrainDimensionsException("height", 2, filename);
        }
        scanner.nextLine();
        return new BasicTextImage(width, height);
    }

    private void readLine(final String line, final int lineNumber) throws InvalidTerrainBorder {
        for (int i = 0 ; i < line.length(); i++) {

            char character = line.charAt(i);
            if(lineNumber == 0 || lineNumber == getHeight() - 1 || i == 0 || i == line.length() - 1) {
                if (character != Terrain.BRICK_SYMBOL) {
                    throw new InvalidTerrainBorder(character, BRICK_SYMBOL, i, 3 + lineNumber, filename);
                }
            }
            terrain.setCharacterAt(i, lineNumber, new TextCharacter(character));
        }
    }

    private void checkLineWidth(final String line, final int lineIndex) throws InvalidLineWidth {
        if(line.length() < terrain.getSize().getColumns()) {
            throw new InvalidLineWidth(line.length(), terrain.getSize().getColumns(), 3 + lineIndex, filename);
        }
    }

    private void checkInputHeight(final int numberOfLines) throws InvalidTerrainHeight {
        if(numberOfLines < this.terrain.getSize().getRows()) {
            throw new InvalidTerrainHeight(terrain.getSize().getRows(), numberOfLines, filename);
        }
    }
}
