package com.amirchev;

import java.awt.event.KeyEvent;
import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

public class Snake {
    public enum Direction{
        UP {
            @Override
            public Field getNextField(final Field current) {
                return new Field(current.getRow() - 1, current.getCol());

            };
        },
        DOWN {
            @Override
            public Field getNextField(Field current) {
                return new Field(current.getRow() + 1, current.getCol());
            }
        },
        LEFT {
            @Override
            public Field getNextField(Field current) {
                return new Field(current.getRow(), current.getCol() - 1);
            }
        },
        RIGHT {
            @Override
            public Field getNextField(Field current) {
                return new Field(current.getRow(), current.getCol() + 1);
            }
        };

        public abstract Field getNextField(final Field current);
    }

    private LinkedList<Field> snakeBody;
    private Direction direction;
    private boolean reversed;
    private static final char SNAKE_SYMBOL = '*';

    public Snake(final Field start) {
        direction = Direction.RIGHT;
        snakeBody = new LinkedList<>();
        snakeBody.add(start);
        for (int i = 0; i< 2; i++) {
            snakeBody.addFirst(direction.getNextField(getHead()));
        }
        reversed = false;
    }

    public void move() {
        Field head = getHead();
        addFirst(direction.getNextField(head));
        removeLast();
    }

    public Field getHead() {
        return reversed ? snakeBody.getLast(): snakeBody.getFirst();
    }

    public Field getLast() {
        return reversed ? snakeBody.getFirst(): snakeBody.getLast();
    }

    public void addFirst(final Field field) {
        if (reversed) {
            snakeBody.addLast(field);
        }
        else {
            snakeBody.addFirst(field);
        }
    }

    public void removeLast() {
        if (reversed) {
            snakeBody.removeFirst();
        }
        else {
            snakeBody.removeLast();
        }
    }

    public List<Field> getSnake() {
        System.out.println(snakeBody.size());
        return new ArrayList<>(snakeBody);
    }

    public char getSnakeSymbol() {
        return SNAKE_SYMBOL;
    }

//    public void keyPressed(KeyEvent e) {
//        this.direction = keyToDirectionMap.get(e.getKeyCode());
//    }
//
//    private static final Map<Integer, Direction> keyToDirectionMap = new HashMap<>(
//            Map.ofEntries(
//                    new AbstractMap.SimpleEntry<> (KeyEvent.VK_LEFT, Direction.LEFT),
//                    new AbstractMap.SimpleEntry<> (KeyEvent.VK_UP, Direction.UP),
//                    new AbstractMap.SimpleEntry<> (KeyEvent.VK_RIGHT, Direction.RIGHT),
//                    new AbstractMap.SimpleEntry<> (KeyEvent.VK_DOWN, Direction.DOWN)
//            )
//    );
}
