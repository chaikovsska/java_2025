import controller.ShapeController;

public class Main {
    public static void main(String[] args) {
        // Тепер контролер сам керує потоком виконання через метод run()
        ShapeController controller = new ShapeController();
        controller.run();
    }
}