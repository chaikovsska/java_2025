package task2ANDtask3;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class.getName());

    public static ResourceBundle messages;

    public static void main(String[] args) {
        setupLogger();
        loadBundle(new Locale("uk"));

        logger.info("Програма запущена.");

        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();

        while (true) {
            System.out.println(messages.getString("menu.header"));
            System.out.println(messages.getString("menu.encrypt"));
            System.out.println(messages.getString("menu.decrypt"));
            System.out.println(messages.getString("menu.language"));
            System.out.println(messages.getString("menu.exit"));
            System.out.print(messages.getString("menu.choice"));

            String choice = scanner.nextLine();

            logger.fine("Користувач обрав пункт меню: " + choice);

            try {
                switch (choice) {
                    case "1":
                        handleEncryption(scanner, fileManager);
                        break;
                    case "2":
                        handleDecryption(scanner, fileManager);
                        break;
                    case "3":
                        changeLanguage(scanner);
                        break;
                    case "0":
                        System.out.println(messages.getString("menu.exit.msg"));
                        logger.info("Завершення роботи програми користувачем.");
                        return;
                    default:
                        logger.warning("Невірний вибір меню: " + choice);
                        System.out.println(messages.getString("menu.invalid"));
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Критична помилка виконання операції", e);
                System.err.println(messages.getString("error.op") + e.getMessage());
            }
        }
    }

    private static void loadBundle(Locale locale) {
        messages = ResourceBundle.getBundle("resources.location.data", locale);
    }

    private static void changeLanguage(Scanner scanner) {
        System.out.println(messages.getString("lang.change"));
        System.out.print(messages.getString("menu.choice"));
        String langChoice = scanner.nextLine();

        if (langChoice.equals("1")) {
            loadBundle(new Locale("uk"));
            logger.info("Мову змінено на Українську");
        } else if (langChoice.equals("2")) {
            loadBundle(new Locale("en"));
            logger.info("Language changed to English");
        } else {
            System.out.println(messages.getString("menu.invalid"));
            return;
        }
        System.out.println(messages.getString("lang.chosen"));
    }

    private static void setupLogger() {
        try {
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);

            FileHandler fileHandler = new FileHandler("app_log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);
            logger.addHandler(consoleHandler);

        } catch (IOException e) {
            System.err.println("Не вдалося налаштувати логування: " + e.getMessage());
        }
    }

    private static void handleEncryption(Scanner scanner, FileManager fileManager) throws IOException {
        System.out.print(messages.getString("prompt.text"));
        String text = scanner.nextLine();
        DataContainer data = new DataContainer(text);

        System.out.print(messages.getString("prompt.dir"));
        String directory = scanner.nextLine();

        System.out.print(messages.getString("prompt.filename"));
        String filename = scanner.nextLine();

        File file = new File(directory, filename);

        System.out.print(messages.getString("prompt.key"));
        String keyInput = scanner.nextLine();
        if (keyInput.isEmpty()) throw new IllegalArgumentException(messages.getString("error.key.empty"));
        int key = keyInput.charAt(0);

        logger.fine("Починаємо процес шифрування для файлу: " + file.getName());
        fileManager.saveEncryptedObject(file.getPath(), data, key);

        System.out.println(messages.getString("msg.file.path") + file.getAbsolutePath());
    }

    private static void handleDecryption(Scanner scanner, FileManager fileManager) throws IOException, ClassNotFoundException {
        System.out.print(messages.getString("prompt.read.dir"));
        String directory = scanner.nextLine();

        System.out.print(messages.getString("prompt.read.filename"));
        String filename = scanner.nextLine();

        File file = new File(directory, filename);

        System.out.print(messages.getString("prompt.key.decrypt"));
        String keyInput = scanner.nextLine();
        if (keyInput.isEmpty()) throw new IllegalArgumentException(messages.getString("error.key.empty"));
        int key = keyInput.charAt(0);

        logger.fine("Починаємо процес дешифрування файлу: " + file.getName());
        Object result = fileManager.loadEncryptedObject(file.getPath(), key);

        System.out.println(messages.getString("msg.result"));
        System.out.println(result.toString());
    }
}