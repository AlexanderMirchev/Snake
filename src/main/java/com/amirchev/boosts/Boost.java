package com.amirchev.boosts;

import com.amirchev.Field;
import com.amirchev.Snake;

public interface Boost {
    char getBoostSymbol();

    Field getField();

    void applyOn(Snake snake);

    boolean isEaten();
}
