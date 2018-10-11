package ru.chentsov.algorithms.hw;

import ru.chentsov.algorithms.webinar.Stack;

import java.util.Scanner;

public class StringReverser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        System.out.println(reverseText(text));
    }

    private static String reverseText(String text) {
        char[] chars = text.toCharArray();
        Stack stack = new Stack(chars.length);
        for (int i = 0; i < chars.length; i++) {
            stack.insert(chars[i]);
        }
        StringBuilder reversedText = new StringBuilder();
        while (!stack.isEmpty()) {
            reversedText.append((char) stack.remove());
        }
        return reversedText.toString();
    }

}
