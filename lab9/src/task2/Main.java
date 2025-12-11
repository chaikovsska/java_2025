package task2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskBuffer<String> buffer1 = new TaskBuffer<>(10);
        TaskBuffer<String> buffer2 = new TaskBuffer<>(10);

        for (int i = 0; i < 5; i++) {
            Thread producer = new Thread(new Producer(buffer1, i + 1));
            producer.setDaemon(true);
            producer.start();
        }

        for (int i = 0; i < 2; i++) {
            Thread translator = new Thread(new Translator(buffer1, buffer2, i + 1));
            translator.setDaemon(true);
            translator.start();
        }

        System.out.println("--- Main починає вичитувати дані ---");
        for (int i = 0; i < 100; i++) {
            String message = buffer2.take();
            ConsoleOutput.print("Main: " + message);
        }

        System.out.println("--- Main завершив роботу (вичитано 100 повідомлень) ---");
    }
}
