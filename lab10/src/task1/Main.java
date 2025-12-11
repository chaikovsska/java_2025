package task1;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ЧАСТИНА 1: Робота зі стрічковим літералом ===");

        String literalString = "JavaLab";
        String anotherReference = "JavaLab";

        System.out.println("До зміни (literalString): " + literalString);
        System.out.println("До зміни (anotherReference): " + anotherReference);

        System.out.print("Введіть нове значення для літералу: ");
        String newLiteralValue = scanner.nextLine();

        changeStringValue(literalString, newLiteralValue);

        System.out.println("Після зміни (literalString): " + literalString);
        System.out.println("Після зміни (anotherReference): " + anotherReference);


        System.out.println("\n=== ЧАСТИНА 2: Робота з введеним рядком ===");

        System.out.print("Введіть початковий рядок: ");
        String inputString = scanner.nextLine();

        System.out.println("До зміни: " + inputString);

        System.out.print("Введіть текст для заміни: ");
        String replacement = scanner.nextLine();

        changeStringValue(inputString, replacement);

        System.out.println("Після зміни: " + inputString);
    }

    private static void changeStringValue(String target, String newValue) {
        try {
            Field valueField = String.class.getDeclaredField("value");

            valueField.setAccessible(true);

            if (valueField.getType() == char[].class) {
                char[] newChars = newValue.toCharArray();
                valueField.set(target, newChars);
            } else if (valueField.getType() == byte[].class) {
                byte[] newBytes = newValue.getBytes();
                valueField.set(target, newBytes);
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Помилка рефлексії: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Невідома помилка: " + e.getMessage());
        }
    }
}