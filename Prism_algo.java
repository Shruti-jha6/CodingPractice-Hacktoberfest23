import java.util.ArrayList;
import java.util.PriorityQueue;

class Graph {
    int V;
    ArrayList<ArrayList<Edge>> adjList;

    Graph(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new Edge(v, weight));
        adjList.get(v).add(new Edge(u, weight));
    }

    ArrayList<Edge> primMST() {
        boolean[] visited = new boolean[V];
        ArrayList<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);

        int startVertex = 0; // Choose the starting vertex
        visited[startVertex] = true;
        for (Edge edge : adjList.get(startVertex)) {
            minHeap.add(edge);
        }

        while (mst.size() < V - 1) {
            Edge edge = minHeap.poll();
            int u = edge.dest;
            if (!visited[u]) {
                visited[u] = true;
                mst.add(edge);
                for (Edge nextEdge : adjList.get(u)) {
                    if (!visited[nextEdge.dest]) {
                        minHeap.add(nextEdge);
                    }
                }
            }
        }

        return mst;
    }

    class Edge {
        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}

public class PrimAlgorithm {
    public static void main(String[] args) {
        int V = 5; // Number of vertices
        Graph graph = new Graph(V);

        // Adding edges with their weights
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);

        ArrayList<Graph.Edge> mst = graph.primMST();
        System.out.println("Minimum Spanning Tree (MST):");
        for (Graph.Edge edge : mst) {
            System.out.println(edge.dest + " - " + edge.weight);
        }
    }
}
