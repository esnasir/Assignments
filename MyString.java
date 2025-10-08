/**
* @filename - MyString.java
* @description - this will perform most of the string operations
* @author - Nasir Ahmed
*/


import java.util.Scanner;

public class MyString {
    private String value;
    
    public MyString(String str) {
        this.value = str;
    }
    
    public void append(String newString) {
        this.value += newString;
    }
    
    public int countWords() {
        if (value == null || value.trim().isEmpty()) {
            return 0;
        }
        String[] words = value.trim().split("\\s+");
        return words.length;
    }
    
    public void replace(String oldStr, String newStr) {
        this.value = value.replace(oldStr, newStr);
    }
    
    public boolean isPalindrome() {
        String clean = value.replaceAll("\\s+", "").toLowerCase();
        int left = 0;
        int right = clean.length() - 1;
        
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    public void splice(int start, int length) {
        if (start < 0 || start >= value.length()) {
            return;
        }
        
        int end = Math.min(start + length, value.length());
        String before = value.substring(0, start);
        String after = value.substring(end);
        this.value = before + after;
    }
    
    public String[] split(String pattern) {
        return value.split(pattern);
    }
    
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
        
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            if (freq[ch] > maxCount) {
                maxCount = freq[ch];
                maxChar = ch;
            }
        }
        
        return maxChar + " -> " + maxCount;
    }
    
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
    
    public void shift(int n) {
        if (value == null || value.isEmpty() || n <= 0) {
            return;
        }
        
        n = n % value.length();
        String shifted = value.substring(n) + value.substring(0, n);
        this.value = shifted;
    }
    
    public String reverse() {
        StringBuilder reversed = new StringBuilder();
        for (int i = value.length() - 1; i >= 0; i--) {
            reversed.append(value.charAt(i));
        }
        return reversed.toString();
    }
    
    public String getValue() {
        return this.value;
    }
}
