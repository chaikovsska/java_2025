package task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        List<LineData> inputData = new ArrayList<>();

        System.out.println("=== Пошук рядка з максимальною кількістю слів ===");

        try {
            System.out.print("Введіть шлях та ім'я файлу для збереження (наприклад, data.ser): ");
            String filePath = scanner.nextLine();

            System.out.println("Введіть рядки тексту (введіть 'exit' для завершення):");
            while (true) {
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                inputData.add(new LineData(line));
            }

            if (inputData.isEmpty()) {
                System.out.println("Дані не були введені.");
                return;
            }

            fileManager.saveToFile(filePath, inputData);

            System.out.println("--- Зчитування файлу та аналіз ---");

            List<LineData> loadedData = fileManager.readFromFile(filePath);

            LineData maxWordsLine = null;
            int maxCount = -1;

            for (LineData item : loadedData) {
                int currentCount = item.getWordCount();
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    maxWordsLine = item;
                }
            }

            if (maxWordsLine != null) {
                System.out.println("\nРЕЗУЛЬТАТ:");
                System.out.println("Рядок з максимальною кількістю слів (" + maxCount + "):");
                System.out.println(">> " + maxWordsLine.getContent());
            } else {
                System.out.println("Не вдалося знайти рядки.");
            }

        } catch (IOException e) {
            System.err.println("Помилка роботи з файлом: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Помилка класу серіалізації: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Виникла непередбачена помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
