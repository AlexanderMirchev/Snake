package com.amirchev;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Game {
    private Screen screen;
//    private Terminal terminal;
    private Terrain terrain;
    private Snake snake;

//    private static final char GRASS_SYMBOL = ' ';
//    private static final char BRICK_SYMBOL = '#';

    public Game(Terrain terrain) throws IOException {
        this.terrain = terrain;
        this.snake = new Snake(new Field(1,1));
        this.screen = new TerminalScreen(new DefaultTerminalFactory().createTerminal());
    }

    public void run() throws InterruptedException, IOException {
        TextGraphics tg = screen.newTextGraphics();
        screen.startScreen();

        snake.getSnake().forEach((Field field) ->
                terrain.setField(field, snake.getSnakeSymbol()));

        boolean snakeIsAlive = true;
        while (snakeIsAlive){
            tg.drawImage(new TerminalPosition(0,0), this.terrain.getTerrain());
            screen.refresh();
            snakeIsAlive = applySnakeMovement();
            Thread.sleep(100);
        }
        System.out.println("You died");
        screen.readInput();
        screen.close();
    }

    private boolean applySnakeMovement() {
        terrain.setField(snake.getLast(), ' ');
        snake.move();
        if(terrain.isFieldWall(snake.getHead())) {
            return false;
        }
        else {
            terrain.setField(snake.getHead(), snake.getSnakeSymbol());
            return true;
        }
    }
}
