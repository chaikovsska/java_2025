import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[1_000_000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101);
        }

        long startTime = System.nanoTime();
        try (ForkJoinPool pool = new ForkJoinPool()) {
            System.out.println("Кількість потоків у пулі: " + pool.getParallelism());

            SumTask mainTask = new SumTask(array, 0, array.length);
            long totalSum = pool.invoke(mainTask);

            System.out.println("Сума всіх елементів масиву: " + totalSum);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.println("Час виконання: " + duration + " мс");
        }
    }
}