package task3;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();

        while (true) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Ввести дані та зашифрувати у файл");
            System.out.println("2. Прочитати та дешифрувати файл");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        handleEncryption(scanner, fileManager);
                        break;
                    case "2":
                        handleDecryption(scanner, fileManager);
                        break;
                    case "0":
                        System.out.println("Вихід з програми.");
                        return;
                    default:
                        System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (Exception e) {
                System.err.println("!!! Помилка виконання операції: " + e.getMessage());
            }
        }
    }

    private static void handleEncryption(Scanner scanner, FileManager fileManager) throws IOException {
        System.out.print("Введіть текст повідомлення для шифрування: ");
        String text = scanner.nextLine();
        DataContainer data = new DataContainer(text);

        System.out.print("Введіть директорію для збереження (напишіть '.' для поточної папки): ");
        String directory = scanner.nextLine();

        System.out.print("Введіть ім'я файлу (наприклад, 'data.enc'): ");
        String filename = scanner.nextLine();

        File file = new File(directory, filename);

        System.out.print("Введіть символ-ключ (наприклад, 'k'): ");
        String keyInput = scanner.nextLine();
        if (keyInput.isEmpty()) throw new IllegalArgumentException("Ключ не може бути порожнім");
        int key = keyInput.charAt(0);

        fileManager.saveEncryptedObject(file.getPath(), data, key);
        System.out.println("Файл збережено за адресою: " + file.getAbsolutePath());
    }

    private static void handleDecryption(Scanner scanner, FileManager fileManager) throws IOException, ClassNotFoundException {
        System.out.print("Введіть директорію, де лежить файл (напишіть '.' для поточної папки): ");
        String directory = scanner.nextLine();

        System.out.print("Введіть ім'я файлу для читання: ");
        String filename = scanner.nextLine();

        File file = new File(directory, filename);

        System.out.print("Введіть символ-ключ для дешифрування: ");
        String keyInput = scanner.nextLine();
        if (keyInput.isEmpty()) throw new IllegalArgumentException("Ключ не може бути порожнім");
        int key = keyInput.charAt(0);

        Object result = fileManager.loadEncryptedObject(file.getPath(), key);

        System.out.println("-> Результат читання:");
        System.out.println(result.toString());
    }
}