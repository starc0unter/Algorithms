package ru.chentsov.algorithms;

public class App
{
    static int powerCounter = 0;

    public static void main( String[] args )
    {
        int[] a = {1, 6, 5, 2, 6, 7, 1, 54, -1, -4, 0, 99};
        System.out.println("Result of power calculation is " + power(3, 15) + " and it took " + powerCounter
        + " iterations");
        System.out.println("Minimum value of the array is " + min(a));
        System.out.println("Average of the array values is " + average(a));
    }

    /**
     * Complexity of given algorithm is LOGARITHMIC. Why?
     * Conceptually, Math.pow(number, power) = number * number * number * ... * number (n - 1 times).
     * Here we do one multiplication and divide amount of required multiplications by 2.
     * That proves logarithmic complexity.
     * @param n is a number to be powered
     * @param pow is a power applied to number
     * @return powered number
     */
    private static int power(int n, int pow) {
        powerCounter++;
        if (pow == 1) return n;
        if (pow % 2 == 0) return power(n * n, pow / 2);
        else return n * power((n - 1) * (n - 1), pow /2);
    }

    /**
     * Complexity of given algorithm is LINEAR because to find the lowest value,
     * in the worst case we have to make N comparisons for N-sized array. This is
     * THE BEST possible complexity for an unordered/unsorted array.
     * @param a is a non-nul array
     * @return integer value - a minimum of the array a
     */
    private static int min(int[] a) {
        if (a.length == 0) throw new NullPointerException("given array is zero");
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) min = a[i];
        }
        return min;
    }

    /**
     * complexity of given algorithm us LINEAR because:
     * 1. Every value in a is independent from others;
     * 2. Every element take a crucial role in result.
     * So, the total complexity is O(N), where N is the elements number.
     * @param a is a non-nul array
     * @return double value - an average of a elements
     */
    private static double average(int[] a) {
        if (a.length == 0) throw new NullPointerException("given array is zero");
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum / a.length;
    }

}
