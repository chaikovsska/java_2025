import java.util.*;

public class UniqueCharsWordsLambda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть рядок:");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Рядок порожній!");
            return;
        }

        String[] words = input.split("[\\s,.;!?]+");

        String[] result = Arrays.stream(words)
                .filter(w -> w.matches("[\\p{L}'-]+"))
                .filter(w -> w.toLowerCase().chars().distinct().count() == w.length())
                .toArray(String[]::new);

        if (result.length == 0) {
            System.out.println("Немає слів з різних символів.");
        } else {
            System.out.println("Слова з різних символів:");
            Arrays.stream(result).forEach(System.out::println);
            System.out.println("Масив: " + Arrays.toString(result));
        }
    }
}