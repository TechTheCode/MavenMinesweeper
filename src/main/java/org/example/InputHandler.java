package org.example;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler(){
        scanner = new Scanner(System.in);
    }

    public String readString() {
        return scanner.next();

    }

    // Method to read an integer with validation
    public int readInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.println(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Method to read a specific command (like 'r', 'f', or 'end game')
    public String readCommand(String[] validCommands) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            for (String command : validCommands) {
                if (command.equalsIgnoreCase(input)) {
                    return input;
                }
            }
            System.out.println("Invalid command. Please enter one of the following: "
                    + String.join(", ", validCommands));
        }
    }

    public void consumeNextLine() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
