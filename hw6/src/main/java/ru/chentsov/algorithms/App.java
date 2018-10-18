package ru.chentsov.algorithms;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        //example of balanced tree
        Tree tree = new Tree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(2);
        tree.insert(14);
        tree.insert(17);
        tree.insert(1);
        tree.insert(13);
        tree.insert(16);
        tree.insert(18);
        tree.insert(7);     //remove this line to make tree unbalanced
        System.out.println(tree.isBalanced());

        int treesAmount = 20;
        int balancedTreesAmount = 0;

        for (int i = 0; i < treesAmount; i++) {
            if (generateTree().isBalanced()) balancedTreesAmount++;
        }

        System.out.printf("Balanced tree occurs in %.0f%% cases", 100 * ((double) balancedTreesAmount) / treesAmount);
    }

    public static Tree generateTree() {
        Tree tree = new Tree();
        Random random = new Random();
        int nodesAmount = 10;
        for (int i = 0; i < nodesAmount; i++) {
            tree.insert(random.nextInt(100));
        }
        return tree;
    }


}
