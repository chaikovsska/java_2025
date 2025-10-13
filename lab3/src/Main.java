import controller.ShapeController;
import model.Circle;
import model.Rectangle;
import model.Triangle;

public class Main {
    public static void main(String[] args) {
        ShapeController controller = new ShapeController(10);

        controller.displayShapes();

        System.out.println("Total area: " + controller.calcTotalArea());

        System.out.println("Total area of all Circles: " + controller.calcTotalAreaByType(Circle.class));
        System.out.println("Total area of all Triangles: " + controller.calcTotalAreaByType(Triangle.class));
        System.out.println("Total area of all Rectangles: " + controller.calcTotalAreaByType(Rectangle.class));

        controller.sortByArea();
        controller.sortByColor();
    }
}
