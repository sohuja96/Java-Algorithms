import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementations of various graph algorithms.
 *
 * @author Joshua Santillo
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * The graph passed in may be directed or undirected, but never both.
     *
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Null data not allowed!");
        }
        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Entriless not allowed!");
        }
        Queue<Vertex<T>> queue = new LinkedList<>();
        int graphSize = graph.getAdjacencyList().size();
        ArrayList<Vertex<T>> bfs = new ArrayList<>();
        Set<Vertex<T>> newSet = new HashSet<>();

        Vertex<T> current = start;
        newSet.add(current);
        queue.add(current);

        while (!queue.isEmpty()) {
            current = queue.remove();
            bfs.add(current);
            newSet.add(current);

            List<VertexDistancePair<T>> temp
                = graph.getAdjacencyList().get(current);
            for (VertexDistancePair i : temp) {
                if (!newSet.contains(i.getVertex())) {
                    queue.add(i.getVertex());
                    newSet.add(i.getVertex());
                }
            }
        }

        return bfs;
    }

    /**
     * Private bfs for vertex distance pairs
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    private static <T> List<VertexDistancePair<T>> bfs(Vertex<T> start,
            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Null data not allowed!");
        }
        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Entriless not allowed!");
        }
        Queue<VertexDistancePair<T>> queue = new LinkedList<>();
        int graphSize = graph.getAdjacencyList().size();
        ArrayList<VertexDistancePair<T>> bfs = new ArrayList<>();
        VertexDistancePair<T> current = new VertexDistancePair<T>(start, 0);
        bfs.add(current);

        while (!queue.isEmpty() && current != null) {
            for (VertexDistancePair<T> i
                : graph.getAdjacencyList().get(current)) {
                if (!bfs.contains(i)) {
                    queue.add(i);
                }
            }
            current = queue.poll();
            if (!bfs.contains(current)) {
                bfs.add(current);
            }
        }

        return bfs;
    }

    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     *
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     *
     * The graph passed in may be directed or undirected, but never both.
     *
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     *
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Null data not allowed!");
        }
        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Entriless not allowed!");
        }
        ArrayList<Vertex<T>> visited = new ArrayList<Vertex<T>>();
        return dfs(start, graph, visited);
    }

    /**
     * Private method for depth first search
     * @param current Vertex
     * @param graph as a map
     * @param visited vertexes
     * @param <T> generic
     * @return ArrayList of vertexes
     */
    private static <T> ArrayList<Vertex<T>> dfs(Vertex<T> current,
            Graph<T> graph, ArrayList<Vertex<T>> visited) {
        visited.add(current);
        for (VertexDistancePair<T> i : graph.getAdjacencyList().get(current)) {
            if (!visited.contains(i.getVertex())) {
                dfs(i.getVertex(), graph, visited);
            }
        }
        return visited;
    }

    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     *
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     *
     * There are guaranteed to be no negative edge weights in the graph.
     * The graph passed in may be directed or undirected, but never both.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @throws IllegalArgumentException if any input is null, or if
     *         {@code start} doesn't exist in the graph
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     *         in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
            Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Null data not allowed!");
        }
        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Entriless not allowed!");
        }
        int alt;
        Vertex<T> current = start;
        Map<Vertex<T>, Integer> dijk = new HashMap<>();
        VertexDistancePair<T> currentPair;
        PriorityQueue<VertexDistancePair<T>> adj = new PriorityQueue<>();

        for (Vertex<T> i : graph.getAdjacencyList().keySet()) {
            dijk.put(i, Integer.MAX_VALUE);
        }
        dijk.put(start, 0);
        adj.add(new VertexDistancePair<T>(start, dijk.get(start)));
        while (!adj.isEmpty()) {
            currentPair = adj.remove();
            if (dijk.get(currentPair.getVertex())
                >= currentPair.getDistance()) {
                for (VertexDistancePair<T> i
                    : graph.getAdjacencyList().get(currentPair.getVertex())) {
                    alt = dijk.get(currentPair.getVertex())
                        + (i.getDistance());
                    if (alt < dijk.get(i.getVertex())) {
                        dijk.put(i.getVertex(), alt);
                        adj.add(new VertexDistancePair<T>(i.getVertex(), alt));
                    }
                }
            }

        }
        return dijk;
    }

    /**
     * Returns the shortest path priority queue
     * @param start vertex
     * @param graph a map
     * @param shortest distance
     * @param <T> the generic
     * @return shortest distance priority queue
     */
    private static <T> PriorityQueue<VertexDistancePair<T>> shortPath(
        Vertex<T> start, Graph<T> graph,
            PriorityQueue<VertexDistancePair<T>> shortest) {
        shortest.addAll(bfs(start, graph));
        return shortest;
    }
    /**
     * Run Kruskal's algorithm on the given graph and return the minimum
     * spanning tree in the form of a set of Edges. If the graph is
     * disconnected, and therefore there is no valid MST, return null.
     *
     * You may assume that there will only be one valid MST that can be formed.
     * In addition, only an undirected graph will be passed in.
     *
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, {@code java.util.Map} and any class from java.util
     * that implements the aforementioned interfaces.
     *
     * @throws IllegalArgumentException if graph is null
     * @param graph the Graph we are searching
     * @param <T> the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Null data not allowed!");
        }
        HashSet<Edge<T>> krus = new HashSet<>();
        Edge<T> tempEdge;
        HashMap<Vertex<T>, DisjointSet> djMap = new HashMap<>();
        PriorityQueue<Edge<T>> edgeList = new PriorityQueue<>();

        for (Vertex<T> i : graph.getAdjacencyList().keySet()) {
            djMap.put(i, new DisjointSet());
        }
        for (Edge<T> i : graph.getEdgeList()) {
            edgeList.add(i);
        }
        while (krus.size() < graph.getAdjacencyList().size() - 1
            && !edgeList.isEmpty()) {
            tempEdge = edgeList.poll();
            if (djMap.get(tempEdge.getU()).find()
                != djMap.get(tempEdge.getV()).find()) {
                krus.add(tempEdge);
                djMap.get(tempEdge.getU()).union(djMap.get(tempEdge.getV()));
            }
        }
        if (krus.size() != graph.getAdjacencyList().keySet().size() - 1) {
            return null;
        }
        return krus;
    }
}