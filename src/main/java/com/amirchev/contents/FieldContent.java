package com.amirchev.contents;

import com.amirchev.Snake;

/**
 * Class representing the contents of a single field
 */
public interface FieldContent {

    /**
     * Displays the content
     */
    void printContent();

    /**
     * Modifies the snake when on top
     *
     * @param snake
     */
    void actionIfEatenBySnake(Snake snake);
}
