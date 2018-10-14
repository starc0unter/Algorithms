package ru.chentsov.algorithms;

public class App {

    public static void main(String[] args) {
        System.out.println("7 ^ 8 = " + power(7, 8));
        moveRing("|1st|", "|2nd|", "|3rd|", 3);
    }

    public static int power(int n, int pow) {
        if (pow <= 1) return n;
        if (pow % 2 == 0) return power(n * n, pow / 2);
        else return n * power(n * n, (pow - 1) / 2);
    }

    public static void moveRing(String a, String b, String c, int height) {
        if (height == 0) return;
        moveRing(a, c, b, height - 1);
        System.out.println("From  " + a + "  to  " + c);
        moveRing(b, a, c, height - 1);
    }

}
