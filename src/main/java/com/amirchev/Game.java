package com.amirchev;

import com.amirchev.boosts.Boost;
import com.amirchev.boosts.BoostFactory;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

/**
 * Class for game representation
 */
public final class Game {
    private Screen screen;
    private Terrain terrain;
    private Snake snake;
    private boolean gameOver = false;

    private static final int DELAY = 400;
    private static final int BOOST_TIMEOUT = 20000;

    public Game(Terrain terrain) throws IOException {
        this.terrain = terrain;
        this.snake = new Snake(new Field(1,1));
        this.screen = new TerminalScreen(new DefaultTerminalFactory().createTerminal());
    }

    /**
     * Opens game screen
     *
     * @return score
     * @throws InterruptedException
     * @throws IOException
     */
    public final int run() throws InterruptedException, IOException {
        TextGraphics tg = screen.newTextGraphics();
        screen.startScreen();
        drawSnake();

        Boost boost = generateAndDrawBoost();
        int boostAge = 0;

        while (!gameOver){
            tg.drawImage(new TerminalPosition(0,0), this.terrain.getTerrain());
            screen.refresh();

            if(boost.isEaten() || boostAge >= BOOST_TIMEOUT/DELAY) {
                if(!boost.isEaten()) {
                    terrain.setField(boost.getField(), Terrain.GRASS_SYMBOL);
                }
                boost = generateAndDrawBoost();
                boostAge = 0;
            }

            checkUserInput();

            Thread.sleep(DELAY);

            snake.move();

            final int preBoostSize = snake.getSize();
            final char snakeHeadFieldSymbol = terrain.getField(snake.getHead());

            if(snakeHeadFieldSymbol == Snake.SNAKE_SYMBOL ||
                snakeHeadFieldSymbol == Terrain.BRICK_SYMBOL) {
                gameOver = true;
            }
            else if(snakeHeadFieldSymbol == boost.getBoostSymbol()) {
                boost.applyOn(snake);
            }

            if(!gameOver) {
                terrain.setField(snake.getHead(), Snake.SNAKE_SYMBOL);
                if(preBoostSize == snake.getSize()) {
                    terrain.setField(snake.getRecentlyLeft() ,Terrain.GRASS_SYMBOL);
                }
            }
            else {
                tg.putString(0, terrain.getHeight(), "Game over!");
                screen.refresh();
            }
            boostAge++;
        }
        screen.readInput();
        screen.close();
        return snake.getSize();
    }

    private void drawSnake() {
        snake.getSnake().forEach((Field field) ->
            terrain.setField(field, Snake.SNAKE_SYMBOL));
    }

    private Boost generateAndDrawBoost() {
        Boost boost = BoostFactory.generateBoost(terrain);
        terrain.setField(boost.getField(), boost.getBoostSymbol());
        return boost;
    }

    private void checkUserInput() throws IOException {
        KeyStroke stroke = screen.pollInput();
        if(stroke != null) {
            snake.changeDirection(stroke);
        }
    }
}
