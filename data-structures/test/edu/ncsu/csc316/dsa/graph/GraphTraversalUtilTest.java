package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * This test was developed using older test cases from Workshop 11.
 * 
 * Tests for GraphTraversalUtil
 * @author clorejol
 *
 */
public class GraphTraversalUtilTest {
	
	/** Undirected graph to test */
    private Graph<String, Integer> undirectedGraph;
	
	/**
	 * Sets up tests
	 * @throws Exception exception thrown during setup
	 */
	@Before
	public void setUp() throws Exception {
		undirectedGraph = new AdjacencyMapGraph<String, Integer>();
        
	}
	
	/**
	 * Tests all methods in GraphTraversalUtil
	 */
	@Test
	public void test() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v4, v5, 50);
        
		Map<Vertex<String>, Edge<Integer>> m = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);

		assertEquals(m.get(v2), e1);
		assertEquals(m.get(v1), null);
		assertEquals(m.get(v3), e2);
		assertEquals(m.get(v4), e3);
		assertEquals(m.get(v5), e4);
		
		Map<Vertex<String>, Edge<Integer>> m2 = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
		
		assertEquals(m2.get(v2), e1);
		assertEquals(m2.get(v1), null);
		assertEquals(m2.get(v3), e2);
		assertEquals(m2.get(v4), e3);
		assertEquals(m2.get(v5), e4);

	}

}
