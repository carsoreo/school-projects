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
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * This test was developed using older test cases from Workshop 11.
 * 
 * Tests MinimumSpanningTreeUtil
 * 
 * @author clorejol
 *
 */
public class MinimumSpanningTreeUtilTest {
	
	/** Undirected graph to test */
    private Graph<String, Highway> undirectedGraph;

	/**
	 * Sets up tests
	 * @throws java.lang.Exception Exception thrown
	 */
	@Before
	public void setUp() throws Exception {
		undirectedGraph = new AdjacencyMapGraph<String, Highway>();
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.graph.MinimumSpanningTreeUtil#kruskal(edu.ncsu.csc316.dsa.graph.Graph)}.
	 */
	@Test
	public void testKruskal() {
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
        
		PositionalList<Edge<Highway>> m = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
		
		assertEquals(m.size(), 4);
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.graph.MinimumSpanningTreeUtil#primJarnik(edu.ncsu.csc316.dsa.graph.Graph)}.
	 */
	@Test
	public void testPrimJarnik() {
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
        
        PositionalList<Edge<Highway>> m2 = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
        
        assertEquals(m2.size(), 4);
        
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
