package com.amirchev;

import com.amirchev.exceptions.InvalidLineWidth;
import com.amirchev.exceptions.InvalidTerrainDimensionsException;
import com.amirchev.exceptions.InvalidTerrainFormat;
import com.amirchev.exceptions.InvalidTerrainHeight;
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
            this.terrain = initTerrain(in, filename);

            int lines = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if(line.length() < terrain.getSize().getColumns()) {
                    throw new InvalidLineWidth(line.length(), terrain.getSize().getColumns(), 3 + lines, filename);
                }
                for (int i = 0 ; i < line.length(); i++) {
                    terrain.setCharacterAt(i, lines, new TextCharacter(line.charAt(i)));
                }
                lines++;
            }
            if(lines < this.terrain.getSize().getRows()) {
                throw  new InvalidTerrainHeight(terrain.getSize().getRows(), lines, filename);
            }
        }
    }

    public TextImage getTerrain() {
        return this.terrain;
    }

    public void setField(final Field field, final char symbol) {
        this.terrain.setCharacterAt(field.getCol(), field.getRow(), new TextCharacter(symbol));
    }

    public char getField(final Field field) {
        return this.terrain.getCharacterAt(field.getCol(), field.getRow()).getCharacter();
    }

    public int getWidth() {
        return this.terrain.getSize().getColumns();
    }

    public int getHeight() {
        return this.terrain.getSize().getRows();
    }

    private TextImage initTerrain(Scanner scanner, final String filename) throws InvalidTerrainDimensionsException {
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
}
