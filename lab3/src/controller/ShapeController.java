package controller;

import model.*;
import view.ShapeView;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ShapeController {
    private Shape[] shapes;
    private ShapeView view;

    public ShapeController(int size) {
        this.view = new ShapeView();
        this.shapes = new Shape[size];
        generateRandomShapes();
    }

    private void generateRandomShapes() {
        Random random = new Random();
        String[] colors = {"Red","Blue","Green","Yellow","Black","Pink"};

        for (int i = 0; i < shapes.length; i++) {
            int type = random.nextInt(3);
            String color = colors[random.nextInt(colors.length)];
            switch(type) {
                case 0:
                    double width = 1 + random.nextDouble()*10;
                    double height = 1 + random.nextDouble()*10;
                    shapes[i] = new Rectangle(color, width, height);
                    break;
                case 1:
                    double base = 1 + random.nextDouble()*10;
                    double tHeight = 1 + random.nextDouble()*10;
                    shapes[i] = new Triangle(color, base, tHeight);
                    break;
                case 2:
                    double radius = 1 + random.nextDouble()*5;
                    shapes[i] = new Circle(color, radius);
                    break;
            }
        }
    }

    public void displayShapes() {
        view.displayMessage("Initial Shapes");
        view.displayShapes(shapes, true);
    }

    public double calcTotalArea() {
        double total = 0;
        for (Shape s : shapes) {
            total += s.calcArea();
        }
        return total;
    }

    public double calcTotalAreaByType(Class<? extends Shape> type) {
        double total = 0;
        for (Shape s : shapes) {
            if (type.isInstance(s)) {
                total += s.calcArea();
            }
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
