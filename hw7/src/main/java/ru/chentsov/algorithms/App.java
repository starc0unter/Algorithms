package ru.chentsov.algorithms;

public class App {

    public static void main(String[] args) {
        System.out.println("---Undirected unweighted graph---");
        Graph unweightedGraph = generateUnweightedGraph();
        System.out.println("Shortest path from b to c:");
        unweightedGraph.getShortestPathUW('b', 'c');
        System.out.println("Shortest path from d to g:");
        unweightedGraph.getShortestPathUW('d', 'g');
        System.out.println("Shortest path from h to i:");
        unweightedGraph.getShortestPathUW('h', 'i');
        System.out.println("Shortest path from e to f:");
        unweightedGraph.getShortestPathUW('e', 'f');

        System.out.println("\n---Directed weighted graph---");
        Graph weightedGraph = generateWeightedGraph();
        System.out.println("Shortest path from b to c:");
        weightedGraph.getShortestPathW('b', 'c');
        System.out.println("Shortest path from b to e:");
        weightedGraph.getShortestPathW('b', 'e');
        System.out.println("Shortest path from d to g:");
        weightedGraph.getShortestPathW('d', 'g');
        System.out.println("Shortest path from b to h:");
        weightedGraph.getShortestPathW('b', 'h');
        System.out.println("Shortest path from i to f:");
        weightedGraph.getShortestPathW('i', 'f');
    }

    private static Graph generateUnweightedGraph() {
        Graph graph = new Graph(10);
        graph.addVertex('a');
        graph.addVertex('b');
        graph.addVertex('c');
        graph.addVertex('d');
        graph.addVertex('e');
        graph.addVertex('f');
        graph.addVertex('g');
        graph.addVertex('h');
        graph.addVertex('i');
        graph.addVertex('j');
        graph.addUndirectedEdge('a', 'b', 1);
        graph.addUndirectedEdge('a', 'd', 1);
        graph.addUndirectedEdge('a', 'c', 1);
        graph.addUndirectedEdge('b', 'e', 1);
        graph.addUndirectedEdge('b', 'd', 1);
        graph.addUndirectedEdge('d', 'h', 1);
        graph.addUndirectedEdge('h', 'j', 1);
        graph.addUndirectedEdge('j', 'g', 1);
        graph.addUndirectedEdge('c', 'f', 1);
        graph.addUndirectedEdge('f', 'g', 1);
        graph.addUndirectedEdge('g', 'i', 1);

        return graph;
    }

    private static Graph generateWeightedGraph() {
        Graph graph = new Graph(10);
        graph.addVertex('a');
        graph.addVertex('b');
        graph.addVertex('c');
        graph.addVertex('d');
        graph.addVertex('e');
        graph.addVertex('f');
        graph.addVertex('g');
        graph.addVertex('h');
        graph.addVertex('i');
        graph.addVertex('j');
        graph.addDirectedEdge('b', 'a', 6);
        graph.addDirectedEdge('d', 'a', 3);
        graph.addDirectedEdge('a', 'c', 1);
        graph.addDirectedEdge('b', 'e', 25);
        graph.addDirectedEdge('b', 'd', 2);
        graph.addDirectedEdge('d', 'h', 15);
        graph.addDirectedEdge('d', 'c', 5);
        graph.addDirectedEdge('h', 'j', 2);
        graph.addDirectedEdge('h', 'e', 4);
        graph.addDirectedEdge('j', 'g', 4);
        graph.addDirectedEdge('j', 'e', 1);
        graph.addDirectedEdge('c', 'f', 3);
        graph.addDirectedEdge('f', 'g', 1);
        graph.addDirectedEdge('f', 'h', 6);
        graph.addDirectedEdge('g', 'i', 5);

        return graph;
    }

}
