/**
 * @filename - Main.java
 * @description - This class provides a console-based menu to test and execute
 *                various string and number operations defined in the
 *                StringOperations class.
 *                The user can interactively select an operation, provide input,
 *                and view results.
 *
 * @package - assignment_2
 * @author  - Nasir
 */

package assignment_2;
import java.util.*;

public class Main {

    /**
     * Entry point of the program.
     * Displays a menu-driven interface allowing users to perform multiple
     * string and number-based operations
     * The program runs in an infinite loop until the user chooses to exit.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("\n====== MENU ======");
            System.out.println("1. Count Unique Palindromes");
            System.out.println("2. Get Nth Fibonacci Number");
            System.out.println("3. Convert Snake Case to Camel Case");
            System.out.println("4. Count Consonants in String");
            System.out.println("5. Convert Binary to Decimal");
            System.out.println("6. Expand Characters in String");
            System.out.println("7. Get Character Frequency (Compressed)");
            System.out.println("8. Check Prime Number");
            System.out.println("9. Convert Number to Words");
            System.out.println("10. Get Length of Longest Substring Without Repeating Characters");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            // Input choice validation
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 11.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter a string: ");
                    String palindromeInput = scanner.nextLine();
                    System.out.println("Count: " + Operations.getUniquePalindromes(palindromeInput));
                    break;

                case 2:
                    System.out.print("Enter N: ");
                    int nFibo = scanner.nextInt();
                    System.out.println("Nth Fibonacci Number: " + Operations.getNthFibonacci(nFibo));
                    break;

                case 3:
                    System.out.print("Enter snake_case string: ");
                    String snakeInput = scanner.nextLine();
                    System.out.println("CamelCase: " + Operations.convertSnakeToCamel(snakeInput));
                    break;

                case 4:
                    System.out.print("Enter a string: ");
                    String consInput = scanner.nextLine();
                    System.out.println("Consonant count: " + Operations.getConsonantCount(consInput));
                    break;

                case 5:
                    System.out.print("Enter binary string: ");
                    String binaryInput = scanner.nextLine();
                    System.out.println("Decimal: " + Operations.convertBinaryToDecimal(binaryInput));
                    break;

                case 6:
                    System.out.print("Enter string (e.g., a1b4c3): ");
                    String charExpandInput = scanner.nextLine();
                    System.out.println("Expanded string: " + Operations.expandCharacters(charExpandInput));
                    break;

                case 7:
                    System.out.print("Enter a string: ");
                    String freqInput = scanner.nextLine();
                    System.out.println("Compressed frequency: " + Operations.getCharacterFrequency(freqInput));
                    break;

                case 8:
                    System.out.print("Enter a number: ");
                    int primeInput = scanner.nextInt();
                    System.out.println(Operations.checkPrimeNumber(primeInput));
                    break;

                case 9:
                    System.out.print("Enter an integer: ");
                    int numToWords = scanner.nextInt();
                    System.out.println("In words: " + Operations.convertNumberToWords(numToWords));
                    break;

                case 10:
                    System.out.print("Enter a string: ");
                    String uniqueSubstrInput = scanner.nextLine();
                    System.out.println("Longest substring length: " +
                            Operations.getLongestUniqueSubstringLength(uniqueSubstrInput));
                    break;

                case 11:
                    System.out.println("Exiting the program. Thank you!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please enter a valid option (1–11).");
            }
        }
    }
}
