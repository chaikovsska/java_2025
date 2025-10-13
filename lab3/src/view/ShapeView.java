package view;

import model.Shape;

public class ShapeView {
    public void displayShapes(Shape[] shapes, boolean drawShapes) {
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

}