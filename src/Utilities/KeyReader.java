package Utilities;

import java.util.Scanner;

public class KeyReader {

    private static Scanner scanner = scanner = new Scanner(System.in); //Neccesary for read input from user


    public static int readKey(){
        return scanner.nextInt();
    }
}
