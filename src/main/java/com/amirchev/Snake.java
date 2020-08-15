package com.amirchev;

import java.awt.event.KeyEvent;
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

    public Snake(final Field start) {
        direction = Direction.RIGHT;
        snakeBody = new LinkedList<>();
        snakeBody.add(start);
    }

    public Field move() {
        Field head = snakeBody.getFirst();
        snakeBody.addFirst(direction.getNextField(head));
        Field popped = snakeBody.removeLast();
        return snakeBody.getFirst();
    }

    public Field getLast() {
        return snakeBody.getLast();
    }

    public List<Field> getSnake() {
        return new ArrayList<Field>(snakeBody);
    }

    public void keyPressed(KeyEvent e) {
        this.direction = keyToDirectionMap.get(e.getKeyCode());
    }

    private static final Map<Integer, Direction> keyToDirectionMap = new HashMap<>(
            Map.ofEntries(
                    new AbstractMap.SimpleEntry<> (KeyEvent.VK_LEFT, Direction.LEFT),
                    new AbstractMap.SimpleEntry<> (KeyEvent.VK_UP, Direction.UP),
                    new AbstractMap.SimpleEntry<> (KeyEvent.VK_RIGHT, Direction.RIGHT),
                    new AbstractMap.SimpleEntry<> (KeyEvent.VK_DOWN, Direction.DOWN)
            )
    );
}
