package com.amirchev;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TextGraphics tg = screen.newTextGraphics();

        screen.startScreen();
        tg.putString(10,10, "Hello World");

        screen.refresh();
        screen.readInput();
        screen.close();
//        System.out.print("Input file name \n~ ");
//        Scanner scanner = new Scanner(System.in);
//        String filename = scanner.nextLine();
//        scanner.close();
//
//        try {
//            Terrain terrain = new Terrain(filename);
//            Game game = new Game(terrain);
//            game.run();
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        }
//        catch (NoSuchElementException e) {
//            System.out.println("File not in proper format");
//        }
//        catch (InterruptedException e) {
//            System.out.println("Game was stopped");
//        }
//        catch (IOException e) {
//
//        }
    }
}
