package com.pluralsight.ui;

import java.util.Scanner;

public class Console {

    public static final Scanner scanner = new Scanner(System.in);


    public static double askForDouble(String prompt) {

        System.out.print(prompt);
        double result =  scanner.nextDouble();
        scanner.nextLine();
        return result;

    }

    public static String askForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }


    public static int askForInt(String prompt) {

        System.out.print(prompt);
        int result =  scanner.nextInt();
        scanner.nextLine();
        return result;

    }


    public static boolean askForBoolean(String prompt) {
        System.out.print(prompt);
        String userInput = scanner.nextLine();
        return userInput.equalsIgnoreCase("YES");
        //opportunity to enhance this with some error protection.
    }

}