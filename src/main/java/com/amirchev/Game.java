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
    private final Screen screen;
    private Terrain terrain;
    private Snake snake;


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
    public int run() throws InterruptedException, IOException {
        TextGraphics tg = screen.newTextGraphics();
        screen.startScreen();
        int delay = 500;

        drawSnake();

        Boost boost = generateAndDrawBoost();
        int boostAge = 0;

        boolean snakeIsAlive = true;
        while (snakeIsAlive){
            tg.drawImage(new TerminalPosition(0,0), this.terrain.getTerrain());
            screen.refresh();

            if(boost.isEaten() || boostAge >= 20000/delay) {
                if(!boost.isEaten()) {
                    terrain.setField(boost.getField(), Terrain.GRASS_SYMBOL);
                }
                boost = generateAndDrawBoost();
                boostAge = 0;
            }

            KeyStroke stroke = screen.pollInput();
            if(stroke != null) {
                snake.changeDirection(stroke);
            }
            Thread.sleep(delay);

            snake.move();

            final int preBoostSize = snake.getSize();
            final char snakeHeadFieldSymbol = terrain.getField(snake.getHead());
            if(snakeHeadFieldSymbol == Snake.SNAKE_SYMBOL ||
                snakeHeadFieldSymbol == Terrain.BRICK_SYMBOL) {
                snakeIsAlive = false;
            }
            else if(snakeHeadFieldSymbol == boost.getBoostSymbol()) {
                boost.applyOn(snake);
            }

            if(snakeIsAlive) {
                terrain.setField(snake.getHead(), Snake.SNAKE_SYMBOL);
                if(preBoostSize == snake.getSize()) {
                    terrain.setField(snake.getRecentlyLeft() ,Terrain.GRASS_SYMBOL);
                }
            }
            boostAge++;
        }
        screen.readInput();
        screen.close();
        return snake.getSize();
    }

    private void drawSnake() {
        snake.getSnake().forEach((Field field) ->
            terrain.setField(field, snake.SNAKE_SYMBOL));
    }

    private Boost generateAndDrawBoost() {
        Boost boost = BoostFactory.generateBoost(terrain);
        terrain.setField(boost.getField(), boost.getBoostSymbol());
        return boost;
    }
}
