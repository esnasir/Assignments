/**
 * @filename  - MyString.java
 * @description - This class provides custom implementations for some
 *                string manipulation operations such as counting words,
 *                replacing substrings, checking for palindrome,
 *                sorting, shifting, reversing, and splitting strings
 *                (without using Java's built-in split()).
 *
 * @package - assignment_1
 * @author  - Nasir
 */

package assignment_1;

public class MyString {
    /** Holds the string value on which operations are performed. */
    private String value;


    // Constructor to initialize MyString with a given value.
    public MyString(String str) {
        this.value = str;
    }


    // Appends another string to the current string.
    public void append(String newString) {
        this.value += newString;
    }

    /*
    Counts the total number of words in the string.
    Words are assumed to be separated by one or more spaces.
     */
    public int countWords() {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }

        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (Character.isWhitespace(ch)) {
                inWord = false;
            } else if (!inWord) {
                count++;
                inWord = true;
            }
        }

        return count;
    }

    // Replaces all occurrences of oldStr with newStr in the string.
    public void replace(String oldStr, String newStr) {
        this.value = value.replace(oldStr, newStr);
    }

    // Checks if the string is a palindrome (ignores spaces and case).
    public boolean isPalindrome() {
        String clean = value.replaceAll("\\s+", "").toLowerCase();
        int left = 0, right = clean.length() - 1;

        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Removes a substring starting at a given index for a given length.
    public void splice(int start, int length) {
        if (start < 0 || start >= value.length() || length <= 0) {
            return;
        }

        int end = Math.min(start + length, value.length());
        String before = value.substring(0, start);
        String after = value.substring(end);
        this.value = before + after;
    }


    // Splits the string based on a given pattern (manual implementation).
    public String[] split(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return new String[]{value};
        }

        int parts = 0;
        int index = 0;
        // Count how many parts we’ll have
        while (true) {
            int pos = value.indexOf(pattern, index);
            if (pos == -1) {
                parts++;
                break;
            }
            parts++;
            index = pos + pattern.length();
        }

        String[] result = new String[parts];
        int start = 0;
        int idx = 0;

        while (true) {
            int pos = value.indexOf(pattern, start);
            if (pos == -1) {
                result[idx++] = value.substring(start);
                break;
            }
            result[idx++] = value.substring(start, pos);
            start = pos + pattern.length();
        }

        return result;
    }

    // Finds the most frequently occurring character in the string.
    public String maxRepeat() {
        if (value == null || value.isEmpty()) {
            return "No characters";
        }

        int[] freq = new int[256];
        for (int i = 0; i < value.length(); i++) {
            freq[value.charAt(i)]++;
        }

        char maxChar = value.charAt(0);
        int maxCount = freq[maxChar];

        for (int i = 1; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (freq[ch] > maxCount) {
                maxCount = freq[ch];
                maxChar = ch;
            }
        }

        return maxChar + " -> " + maxCount;
    }

    // Sorts the characters in the string in ascending order.
    public String sort() {
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] > chars[j]) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
        }
        return new String(chars);
    }

    // Shifts the string to the left by n characters.
    public void shift(int n) {
        if (value == null || value.isEmpty() || n <= 0) {
            return;
        }

        n = n % value.length();
        this.value = value.substring(n) + value.substring(0, n);
    }

    // Reverses the current string.
    public String reverse() {
        StringBuilder reversed = new StringBuilder();
        for (int i = value.length() - 1; i >= 0; i--) {
            reversed.append(value.charAt(i));
        }
        return reversed.toString();
    }

    // Returns the current value of the string.
    public String getValue() {
        return this.value;
    }
}
