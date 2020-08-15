package com.amirchev.contents;

import com.amirchev.Snake;

public class Brick implements FieldContent {
    private static final char symbol = '#';

    @Override
    public void printContent() {
        System.out.print(symbol);
    }

    @Override
    public void actionIfEatenBySnake(Snake snake) {

    }
}
