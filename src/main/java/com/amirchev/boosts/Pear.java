package com.amirchev.boosts;

import com.amirchev.Field;
import com.amirchev.Snake;

public final class Pear implements Boost {
    private static final char SYMBOL = 'd';

    private Field field;
    private boolean isEaten = false;

    public Pear (final Field field) {
        this.field = field;
    }

    @Override
    public char getBoostSymbol() {
        return SYMBOL;
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public void applyOn(Snake snake) {
        snake.reverse();
        isEaten = true;
    }

    @Override
    public boolean isEaten() {
        return isEaten;
    }
}
