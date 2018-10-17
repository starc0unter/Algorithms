package ru.chentsov.algorithms;

import java.util.Arrays;

public class Rucksack {

    private int capacity;
    private int[] weight;
    private int[] value;

    public Rucksack(int capacity, int[] weight, int[] value) {
        this.capacity = capacity;
        this.weight = weight;
        this.value = value;
    }

    public static void main(String[] args) {
        int[] weights = {23, 31, 29, 44, 53, 38, 63, 85, 89, 82};
        System.out.println("Weights: " + Arrays.toString(weights));
        int[] values = {92, 57, 49, 68, 60, 43, 67, 84, 87, 72};
        System.out.println("Values: " + Arrays.toString(weights));

        int capacity = 165;
        Rucksack rucksack = new Rucksack(capacity, weights, values);
        int items = weights.length;
        System.out.println("Maximum value for capacity " + capacity + " is " + rucksack.knapsack(items, rucksack.capacity));
    }

    public int knapsack(int items, int capacity) {
        if (items <= 0) return 0;
        int i = items - 1;
        if (weight[i] > capacity) return knapsack(items - 1, capacity);
        else return Math.max(knapsack(items - 1, capacity),
                knapsack(items - 1, capacity - weight[i]) + value[i]);
    }

}
