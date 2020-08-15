package com.amirchev;

import com.amirchev.contents.FieldContent;
import com.amirchev.contents.FieldContentFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class responsible for creation and management of the terrain the game is played on
 */
public class Terrain {
    private FieldContent[][] fields;

    /**
     * Deserializes string from file with filename - first pair of integers are the dimensions of the terrain,
     * while all other pairs notify where bricks should be placed
     *
     * @param filename - should be existing file with whitespace separated integers
     * @throws FileNotFoundException
     * @throws NoSuchElementException
     */
    public Terrain(final String filename) throws FileNotFoundException, NoSuchElementException {

        Scanner scanner = new Scanner(new File(filename));

        final int width = scanner.nextInt();
        final int height = scanner.nextInt();
        this.fields = new FieldContent[height][width];

        while (scanner.hasNextInt()) {
            final int firstCoord = scanner.nextInt();
            if (scanner.hasNextInt()) {
                this.fields[firstCoord][scanner.nextInt()] =
                        FieldContentFactory.make(FieldContentFactory.FieldContentEnum.BRICK);
            }
        }

        scanner.close();
    }

    /**
     * Prints the whole terrain with apples, bricks, snake and adds border.
     */
    public void print() {
        final int BORDER_WIDTH = 1;
        final char BORDER_SYMBOL = '#';
        final char EMPTY_FIELD = ' ';

        for (int i = 0; i < fields.length + 2 * BORDER_WIDTH; i++) {
            for (int j = 0; j < fields[0].length + 2 * BORDER_WIDTH; j++) {
                if (isOnBorder(i, j, BORDER_WIDTH)) {
                    System.out.print(BORDER_SYMBOL);
                } else {
                    if (fields[i - BORDER_WIDTH][j - BORDER_WIDTH] != null) {
                        fields[i - BORDER_WIDTH][j - BORDER_WIDTH].printContent();
                    } else {
                        System.out.print(EMPTY_FIELD);
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Sets field to value of content
     *
     * @param field - pair of row and column
     * @param content - field content object
     */
    public void setField(final Field field, FieldContent content) {
        if(field.getRow() < 0 || field.getRow() >= fields.length ||
            field.getCol() < 0 || field.getCol() >= fields[0].length) {
            throw new IndexOutOfBoundsException();
        }
        this.fields[field.getRow()][field.getCol()] = content;
    }

    private boolean isOnBorder(final int i, final int j, final int width) {
        return i < width ||
                j < width ||
                i > fields.length + width - 1 ||
                j > fields[0].length + width - 1;
    }
}
