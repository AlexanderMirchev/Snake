package com.amirchev.boosts;

import com.amirchev.Field;
import com.amirchev.Terrain;

import java.util.Random;

public final class BoostFactory {
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
        if(boostProbability%6 == 0) {
            return new Pear(newField);
        }
        else {
            return new Apple(newField);
        }
    }
}
