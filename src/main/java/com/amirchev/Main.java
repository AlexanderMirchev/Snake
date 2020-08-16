package com.amirchev;

import com.amirchev.exceptions.InvalidTerrainFormat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
//        Terminal terminal = new DefaultTerminalFactory().createTerminal();
//        Screen screen = new TerminalScreen(terminal);
//        TextGraphics tg = screen.newTextGraphics();
//
//        screen.startScreen();
//        tg.putString(10,10, "Hello World");
//
//        screen.refresh();
//        screen.readInput();
//        screen.close();
        System.out.print("Input file name \n~ ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        scanner.close();
        try {
            Terrain terrain = new Terrain(filename);
//            terrain.print();
            Game game = new Game(terrain);
            game.run();
//            game.run();
        }
        catch (InvalidTerrainFormat e) {
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
//        catch (NoSuchElementException e) {
//            System.out.println("File not in proper format");
//        }
        catch (InterruptedException e) {
            System.out.println("Game was stopped");
        }
        catch (IOException e) {

        }
    }
}
