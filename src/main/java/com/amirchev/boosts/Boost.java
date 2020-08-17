package com.amirchev.boosts;

import com.amirchev.Field;
import com.amirchev.Snake;

/**
 * Interface class for boosts
 */
public interface Boost {
    /**
     * @return char symbol of the boost
     */
    char getBoostSymbol();

    /**
     * @return the field where the boost was generated
     */
    Field getField();

    /**
     * Applies the boost effect on the snake
     *
     * @param snake
     */
    void applyOn(Snake snake);

    /**
     * @return flag whether boost is eaten or not
     */
    boolean isEaten();
}
