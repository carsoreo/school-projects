/**
 * 
 */
package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * This test was developed using older test cases from Workshop 11.
 * 
 * Test for ShortestPathUtil
 * 
 * @author clorejol
 *
 */
public class ShortestPathUtilTest {
	
	/** Undirected graph to test */
    private Graph<String, Highway> undirectedGraph;
	
	/**
	 * Sets up tests
	 * @throws java.lang.Exception exception thrown
	 */
	@Before
	public void setUp() throws Exception {
		undirectedGraph = new AdjacencyMapGraph<String, Highway>();
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.graph.ShortestPathUtil#dijkstra(edu.ncsu.csc316.dsa.graph.Graph, edu.ncsu.csc316.dsa.graph.Graph.Vertex)}.
	 */
	@Test
	public void testDijkstra() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Highway h1 = new Highway("RaltoAsh", 5);
        undirectedGraph.insertEdge(v1, v2, h1);
        
        Highway h2 = new Highway("AshtoWil", 10);
        undirectedGraph.insertEdge(v2, v3, h2);
        
        Highway h3 = new Highway("WiltoDur", 15);
        undirectedGraph.insertEdge(v3, v4, h3);
        
        Highway h4 = new Highway("DurtoGreen", 20);
        undirectedGraph.insertEdge(v4, v5, h4);
        
        
        Map<Vertex<String>, Integer> m = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        assertEquals((int) m.get(v1), 0);
        assertEquals((int)m.get(v2), 5);
        assertEquals((int)m.get(v3), 15);
        assertEquals((int)m.get(v4), 30);
        assertEquals((int)m.get(v5), 50);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.graph.ShortestPathUtil#shortestPathTree(edu.ncsu.csc316.dsa.graph.Graph, edu.ncsu.csc316.dsa.graph.Graph.Vertex, edu.ncsu.csc316.dsa.map.Map)}.
	 */
	@Test
	public void testShortestPathTree() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Highway h1 = new Highway("RaltoAsh", 5);
        Edge<Highway> e1 = undirectedGraph.insertEdge(v1, v2, h1);
        
        Highway h2 = new Highway("AshtoWil", 10);
        Edge<Highway> e2 = undirectedGraph.insertEdge(v2, v3, h2);
        
        Highway h3 = new Highway("WiltoDur", 15);
        Edge<Highway> e3 = undirectedGraph.insertEdge(v3, v4, h3);
        
        Highway h4 = new Highway("DurtoGreen", 20);
        Edge<Highway> e4 = undirectedGraph.insertEdge(v4, v5, h4);
        
        Map<Vertex<String>, Integer> m = ShortestPathUtil.dijkstra(undirectedGraph, v1);
        Map<Vertex<String>, Edge<Highway>> m2 = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, m);
        
        assertEquals(m2.get(v1), null);
        assertEquals(m2.get(v2), e1);
        assertEquals(m2.get(v3), e2);
        assertEquals(m2.get(v4), e3);
        assertEquals(m2.get(v5), e4);
        
	}
	
	/**
	 * Weighted object to use during testing
	 * @author clorejol
	 *
	 */
	public class Highway implements Weighted {
		
		/** Name for Highway */
        private String name;
        
        /** Length of Highway */
        private int length;
        
        /**
         * Constructor for highway
         * @param n name to set
         * @param l length to set
         */
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }
        
        /**
         * Sets the name
         * @param name of highway
         */
        public void setName(String name) {
            this.name = name;
        }
        
        /**
         * Gets length
         * @return length
         */
        public int getLength() {
            return length;
        }
        
        /**
         * Sets the length
         * @param length to set
         */
        public void setLength(int length) {
            this.length = length;
        }
        
        /**
         * Gets the name
         * @return String name to return
         */
        public String getName() {
            return name;
        }
        
        
        @Override
        public int getWeight() {
            return getLength();
        }
    }
}
