import java.util.*;

class CuratorRecord {
    private String lastName;
    private String firstName;
    private String birthDate;
    private String phone;
    private String address;

    public CuratorRecord(String lastName, String firstName, String birthDate, String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + ", дата народження: " + birthDate +
                ", телефон: " + phone + ", адреса: " + address;
    }
}

public class CuratorJournal {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<CuratorRecord> records = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.print("Введіть прізвище студента: ");
            String lastName = inputName();

            System.out.print("Введіть ім’я студента: ");
            String firstName = inputName();

            System.out.print("Введіть дату народження (dd.MM.yyyy): ");
            String birthDate = inputDate();

            System.out.print("Введіть телефон (+380XXXXXXXXX): ");
            String phone = inputPhone();

            System.out.print(
                    "Введіть адресу (приклади):\n" +
                            " - вул. Лесі Українки 12A кв.5\n" +
                            " - вул. Космічна-Будівельна буд. 13\n" +
                            " - просп. Шевченка 12 кв.3\n" +
                            " - бул. Тараса Шевченка буд. 25А\n" +
                            " - пл. Ринок буд. 7/9\n" +
                            " - село Ромашки буд. 25\n" +
                            " - м. Київ буд. 10 кв.12\n" +
                            "Адреса: "
            );
            String address = inputAddress();

            CuratorRecord record = new CuratorRecord(lastName, firstName, birthDate, phone, address);
            records.add(record);

            System.out.println("\nЗапис успішно додано!");
            showAllRecords();

            System.out.print("\nБажаєте додати ще одного студента? (так/ні): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("ні")) {
                break;
            }
        }
    }

    private static String inputName() {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.matches("[А-ЯЇІЄҐ][а-яїієґ]+(?:[-’'][А-ЯЇІЄҐ][а-яїієґ]+)*|[A-Z][a-z]+(?:[-’'][A-Z][a-z]+)*")) {
                return input;
            }

            System.out.print("Помилка! Ім’я/Прізвище повинно бути одним словом: з великої літери, решта маленькі (можна дефіс або апостроф). Спробуйте ще раз: ");
        }
    }

    private static String inputDate() {
        while (true) {
            String input = scanner.nextLine().trim();

            if (!input.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                System.out.print("Неправильний формат! Використовуйте dd.MM.yyyy: ");
                continue;
            }

            String[] parts = input.split("\\.");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (year < 1950 || year > 2025) {
                System.out.print("Рік має бути від 1900 до 2025. Спробуйте ще раз: ");
                continue;
            }

            if (month < 1 || month > 12) {
                System.out.print("Місяць має бути від 01 до 12. Спробуйте ще раз: ");
                continue;
            }

            int[] daysInMonth = {31, (isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int maxDay = daysInMonth[month - 1];

            if (day < 1 || day > maxDay) {
                System.out.print("У цьому місяці тільки " + maxDay + " днів. Спробуйте ще раз: ");
                continue;
            }

            return input;
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static String inputPhone() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.matches("\\+380\\d{9}")) {
                return input;
            }
            System.out.print("Неправильний телефон! Формат: +380XXXXXXXXX. Спробуйте ще раз: ");
        }
    }

    private static String inputAddress() {
        while (true) {
            String input = scanner.nextLine().trim();

            String pattern = "(?i)^(?:м\\.?|місто|село|смт)\\s+" +
                    "[\\p{L}]+(?:[\\s\\-][\\p{L}]+)*\\s+" +
                    "(?:вул\\.?|вулиця|просп\\.?|проспект|пров\\.?|провулок|бул\\.?|бульвар|пл\\.?|площа)\\s+" +
                    "[\\p{L}0-9'’.-]+(?:[\\s\\-][\\p{L}0-9'’.-]+)*\\s+" +
                    "(?:буд\\.?\\s*)?" +
                    "\\d+(?:[A-Za-zА-Яа-яґєії'’]*)?(?:/\\d+(?:[A-Za-zА-Яа-яґєії'’]*)?)?(?:-\\d+)?" +
                    "(?:\\s+кв\\.?\\s*\\d+[A-Za-zА-Яа-я]*)?$";

            if (input.matches(pattern)) {
                return input;
            }

            System.out.print(
                    "Помилка! Формат обов’язковий: спочатку місто/село, потім вулиця, потім будинок, квартира – необов’язково.\n" +
                            "Приклади:\n" +
                            " - м. Київ вул. Лесі Українки буд. 12А кв.5\n" +
                            " - село Ромашки вул. Шевченка 7\n" +
                            " - смт Бородянка просп. Перемоги буд. 25\n" +
                            "Спробуйте ще раз: "
            );
        }
    }

    private static void showAllRecords() {
        System.out.println("\nВсі записи в журналі куратора:");
        int i = 1;
        for (CuratorRecord r : records) {
            System.out.println(i + ". " + r);
            i++;
        }
    }
}