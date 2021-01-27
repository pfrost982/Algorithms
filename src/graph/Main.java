package graph;

import java.util.HashSet;
import java.util.LinkedList;
//7.1 Компьютерная сеть, система улиц в городе, система электропроводки в квартире...
//7.2
class Vertex {
    private final Character label;
    private final LinkedList<Character> adjacent;

    public Vertex(char label, char[] adjacentVertices) {
        this.label = label;
        this.adjacent = new LinkedList<>();
        for (char adj : adjacentVertices) {
            this.adjacent.add(adj);
        }
    }

    public Character getLabel() {
        return label;
    }

    public LinkedList<Character> getAdjacent() {
        return adjacent;
    }

    public void printVertex() {
        System.out.println(this + " " + adjacent);
    }

    @Override
    public String toString() {
        return Character.toString(label);
    }
}

class Graph {
    LinkedList<Vertex> vertices;
    HashSet<Vertex> visited;

    public Graph() {
        this.vertices = new LinkedList<>();
        this.visited = new HashSet<>();
    }

    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }

    public void bfs_start() {
        if (!vertices.isEmpty()) {
            bfs(vertices.getFirst());
            clearVisited();
        }
    }

    private void bfs(Vertex current) {
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.addLast(current);
        visited.add(current);
        while (!queue.isEmpty()) {
            current = queue.pollFirst();
            current.printVertex();
            while (true) {
                Vertex next = getNextNotVisited(current);
                if (next == null) break;
                visited.add(next);
                queue.addLast(next);
            }
        }
    }

    public void dfs_start() {
        if (!vertices.isEmpty()) {
            dfs(vertices.getFirst());
            clearVisited();
        }
    }

    private void dfs(Vertex current) {
        visited.add(current);
        current.printVertex();
        Vertex next = getNextNotVisited(current);
        while (next != null) {
            dfs(next);
            next = getNextNotVisited(current);
        }
    }

    private Vertex getNextNotVisited(Vertex vertex) {
        for (Character adj : vertex.getAdjacent()) {
            Vertex adjVertex = findVertex(adj);
            if (!visited.contains(adjVertex)) return adjVertex;
        }
        return null;
    }

    private Vertex findVertex(Character character) {
        for (Vertex vertex : vertices) {
            if (vertex.getLabel() == character) return vertex;
        }
        return null;
    }

    private void clearVisited() {
        this.visited.clear();
    }

    public void printGraph() {
        for (Vertex vertex : vertices) {
            vertex.printVertex();
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(new Vertex('A', new char[]{'B', 'D', 'G'}));
        graph.addVertex(new Vertex('B', new char[]{'A', 'C'}));
        graph.addVertex(new Vertex('C', new char[]{'B', 'I'}));
        graph.addVertex(new Vertex('D', new char[]{'A', 'E'}));
        graph.addVertex(new Vertex('E', new char[]{'D', 'F', 'H'}));
        graph.addVertex(new Vertex('F', new char[]{'E'}));
        graph.addVertex(new Vertex('G', new char[]{'A', 'H'}));
        graph.addVertex(new Vertex('H', new char[]{'E', 'G', 'I'}));
        graph.addVertex(new Vertex('I', new char[]{'C', 'H'}));
//7.3
        System.out.println("Построен граф:");
        graph.printGraph();
        System.out.println("\nНачинаем обход в глубину...");
        long startTime = System.nanoTime();
        graph.dfs_start();
        System.out.println("Время обхода в глубину: " + (System.nanoTime() - startTime));
//7.4
        System.out.println("\nНачинаем обход в ширину...");
        startTime = System.nanoTime();
        graph.bfs_start();
        System.out.println("Время обхода в ширину: " + (System.nanoTime() - startTime));
    }
}
