package task2.controller;

import task2.model.Circle;
import task2.model.Rectangle;
import task2.model.Shape;
import task2.model.Triangle;
import task2.view.ShapeView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ShapeController {
    private Shape[] shapes;
    private ShapeView view;
    private ShapeFileManager fileManager;

    public ShapeController() {
        this.view = new ShapeView();
        this.fileManager = new ShapeFileManager();
    }

    public void run() {
        generateRandomShapes(10);

        boolean running = true;
        while (running) {
            view.printMenu();
            int choice = view.getIntInput();

            switch (choice) {
                case 1:
                    generateRandomShapes(10);
                    view.displayMessage("New random shapes generated.");
                    break;
                case 2:
                    view.displayShapes(shapes, true);
                    break;
                case 3:
                    printStats();
                    break;
                case 4:
                    sortByArea();
                    break;
                case 5:
                    sortByColor();
                    break;
                case 6:
                    saveData();
                    break;
                case 7:
                    loadData();
                    break;
                case 8:
                    searchBySet();
                    break;
                case 0:
                    running = false;
                    view.displayMessage("Exiting...");
                    break;
                default:
                    view.displayMessage("Invalid option.");
            }
        }
    }

    private void saveData() {
        String directory = view.getInput("Enter directory path (e.g., C:\\Temp or . for current): ");

        String filename = view.getInput("Enter filename (e.g., shapes.dat): ");

        String fullPath = directory + java.io.File.separator + filename;

        try {
            fileManager.saveShapes(shapes, fullPath);
            view.displayMessage("Shapes saved successfully to: " + fullPath);
        } catch (IOException e) {
            view.displayMessage("Error saving file (check if directory exists): " + e.getMessage());
        }
    }

    private void loadData() {
        String directory = view.getInput("Enter directory path (e.g., C:\\Temp or . for current): ");
        String filename = view.getInput("Enter filename to load: ");

        String fullPath = directory + java.io.File.separator + filename;

        try {
            this.shapes = fileManager.loadShapes(fullPath);
            view.displayMessage("Shapes loaded successfully from " + fullPath);
            view.displayMessage("\n--- Content of the file ---");
            view.displayShapes(shapes, false);
            view.displayMessage("---------------------------\n");

        } catch (IOException | ClassNotFoundException e) {
            view.displayMessage("Error loading file: " + e.getMessage());
        }
    }

    private void searchBySet() {
        view.displayMessage("\n--- Complex Search ---");

        String typeCriteria = view.getInput("Enter shape type (Circle, Rectangle, Triangle or 'any'): ");
        String colorCriteria = view.getInput("Enter color (Red, Blue... or 'any'): ");
        double areaCriteria = view.getDoubleInput("Enter minimum area: ");

        view.displayMessage("\nSearching for: Type=" + typeCriteria + ", Color=" + colorCriteria + ", Area > " + areaCriteria);

        boolean found = false;
        for (Shape s : shapes) {
            boolean matchType = typeCriteria.equalsIgnoreCase("any") ||
                    s.getClass().getSimpleName().equalsIgnoreCase(typeCriteria);

            boolean matchColor = colorCriteria.equalsIgnoreCase("any") ||
                    s.getShapeColor().equalsIgnoreCase(colorCriteria);

            boolean matchArea = s.calcArea() >= areaCriteria;

            if (matchType && matchColor && matchArea) {
                System.out.println(s);
                found = true;
            }
        }

        if (!found) {
            view.displayMessage("No shapes found matching this set of data.");
        }
    }

    private void generateRandomShapes(int size) {
        this.shapes = new Shape[size];
        Random random = new Random();
        String[] colors = {"Red", "Blue", "Green", "Yellow", "Black", "Pink"};
        for (int i = 0; i < shapes.length; i++) {
            int type = random.nextInt(3);
            String color = colors[random.nextInt(colors.length)];
            switch (type) {
                case 0:
                    shapes[i] = new Rectangle(color, 1 + random.nextDouble() * 10, 1 + random.nextDouble() * 10);
                    break;
                case 1:
                    shapes[i] = new Triangle(color, 1 + random.nextDouble() * 10, 1 + random.nextDouble() * 10);
                    break;
                case 2:
                    shapes[i] = new Circle(color, 1 + random.nextDouble() * 5);
                    break;
            }
        }
    }

    private void printStats() {
        view.displayMessage("Total area: " + calcTotalArea());
        view.displayMessage("Total area of Circles: " + calcTotalAreaByType(Circle.class));
        view.displayMessage("Total area of Triangles: " + calcTotalAreaByType(Triangle.class));
        view.displayMessage("Total area of Rectangles: " + calcTotalAreaByType(Rectangle.class));
    }

    public double calcTotalArea() {
        double total = 0;
        for (Shape s : shapes) total += s.calcArea();
        return total;
    }

    public double calcTotalAreaByType(Class<? extends Shape> type) {
        double total = 0;
        for (Shape s : shapes) {
            if (type.isInstance(s)) total += s.calcArea();
        }
        return total;
    }

    public void sortByArea() {
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
        view.displayMessage("\nSorted by area");
        view.displayShapes(shapes, false);
    }

    public void sortByColor() {
        Arrays.sort(shapes, Comparator.comparing(Shape::getShapeColor));
        view.displayMessage("\nSorted by color");
        view.displayShapes(shapes, false);
    }
}