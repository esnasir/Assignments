/**
 * @filename  - Main.java
 * @description - This class provides a console-based, menu-driven interface
 *                to test and interact with the MyString class. Users can
 *                perform various string operations like append, replace,
 *                palindrome check, splice, split, max character frequency,
 *                sort, shift, reverse, and count words.
 *
 * @package - assignment_1
 * @author  - Nasir
 */

package assignment_1;

import java.util.Scanner;

public class Main {

    /**
     * Entry point of the program.
     * Initializes a MyString object with user input and provides a menu
     * for performing various string operations interactively.
     * The program runs in a loop until the user chooses to exit.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initial input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        MyString myStr = new MyString(input);

        boolean running = true;

        while (running) {
            // Display current string and menu options
            System.out.println("\nCurrent String: " + myStr.getValue());
            System.out.println("\nSelect an operation:");
            System.out.println("1. Append");
            System.out.println("2. CountWords");
            System.out.println("3. Replace");
            System.out.println("4. isPalindrome");
            System.out.println("5. Splice");
            System.out.println("6. Split");
            System.out.println("7. MaxRepeatingCharacter");
            System.out.println("8. Sort");
            System.out.println("9. Shift");
            System.out.println("10. Reverse");
            System.out.println("0. Exit");
            System.out.print("\nEnter choice: ");

            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Append string
                    System.out.print("Enter string to append: ");
                    String toAppend = scanner.nextLine();
                    myStr.append(toAppend);
                    System.out.println("Result: " + myStr.getValue());
                    break;

                case 2: // Count words in string
                    int wordCount = myStr.countWords();
                    System.out.println("Word count: " + wordCount);
                    break;

                case 3: // Replace substring
                    System.out.print("Enter string to replace: ");
                    String oldStr = scanner.nextLine();
                    System.out.print("Enter replacement string: ");
                    String newStr = scanner.nextLine();
                    myStr.replace(oldStr, newStr);
                    System.out.println("Result: " + myStr.getValue());
                    break;

                case 4: // Check if palindrome
                    boolean isPal = myStr.isPalindrome();
                    System.out.println("Is palindrome: " + isPal);
                    break;

                case 5: // Splice string
                    System.out.print("Enter start index: ");
                    int start = scanner.nextInt();
                    System.out.print("Enter length: ");
                    int length = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    myStr.splice(start, length);
                    System.out.println("Result: " + myStr.getValue());
                    break;

                case 6: // Split string by pattern
                    System.out.print("Enter pattern to split by: ");
                    String pattern = scanner.nextLine();
                    String[] parts = myStr.split(pattern);
                    System.out.print("Result: [");
                    for (int i = 0; i < parts.length; i++) {
                        System.out.print("\"" + parts[i] + "\"");
                        if (i < parts.length - 1) System.out.print(", ");
                    }
                    System.out.println("]");
                    break;

                case 7: // Maximum repeating character
                    String maxChar = myStr.maxRepeat();
                    System.out.println("Max repeating character: " + maxChar);
                    break;

                case 8: // Sort characters
                    String sorted = myStr.sort();
                    System.out.println("Sorted: " + sorted);
                    break;

                case 9: // Shift string
                    System.out.print("Enter shift amount: ");
                    int n = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    myStr.shift(n);
                    System.out.println("Result: " + myStr.getValue());
                    break;

                case 10: // Reverse string
                    String reversed = myStr.reverse();
                    System.out.println("Reversed: " + reversed);
                    break;

                case 0: // Exit program
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default: // Invalid choice
                    System.out.println("Invalid choice");
            }
        }

        // Close scanner resource
        scanner.close();
    }
}
