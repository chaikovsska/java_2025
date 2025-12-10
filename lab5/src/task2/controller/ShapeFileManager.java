package task2.controller;

import task2.model.Shape;
import java.io.*;

public class ShapeFileManager {

    public void saveShapes(Shape[] shapes, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shapes);
        }
    }

    public Shape[] loadShapes(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Shape[]) ois.readObject();
        }
    }
}