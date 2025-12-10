package task2.model;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String shapeColor, double width, double height) {
        super(shapeColor);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }

    @Override
    public String toString() {
        return "Rectangle [color=" + shapeColor + ", width=" + width + ", height=" + height +
                ", area=" + calcArea() + "]";
    }
}