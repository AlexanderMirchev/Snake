package com.amirchev.contents;

import com.amirchev.Snake;

public class Apple implements FieldContent {
    private static final char symbol = 'o';

    @Override
    public void printContent() {
        System.out.print(symbol);
    }

    @Override
    public void actionIfEatenBySnake(Snake snake) {

    }
}
