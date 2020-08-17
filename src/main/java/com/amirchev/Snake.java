package com.amirchev;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

/**
 * Class representing snake
 */
public final class Snake {

    /**
     * Enum class with methods concerning the snake direction
     */
    public enum Direction{
        UP {
            @Override
            public final Field getNextField(final Field current) {
                return new Field(current.getRow() - 1, current.getCol());
            }

            @Override
            public final Direction getOpposite() {
                return Direction.DOWN;
            }
        },
        DOWN {
            @Override
            public final Field getNextField(Field current) {
                return new Field(current.getRow() + 1, current.getCol());
            }

            @Override
            public final Direction getOpposite() {
                return Direction.UP;
            }
        },
        LEFT {
            @Override
            public final Field getNextField(Field current) {
                return new Field(current.getRow(), current.getCol() - 1);
            }

            @Override
            public final Direction getOpposite() {
                return Direction.RIGHT;
            }
        },
        RIGHT {
            @Override
            public final Field getNextField(Field current) {
                return new Field(current.getRow(), current.getCol() + 1);
            }

            @Override
            public final Direction getOpposite() {
                return Direction.LEFT;
            }
        };

        /**
         * Returns next field according to given direction
         *
         * @param current current field
         * @return next field
         */
        public abstract Field getNextField(final Field current);

        /**
         * Returns the opposite direction
         *
         * @return
         */
        public abstract Direction getOpposite();
    }

    private LinkedList<Field> snakeBody;
    // the most recent field that the snake is no longer on
    private Field recentlyLeft;
    private Direction direction;
    private boolean reversed;
    public static final char SNAKE_SYMBOL = '*';

    public Snake(final Field start) {
        direction = Direction.RIGHT;
        snakeBody = new LinkedList<>();
        snakeBody.add(start);
        // Snake body could overlap with obstacles
        for (int i = 0; i< 2; i++) {
            snakeBody.addFirst(direction.getNextField(getHead()));
        }
        reversed = false;
    }

    /**
     * Modifies snake according to current direction
     */
    public void move() {
        Field head = getHead();
        addFirst(direction.getNextField(head));
        recentlyLeft = removeLast();
    }

    /**
     * Changes direction according to stroke
     * (doesn't change if new direction is the same or opposite to current)
     * @param stroke key pressed by user
     */
    public void changeDirection(KeyStroke stroke) {
        Direction newDirection = keyToDirectionMap.get(stroke.getKeyType());
        if(newDirection != null && this.direction != newDirection &&
                this.direction.getOpposite() != newDirection) {
            this.direction = newDirection;
        }
    }

    /**
     * Adds the last recently left square to the end
     */
    public void grow() {
        if(reversed) {
            snakeBody.addFirst(recentlyLeft);
        }
        else {
            snakeBody.addLast(recentlyLeft);
        }
    }

    /**
     * Reverses the snake movement
     */
    public void reverse() {
        this.reversed = !this.reversed;
        this.direction = this.direction.getOpposite();
    }

    public final Field getHead() {
        return reversed ? snakeBody.getLast(): snakeBody.getFirst();
    }

    public final Field getRecentlyLeft() {
        return recentlyLeft;
    }

    public final LinkedList<Field> getSnake() {
        return snakeBody;
    }

    public final int getSize() {
        return snakeBody.size();
    }

    private void addFirst(final Field field) {
        if (reversed) {
            snakeBody.addLast(field);
        }
        else {
            snakeBody.addFirst(field);
        }
    }

    private Field removeLast() {
        if (reversed) {
            return snakeBody.removeFirst();
        }
        else {
            return snakeBody.removeLast();
        }
    }

    private static final Map<KeyType, Direction> keyToDirectionMap = new HashMap<>(
            Map.ofEntries(
                    new AbstractMap.SimpleEntry<> (KeyType.ArrowLeft, Direction.LEFT),
                    new AbstractMap.SimpleEntry<> (KeyType.ArrowUp, Direction.UP),
                    new AbstractMap.SimpleEntry<> (KeyType.ArrowRight, Direction.RIGHT),
                    new AbstractMap.SimpleEntry<> (KeyType.ArrowDown, Direction.DOWN)
            )
    );
}
