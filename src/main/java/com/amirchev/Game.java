package com.amirchev;

import com.amirchev.contents.FieldContentFactory;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Game extends JFrame implements KeyListener {
    private Terrain terrain;
    private Snake snake;

    public Game(Terrain terrain) {
        this.terrain = terrain;
        this.snake = new Snake(new Field(0,0));
        addKeyListener(this);
        setFocusable(true);
        setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run() throws InterruptedException, IOException {
        snake.getSnake().forEach((Field field) ->
                terrain.setField(field,
                        FieldContentFactory.make(FieldContentFactory.FieldContentEnum.SNAKE_SEGMENT)));

        while (true) {
            try {
                Runtime.getRuntime().exec("clear");
                terrain.setField(snake.getLast(), null);
                terrain.setField(snake.move(),
                        FieldContentFactory.make(FieldContentFactory.FieldContentEnum.SNAKE_SEGMENT));
                this.terrain.print();
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("You died");
                break;
            }
            Thread.sleep(100);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        snake.keyPressed(keyEvent);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        snake.keyPressed(keyEvent);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
