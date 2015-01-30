import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class MST {

    /**
     * Using disjoint set(s), run Kruskal's algorithm on the given graph and
     * return the MST. Return null if no MST exists for the graph.
     *
     * @param g The graph to be processed. Will never be null.
     * @return The MST of the graph; null if no valid MST exists.
     */
    public static Collection<Edge> kruskals(Graph g) {
        ArrayList<Edge> mst = new ArrayList<Edge>();
        DisjointSets<Vertex> disjoint = new DisjointSets<Vertex>(g.getVertices());
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>(g.getEdgeList());
        while (!edges.isEmpty()
                && ( mst.size() < g.getVertices().size() - 1)) {
            Edge e = edges.poll();
            if (!disjoint.sameSet(e.getU(), e.getV())) {
                disjoint.merge(e.getV(), e.getU());
                mst.add(e);
            }
            if (g.getVertices().size() - 1 == mst.size()) {
                return mst;
            }
        }
        return null;
    }

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree. If no MST exists, return null.
     *
     * @param g The graph to be processed. Will never be null.
     * @param start The ID of the start node. Will always exist in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */

    public static Collection<Edge> prims(Graph g, int start) {
        ArrayList<Edge> mst = new ArrayList<Edge>();
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
        Vertex startVert = new Vertex(start);
        Map<Vertex, Integer> adj = g.getAdjacencies(startVert);
        HashSet<Vertex> visited = new HashSet<Vertex>();
        visited.add(startVert);
        for (Entry<Vertex, Integer> entry : adj.entrySet()) {
            edges.add(new Edge(startVert, entry.getKey(), entry.getValue()));
        }
        while (!edges.isEmpty()) {
            Edge e = edges.poll();

            Vertex v = e.getV();
            if (!visited.contains(v)) {
                mst.add(e);
            }
            visited.add(v);
            adj = g.getAdjacencies(v);
            for (Entry<Vertex, Integer> entry : adj.entrySet()) {
                Vertex k = entry.getKey();
                int val = entry.getValue();
                if (!visited.contains(k)) {
                    edges.add(new Edge(v, k, val));
                }
            }
        }
        if (g.getVertices().size() - 1 != mst.size()) {
            return null;
        }
        return mst;
    }
}
