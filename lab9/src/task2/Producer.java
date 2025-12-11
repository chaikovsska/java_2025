package task2;

class Producer implements Runnable {
    private final TaskBuffer<String> buffer;
    private final int id;

    public Producer(TaskBuffer<String> buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                String message = "Потік № " + id + " згенерував повідомлення " + (i + 1);
                buffer.put(message);
                ConsoleOutput.print("Producer " + id + ": " + message);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
