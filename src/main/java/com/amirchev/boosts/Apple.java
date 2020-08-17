package com.amirchev.boosts;

import com.amirchev.Field;
import com.amirchev.Snake;

public final class Apple implements Boost {
    private static final char SYMBOL = 'o';

    private Field field;
    boolean isEaten = false;

    public Apple (final Field field) {
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
        snake.grow();
        isEaten = true;
    }

    @Override
    public boolean isEaten() {
        return isEaten;
    }
}
