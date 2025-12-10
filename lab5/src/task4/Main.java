package task4;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TagAnalyzer analyzer = new TagAnalyzer();
        FileManager fileManager = new FileManager();
        Map<String, Integer> tagData = null;

        System.out.println("=== Аналізатор HTML тегів ===");

        try {
            System.out.print("Введіть URL адресу (наприклад, https://google.com): ");
            String url = scanner.nextLine();

            System.out.println("Завантаження та аналіз сторінки...");
            tagData = analyzer.countTags(url);

            if (tagData.isEmpty()) {
                System.out.println("Тегів не знайдено або сторінка порожня.");
                return;
            }

            System.out.println("\n[A] Сортування за назвою тегу (A-Z):");
            tagData.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> System.out.printf("%-15s : %d%n", entry.getKey(), entry.getValue()));

            System.out.println("\n[B] Сортування за частотою (зростання):");
            tagData.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.printf("%-15s : %d%n", entry.getKey(), entry.getValue()));

            System.out.println("\n--- Збереження результатів ---");
            System.out.print("Введіть ім'я файлу для збереження (наприклад, tags.dat): ");
            String filePath = scanner.nextLine();

            fileManager.saveMapToFile(tagData, filePath);

            System.out.print("\nБажаєте зчитати файл для перевірки? (так/ні): ");
            String checkChoice = scanner.nextLine();

            if (checkChoice.equalsIgnoreCase("так") || checkChoice.equalsIgnoreCase("yes") || checkChoice.equals("+")) {
                System.out.println("Зчитування файлу...");
                Map<String, Integer> loadedData = fileManager.loadMapFromFile(filePath);

                System.out.println("\n--- Дані, завантажені з файлу (" + filePath + ") ---");
                loadedData.forEach((key, value) -> System.out.println(key + " : " + value));
            }

        } catch (java.net.MalformedURLException e) {
            System.err.println("Помилка: Некоректний формат URL. Додайте http:// або https://");
        } catch (java.net.UnknownHostException e) {
            System.err.println("Помилка: Сайт недоступний.");
        } catch (ClassNotFoundException e) {
            System.err.println("Помилка десеріалізації (клас не знайдено): " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Помилка вводу/виводу: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непередбачувана помилка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
