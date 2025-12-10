package task1;

import java.io.*;
import java.util.List;

class FileManager {

    public void saveToFile(String filePath, List<LineData> dataList) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(dataList);
            System.out.println("Дані успішно збережено у файл: " + filePath);
        }
    }

    @SuppressWarnings("unchecked")
    public List<LineData> readFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            return (List<LineData>) ois.readObject();
        }
    }
}
