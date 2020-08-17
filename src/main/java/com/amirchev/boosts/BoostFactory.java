package com.amirchev.boosts;

import com.amirchev.Field;
import com.amirchev.Terrain;

import java.util.Random;

/**
 * Factory class for generating boosts
 */
public final class BoostFactory {
    /**
     * Method for generating boost with 1 to 5 chance of generating
     * pears to apples
     *
     * @param terrain the terrain on which it should be generated
     * @return the generated boost
     */
    public static Boost generateBoost(Terrain terrain) {
        int row, col;
        Field newField;
        Random rand = new Random();
        do {
            row = rand.nextInt(terrain.getHeight());
            col = rand.nextInt(terrain.getWidth());
            newField = new Field(row, col);
        }
        while (terrain.getField(newField) != Terrain.GRASS_SYMBOL);

        int boostProbability = rand.nextInt();
        if(boostProbability % 6 == 0) {
            return new Pear(newField);
        }
        else {
            return new Apple(newField);
        }
    }
}
