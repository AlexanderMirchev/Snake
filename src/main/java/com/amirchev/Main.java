package com.amirchev;

import com.amirchev.exceptions.InvalidTerrainFormat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public final class Main {

    public static void main(String[] args) {
        System.out.print("Input file name \n~ ");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        scanner.close();

        try {
            Terrain terrain = new Terrain(filename);
            Game game = new Game(terrain);
            System.out.println(String.format("Game over! Your score is %d", game.run()));
        }
        catch (InvalidTerrainFormat e) {
            System.out.println(e.getMessage());
        }
        catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }
        catch (InterruptedException e) {
            System.out.println("Game was stopped");
        }
        catch (IOException e) {
            System.out.println("Error with the input");
        }
    }
}
