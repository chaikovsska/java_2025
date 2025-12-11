package task2;

class Translator implements Runnable {
    private final TaskBuffer<String> inputBuffer;
    private final TaskBuffer<String> outputBuffer;
    private final int id;

    public Translator(TaskBuffer<String> inputBuffer, TaskBuffer<String> outputBuffer, int id) {
        this.inputBuffer = inputBuffer;
        this.outputBuffer = outputBuffer;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String message = inputBuffer.take();

                String translatedMessage = "Потік № " + id + " переклав повідомлення " + message;

                outputBuffer.put(translatedMessage);

                ConsoleOutput.print("Translator " + id + ": " + translatedMessage);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}