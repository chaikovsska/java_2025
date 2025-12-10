package task2.view;

import task2.model.Shape;

import java.util.Scanner;

public class ShapeView {
    private Scanner scanner;

    public ShapeView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayShapes(Shape[] shapes, boolean drawShapes) {
        if (shapes == null || shapes.length == 0) {
            System.out.println("No shapes available.");
            return;
        }
        for (Shape s : shapes) {
            if (drawShapes) {
                s.draw();
            }
            System.out.println(s);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Generate new random shapes");
        System.out.println("2. Display shapes");
        System.out.println("3. Calculate total area");
        System.out.println("4. Sort by Area");
        System.out.println("5. Sort by Color");
        System.out.println("6. Save shapes to file");
        System.out.println("7. Load shapes from file");
        System.out.println("8. Find shapes (Search)");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}