package task4;

import java.io.*;
import java.util.*;

class FileManager {

    public void saveMapToFile(Map<String, Integer> data, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(data);
            System.out.println("Дані успішно збережено у файл: " + filePath);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Integer> loadMapFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (Map<String, Integer>) ois.readObject();
        }
    }
}