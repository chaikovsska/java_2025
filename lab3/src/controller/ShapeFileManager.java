package controller;
import model.Shape;
import java.io.*;

// 4. Виділіть роботу з файлами в окремий клас.
public class ShapeFileManager {

    // 2. Використовуйте об'єктні потоки (ObjectOutputStream)
    public void saveShapes(Shape[] shapes, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shapes);
        }
    }

    // 2. Використовуйте об'єктні потоки (ObjectInputStream)
    public Shape[] loadShapes(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Shape[]) ois.readObject();
        }
    }
}