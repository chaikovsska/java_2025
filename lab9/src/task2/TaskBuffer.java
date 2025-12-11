package task2;

public class TaskBuffer<T> {
    private final Object[] buffer;
    private int head = 0;
    private int tail = 0;
    private final int capacity;

    public TaskBuffer(int capacity) {
        this.buffer = new Object[capacity + 1];
        this.capacity = capacity + 1;
    }

    public synchronized void put(T item) throws InterruptedException {
        while ((tail + 1) % capacity == head) {
            wait();
        }
        buffer[tail] = item;
        tail = (tail + 1) % capacity;
        notifyAll();
    }

    @SuppressWarnings("unchecked")
    public synchronized T take() throws InterruptedException {
        while (head == tail) {
            wait();
        }
        T item = (T) buffer[head];
        head = (head + 1) % capacity;
        notifyAll();
        return item;
    }
}