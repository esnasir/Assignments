package assignment_2;
import java.util.*;

/**
 * The {@code StringOperations} class provides a collection of static utility methods
 * for performing various string and numerical operations.
 * <p>
 * These include palindrome detection, Fibonacci computation, case conversion,
 * character counting, binary conversion, text compression, prime checking,
 * number-to-word translation, and substring analysis.
 *
 * <p>All methods are static and can be invoked directly using the class name.
 *
 * @author Nasir
 * @version 1.0
 */

public class Operations{
    // 1. Unique palindromes in a string
    public static int getUniquePalindromes(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += expandAndCollectPalindromes(s, i, i);
            result += expandAndCollectPalindromes(s, i, i + 1);
        }
        return result;
    }
    private static int expandAndCollectPalindromes(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > 1) count++;
            left--; right++;
        }
        return count;
    }

    // 2. Nth fibonacci number
    public static int getNthFibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;
        int a = 1, b = 1;
        for (int i = 3; i <= n; i++) {
            int temp = a + b;
            a = b; b = temp;
        }
        return b;
    }

    // 3. Snake case to camel case
    public static String convertSnakeToCamel(String snakeCase) {
        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;
        for (char ch : snakeCase.toCharArray()) {
            if (ch == '_') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                camelCase.append(Character.toUpperCase(ch));
                capitalizeNext = false;
            } else {
                camelCase.append(camelCase.isEmpty() ? Character.toLowerCase(ch) : ch);
            }
        }
        return camelCase.toString();
    }

    // 4. Count consonants
    public static int getConsonantCount(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("bcdfghjklmnpqrstvwxyz".indexOf(c) >= 0) count++;
        }
        return count;
    }

    // 5. Binary to decimal
    public static int convertBinaryToDecimal(String binary) {
        int decimal = 0, base = 1;
        for(int i = binary.length() - 1; i >= 0; i--) {
            if(binary.charAt(i) == '1') {
                decimal += base;
            }
            base *= 2;
        }

        return decimal;
    }

    // 6. Expand characters as per digits
    public static String expandCharacters(String stringWithDigits) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < stringWithDigits.length(); i += 2) {
            char ch = stringWithDigits.charAt(i);
            int count = Character.getNumericValue(stringWithDigits.charAt(i + 1));
            for (int j = 0; j < count; j++) result.append(ch);
        }
        return result.toString();
    }

    // 7. Character frequency (compressed)
    public static String getCharacterFrequency(String str) {
        StringBuilder compressedString = new StringBuilder();
        int i = 0;
        while (i < str.length()) {
            char ch = str.charAt(i);
            int count = 1;
            while (i + 1 < str.length() && str.charAt(i + 1) == ch) {
                i++; count++;
            }
            compressedString.append(ch).append(count);
            i++;
        }
        return compressedString.toString();
    }

    // 8. Prime number checker
    public static String checkPrimeNumber(int num) {
        if (num <= 1) return "The given number is NOT prime";
        for (int i = 2; i <= Math.sqrt(num); i++)
            if (num % i == 0) return "The given number is NOT prime";
        return "The given number is PRIME";
    }

    // 9. Number to words
    public static String convertNumberToWords(int num) {
        if (num == 0) return "zero";
        String[] belowTwenty = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
                "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty",
                "seventy", "eighty", "ninety"};
        StringBuilder words = new StringBuilder();

        if (num >= 100) {
            words.append(belowTwenty[num / 100]).append(" hundred ");
            num %= 100;
        }
        if (num >= 20) {
            words.append(tens[num / 10]).append(" ");
            num %= 10;
        }
        if (num > 0) {
            words.append(belowTwenty[num]);
        }
        return words.toString().trim();
    }

    // 10. Length of longest substring without repeating characters
    public static int getLongestUniqueSubstringLength(String s) {
        Set<Character> seen = new HashSet<>();
        int maxLen = 0, start = 0;
        for (int end = 0; end < s.length(); end++) {
            while (seen.contains(s.charAt(end))) {
                seen.remove(s.charAt(start++));
            }
            seen.add(s.charAt(end));
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}