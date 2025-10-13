import java.util.*;

public class UniqueCharsWords {
    public static boolean hasAllUniqueChars(String word) {
        Set<Character> seen = new HashSet<>();
        for (char c : word.toCharArray()) {
            if (seen.contains(c)) {
                return false;
            }
            seen.add(c);
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть рядок:");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Рядок порожній!");
            return;
        }

        String[] words = input.split("[\\s,.;!?]+");

        List<String> uniqueWords = new ArrayList<>();

        for (String word : words) {
            if (word.matches("[\\p{L}'-]+") && hasAllUniqueChars(word.toLowerCase())) {
                uniqueWords.add(word);
            }
        }

        if (uniqueWords.isEmpty()) {
            System.out.println("Немає слів з різних символів.");
        } else {
            System.out.println("Слова з різних символів:");
            for (String word : uniqueWords) {
                System.out.println(word);
            }
            System.out.println("Масив: " + Arrays.toString(uniqueWords.toArray(new String[0])));
        }
    }
}