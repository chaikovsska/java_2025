import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Виберіть опцію для наповнення дерева:");
        System.out.println("1. Ввести числа вручну");
        System.out.println("2. Випадкові числа");
        int option = scanner.nextInt();

        if (option == 1) {
            System.out.println("Введіть числа через пробіл:");
            scanner.nextLine();
            String[] input = scanner.nextLine().split(" ");
            for (String numStr : input) {
                int num = Integer.parseInt(numStr);
                tree.insert(num);
            }
        } else if (option == 2) {
            System.out.println("Введіть кількість випадкових чисел:");
            int count = scanner.nextInt();
            int[] randomNumbers = new int[count];
            for (int i = 0; i < count; i++) {
                randomNumbers[i] = random.nextInt(100);
            }
            System.out.println("Згенеровані числа: " + Arrays.toString(randomNumbers));

            System.out.println("Додати числа у довільному порядку або впорядкувати?");
            System.out.println("1. Довільний порядок");
            System.out.println("2. Впорядкувати за зростанням");
            int orderOption = scanner.nextInt();

            if (orderOption == 2) {
                Arrays.sort(randomNumbers);
            } else if (orderOption != 1) {
                System.out.println("Невірна опція!");
                return;
            }

            for (int num : randomNumbers) {
                tree.insert(num);
            }
        } else {
            System.out.println("Невірна опція!");
            return;
        }

        scanner.close();

        System.out.println("Обхід дерева (inorder):");
        tree.inorder();
        System.out.println("\nСтруктура дерева:");
        tree.displayTree();
    }
}