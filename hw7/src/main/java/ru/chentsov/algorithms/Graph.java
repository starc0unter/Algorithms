package ru.chentsov.algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {
    private class Vertex {
        char label;
        boolean wasVisited;

        public Vertex(char label) {
            this.label = label;
            this.wasVisited = false;
        }

        @Override
        public String toString() {
            return label + " ";
        }
    }

    private final int MAX_VERTICES;
    private Vertex[] vertices;
    private int[][] adjMatrix;
    private int size;
    private boolean isWeighted = false;

    public Graph(int maxVertices) {
        this.MAX_VERTICES = maxVertices;
        vertices = new Vertex[MAX_VERTICES];
        adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        this.size = 0;
    }

    private int getVertexByLabel(char label) {
        for (int pos = 0; pos < vertices.length; pos++) {
            if (vertices[pos].label == label) return pos;
        }
        return -1;
    }

    public void addVertex(char label) {
        vertices[size++] = new Vertex(label);
    }

    public void addUndirectedEdge(char fromLabel, char toLabel, int weight) {
        int from = getVertexByLabel(fromLabel);
        int to = getVertexByLabel(toLabel);
        addEdge(from, to, weight);
        adjMatrix[to][from] = weight;
    }

    public void addDirectedEdge(char fromLabel, char toLabel, int weight) {
        int from = getVertexByLabel(fromLabel);
        int to = getVertexByLabel(toLabel);
        addEdge(from, to, weight);
    }

    private void addEdge(int from, int to, int weight) {
        if (from == -1 || to == -1 || weight < 0) throw new IllegalArgumentException("Vertices not found");
        if (weight != 1) isWeighted = true;
        adjMatrix[from][to] = weight;
    }

    public void printVertex(int vertex) {
        System.out.print(vertices[vertex] + " ");
    }

    private int getUnvisitedVertex(int vertex) {
        for (int i = 0; i < size; i++) {
            if (adjMatrix[vertex][i] > 0 && !vertices[i].wasVisited)
                return i;
        }
        return -1;
    }

    private void resetFlags() {
        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void depthTravers() {
        Stack stack = new Stack(MAX_VERTICES);
        vertices[0].wasVisited = true;
        printVertex(0);
        stack.insert(0);
        while (!stack.isEmpty()) {
            int v = getUnvisitedVertex(stack.peek());
            if (v == -1) {
                stack.remove();
            } else {
                vertices[v].wasVisited = true;
                printVertex(v);
                stack.insert(v);
            }
        }
        resetFlags();
    }

    public void widthTravers() {
        Queue queue = new Queue(MAX_VERTICES);
        vertices[0].wasVisited = true;
        printVertex(0);
        queue.insert(0);
        while (!queue.isEmpty()) {
            int vCurr = queue.remove();
            int vNext;
            while ((vNext = getUnvisitedVertex(vCurr)) != -1) {
                vertices[vNext].wasVisited = true;
                printVertex(vNext);
                queue.insert(vNext);
            }
        }
        resetFlags();
    }

    /**
     * Generates the shortest path for an unweighted undirected graph
     *
     * @param fromLabel a vertex to start from
     * @param toLabel   a vertex to get to
     */
    public void getShortestPathUW(char fromLabel, char toLabel) {
        int from = getVertexByLabel(fromLabel);
        int to = getVertexByLabel(toLabel);
        if (from == -1 || to == -1) throw new IllegalArgumentException("Vertices not found");
        if (isWeighted)
            throw new RuntimeException("Trying to find the shortest path using inappropriate algorithm");

        int[] edgeTo = new int[MAX_VERTICES];
        Arrays.fill(edgeTo, -1);
        Queue queue = new Queue(MAX_VERTICES);

        queue.insert(from);
        edgeTo[from] = from;
        vertices[from].wasVisited = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            int next;
            while ((next = getUnvisitedVertex(current)) != -1) {
                edgeTo[next] = current;
                if (next == to) {
                    printShortestPath(edgeTo, next);
                    resetFlags();
                    return;
                }
                vertices[next].wasVisited = true;
                queue.insert(next);
            }
        }
        System.out.println("No path found");
        resetFlags();
    }

    /**
     * Prints the shortest path if the latter exists
     *
     * @param edgeTo an array of reverted path
     * @param v last vertex in path
     */
    private void printShortestPath(int[] edgeTo, int v) {
        java.util.Stack<String> stack = new java.util.Stack<>();
        while (edgeTo[v] != v) {
            if (edgeTo[v] == -1){
                System.out.println("No path found");
                return;
            }
            stack.add("From " + vertices[edgeTo[v]]  + "to " + vertices[v]);
            v = edgeTo[v];
        }
        while (!stack.isEmpty()) System.out.println(stack.pop());
    }

    /**
     * Generates the shortest path for a weighted directed graph [Dijkstra algorithm]
     *
     * @param fromLabel a vertex to start from
     * @param toLabel   a vertex to get to
     */
    public void getShortestPathW(char fromLabel, char toLabel) {
        int from = getVertexByLabel(fromLabel);
        int to = getVertexByLabel(toLabel);
        if (from == -1 || to == -1) throw new IllegalArgumentException("Vertices not found");
        if (!isWeighted)
            throw new RuntimeException("Trying to find the shortest path using inappropriate algorithm");

        int[] bestWeights = new int[MAX_VERTICES];
        int[] edgeTo = new int[MAX_VERTICES];
        Arrays.fill(bestWeights, Integer.MAX_VALUE);
        Arrays.fill(edgeTo, -1);
        edgeTo[from] = from;
        bestWeights[from] = 0;

        java.util.Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(i -> bestWeights[i]));
        queue.add(from);

        while (!queue.isEmpty()) {
            int current = queue.remove();
            int next;
            while ((next = getUnvisitedVertex(current)) != -1) {
                if (bestWeights[current] + adjMatrix[current][next] < bestWeights[next]) {
                    bestWeights[next] = bestWeights[current] + adjMatrix[current][next];
                    edgeTo[next] = current;
                }
                queue.add(next);
                vertices[next].wasVisited = true;
            }
            resetFlags();
        }
        printShortestPath(edgeTo, to);
    }

}
