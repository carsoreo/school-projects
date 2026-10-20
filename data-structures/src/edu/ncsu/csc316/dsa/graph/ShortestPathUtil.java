package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 12 assignment page or just given explicitly to use in the
 * Workshop 12 assignment page.
 * 
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author clorejol
 *
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
    	AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> c = new LinearProbingHashMap<>();
        Set<Vertex<V>> s = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> e = new LinearProbingHashMap<>();
    	
    	for(Vertex<V> v : graph.vertices()) {
    		if (v.equals(start)) {
    			c.put(v, 0);
    		}
    		else {
    			c.put(v, Integer.MAX_VALUE);
    		}
    		int currentCost = c.get(v);
    		Entry<Integer, Vertex<V>> apqEntry = q.insert(currentCost, v);
    		e.put(v, apqEntry);
    	}
    	while (!q.isEmpty()) {
    		Entry<Integer, Vertex<V>> entry = q.deleteMin();
    		Vertex<V> u = entry.getValue();
    		s.add(u);
    		for(Edge<E> edge : graph.outgoingEdges(u)) {
    			Vertex<V> z = graph.opposite(u, edge);
    			if (!s.contains(z)) {
    				int r = edge.getElement().getWeight() + c.get(u);
    				if (r < c.get(z)) {
    					c.put(z, r);
    					q.replaceKey(e.get(z), r);
    				}
    			}
    		}
    	}
    	return c;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
    	Map<Vertex<V>, Edge<E>> m = new LinearProbingHashMap<>();
    	for (Vertex<V> v : costs) {
    		if (!v.equals(start)) {
    			for(Edge<E> e : graph.incomingEdges(v)) {
    				Vertex<V> u = graph.opposite(v, e);
    				if (costs.get(v) ==  costs.get(u) + e.getElement().getWeight()) {
    					m.put(v, e);
    				}
    			}
    		}
    	}
    	return m;
    }
}