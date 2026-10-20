package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * This class has methods which have been coded using descriptions given in 
 * the Workshop 11 assignment page or just given explicitly to use in the
 * Workshop 11 assignment page.
 * 
 * Test class for AdjacencyMatrixGraph
 * Checks the expected outputs of the Graph abstract data type behaviors when using
 * an edge list graph data structure
 *
 * @author Dr. King
 *
 */
public class AdjacencyMatrixGraphTest {
	
	/** Undirected graph to test */
    private Graph<String, Integer> undirectedGraph;
    
    /** Directed graph to test */
    private Graph<String, Integer> directedGraph;
    
    /**
     * Create a new instance of an AdjacencyMatrixGraph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyMatrixGraph<String, Integer>();
        directedGraph = new AdjacencyMatrixGraph<String, Integer>(true);
    }
    
    private void buildUndirectedSample() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
    }
    
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }

    /**
     * Test the output of the numVertices() behavior
     */     
    @Test
    public void testNumVertices() {
        buildUndirectedSample();
        assertEquals(5, undirectedGraph.numVertices());
        
        buildDirectedSample();       
        assertEquals(6, directedGraph.numVertices());
    }

    /**
     * Test the output of the vertices() behavior
     */ 
    @Test
    public void testVertices() {
        // We cannot call buildUndirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        
        assertEquals(it.next().getElement(), "Raleigh");
        assertEquals(it.next().getElement(), "Asheville");
        assertEquals(it.next().getElement(), "Wilmington");
        assertEquals(it.next().getElement(), "Durham");
        assertEquals(it.next().getElement(), "Greenville");

        try {
        	it.next();
        } catch (Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
        
        
        
        // DIRECTED
        // We cannot call buildDirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing     
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Vertex<String>> it2 = directedGraph.vertices().iterator();
        
        assertEquals(it2.next().getElement(), "Raleigh");
        assertEquals(it2.next().getElement(), "Asheville");
        assertEquals(it2.next().getElement(), "Wilmington");
        assertEquals(it2.next().getElement(), "Durham");
        assertEquals(it2.next().getElement(), "Greenville");

        try {
        	it.next();
        } catch (Exception e) {
        	assertEquals(e.getClass(), NoSuchElementException.class);
        }
    }

    /**
     * Test the output of the numEdges() behavior
     */ 
    @Test
    public void testNumEdges() {
        assertEquals(0, directedGraph.numEdges());
        assertEquals(0, undirectedGraph.numEdges());
        
        buildDirectedSample();
        buildUndirectedSample();
        
        assertEquals(11, directedGraph.numEdges());
        assertEquals(10, undirectedGraph.numEdges());
    }

    /**
     * Test the output of the edges() behavior
     */ 
    @Test
    public void testEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        
        assertEquals(it.next(), e1);
        assertEquals(it.next(), e2);
        assertEquals(it.next(), e3);
        assertEquals(it.next(), e4);
        assertEquals(it.next(), e5);
        assertEquals(it.next(), e6);
        assertEquals(it.next(), e7);
        assertEquals(it.next(), e8);
        assertEquals(it.next(), e9);
        assertEquals(it.next(), e10);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        try {
        	directedGraph.opposite(v6, e1);
        } catch (Exception e) {
        	assertEquals(e.getMessage(), "Vertex is not incident on this edge.");
        }
        
        Iterator<Edge<Integer>> it2 = directedGraph.edges().iterator();
        
        assertEquals(it2.next(), e1);
        assertEquals(it2.next(), e2);
        assertEquals(it2.next(), e3);
        assertEquals(it2.next(), e4);
        assertEquals(it2.next(), e5);
        assertEquals(it2.next(), e6);
        assertEquals(it2.next(), e7);
        assertEquals(it2.next(), e8);
        assertEquals(it2.next(), e9);
        assertEquals(it2.next(), e10);
        assertEquals(it2.next(), e11);
    }

    /**
     * Test the output of the getEdge(v1,v2) behavior
     */ 
    @Test
    public void testGetEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(undirectedGraph.getEdge(v1, v2), e1);
        assertEquals(undirectedGraph.getEdge(v1, v3), e2);
        assertEquals(undirectedGraph.getEdge(v1, v4), e3);
        assertEquals(undirectedGraph.getEdge(v1, v5), e4);
        assertEquals(undirectedGraph.getEdge(v2, v3), e5);
        assertEquals(undirectedGraph.getEdge(v2, v4), e6);
        assertEquals(undirectedGraph.getEdge(v2, v5), e7);
        assertEquals(undirectedGraph.getEdge(v3, v4), e8);
        assertEquals(undirectedGraph.getEdge(v3, v5), e9);
        assertEquals(undirectedGraph.getEdge(v4, v5), e10);
        
        assertEquals((int)undirectedGraph.getEdge(v4, v5).getElement(), 50);
        assertEquals(undirectedGraph.getEdge(v4, v5).toString(), "Edge[element=50]");
        assertEquals(undirectedGraph.opposite(v4, e10), v5);
        assertEquals(undirectedGraph.opposite(v5, e10), v4);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(directedGraph.getEdge(v1, v2), e1);
        assertEquals(directedGraph.getEdge(v1, v3), e2);
        assertEquals(directedGraph.getEdge(v1, v4), e3);
        assertEquals(directedGraph.getEdge(v1, v5), e4);
        assertEquals(directedGraph.getEdge(v2, v3), e5);
        assertEquals(directedGraph.getEdge(v2, v4), e6);
        assertEquals(directedGraph.getEdge(v2, v5), e7);
        assertEquals(directedGraph.getEdge(v3, v4), e8);
        assertEquals(directedGraph.getEdge(v3, v5), e9);
        assertEquals(directedGraph.getEdge(v4, v5), e10);
        assertEquals(directedGraph.getEdge(v5, v6), e11);
        
        assertEquals(undirectedGraph.opposite(v4, e10), v5);
        assertEquals(undirectedGraph.opposite(v5, e10), v4);
    }

    /**
     * Test the output of the endVertices(e) behavior
     */ 
    @Test
    public void testEndVertices() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(undirectedGraph.endVertices(e1)[0], v1);
        assertEquals(undirectedGraph.endVertices(e1)[1], v2);
        
        assertEquals(undirectedGraph.endVertices(e2)[0], v1);
        assertEquals(undirectedGraph.endVertices(e2)[1], v3);
        
        assertEquals(undirectedGraph.endVertices(e3)[0], v1);
        assertEquals(undirectedGraph.endVertices(e3)[1], v4);
        
        assertEquals(undirectedGraph.endVertices(e4)[0], v1);
        assertEquals(undirectedGraph.endVertices(e4)[1], v5);
        
        assertEquals(undirectedGraph.endVertices(e5)[0], v2);
        assertEquals(undirectedGraph.endVertices(e5)[1], v3);
        
        assertEquals(undirectedGraph.endVertices(e6)[0], v2);
        assertEquals(undirectedGraph.endVertices(e6)[1], v4);
        
        assertEquals(undirectedGraph.endVertices(e7)[0], v2);
        assertEquals(undirectedGraph.endVertices(e7)[1], v5);
        
        assertEquals(undirectedGraph.endVertices(e8)[0], v3);
        assertEquals(undirectedGraph.endVertices(e8)[1], v4);
        
        assertEquals(undirectedGraph.endVertices(e9)[0], v3);
        assertEquals(undirectedGraph.endVertices(e9)[1], v5);
        
        assertEquals(undirectedGraph.endVertices(e10)[0], v4);
        assertEquals(undirectedGraph.endVertices(e10)[1], v5);
        
        assertEquals(undirectedGraph.endVertices(e1)[0], v1);
        assertEquals(undirectedGraph.endVertices(e1)[1], v2);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(directedGraph.endVertices(e1)[0], v1);
        assertEquals(directedGraph.endVertices(e1)[1], v2);
        
        assertEquals(directedGraph.endVertices(e2)[0], v1);
        assertEquals(directedGraph.endVertices(e2)[1], v3);
        
        assertEquals(directedGraph.endVertices(e3)[0], v1);
        assertEquals(directedGraph.endVertices(e3)[1], v4);
        
        assertEquals(directedGraph.endVertices(e4)[0], v1);
        assertEquals(directedGraph.endVertices(e4)[1], v5);
        
        assertEquals(directedGraph.endVertices(e5)[0], v2);
        assertEquals(directedGraph.endVertices(e5)[1], v3);
        
        assertEquals(directedGraph.endVertices(e6)[0], v2);
        assertEquals(directedGraph.endVertices(e6)[1], v4);
        
        assertEquals(directedGraph.endVertices(e7)[0], v2);
        assertEquals(directedGraph.endVertices(e7)[1], v5);
        
        assertEquals(directedGraph.endVertices(e8)[0], v3);
        assertEquals(directedGraph.endVertices(e8)[1], v4);
        
        assertEquals(directedGraph.endVertices(e9)[0], v3);
        assertEquals(directedGraph.endVertices(e9)[1], v5);
        
        assertEquals(directedGraph.endVertices(e10)[0], v4);
        assertEquals(directedGraph.endVertices(e10)[1], v5);
        
        assertEquals(directedGraph.endVertices(e1)[0], v1);
        assertEquals(directedGraph.endVertices(e1)[1], v2);
        
        assertEquals(directedGraph.endVertices(e11)[0], v5);
        assertEquals(directedGraph.endVertices(e11)[1], v6);
    }

    /**
     * Test the output of the opposite(v, e) behavior
     */ 
    @Test
    public void testOpposite() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(undirectedGraph.opposite(v1, e1), v2);
        assertEquals(undirectedGraph.opposite(v1, e2), v3);
        assertEquals(undirectedGraph.opposite(v1, e3), v4);
        assertEquals(undirectedGraph.opposite(v1, e4), v5);
        assertEquals(undirectedGraph.opposite(v2, e5), v3);
        assertEquals(undirectedGraph.opposite(v2, e6), v4);
        assertEquals(undirectedGraph.opposite(v2, e7), v5);
        assertEquals(undirectedGraph.opposite(v3, e8), v4);
        assertEquals(undirectedGraph.opposite(v3, e9), v5);
        assertEquals(undirectedGraph.opposite(v4, e10), v5);
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(directedGraph.opposite(v1, e1), v2);
        assertEquals(directedGraph.opposite(v1, e2), v3);
        assertEquals(directedGraph.opposite(v1, e3), v4);
        assertEquals(directedGraph.opposite(v1, e4), v5);
        assertEquals(directedGraph.opposite(v2, e5), v3);
        assertEquals(directedGraph.opposite(v2, e6), v4);
        assertEquals(directedGraph.opposite(v2, e7), v5);
        assertEquals(directedGraph.opposite(v3, e8), v4);
        assertEquals(directedGraph.opposite(v3, e9), v5);
        assertEquals(directedGraph.opposite(v4, e10), v5);
        assertEquals(directedGraph.opposite(v5, e11), v6);
    }

    /**
     * Test the output of the outDegree(v) behavior
     */ 
    @Test
    public void testOutDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.outDegree(v1));
        assertEquals(4, undirectedGraph.outDegree(v2));
        assertEquals(4, undirectedGraph.outDegree(v3));
        assertEquals(4, undirectedGraph.outDegree(v4));
        assertEquals(4, undirectedGraph.outDegree(v5));
        assertEquals(0, undirectedGraph.outDegree(v6));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(4, directedGraph.outDegree(v1));
        assertEquals(3, directedGraph.outDegree(v2));
        assertEquals(2, directedGraph.outDegree(v3));
        assertEquals(1, directedGraph.outDegree(v4));
        assertEquals(1, directedGraph.outDegree(v5));
        assertEquals(0, directedGraph.outDegree(v6));
    }

    /**
     * Test the output of the inDegree(v) behavior
     */ 
    @Test
    public void testInDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.inDegree(v1));
        assertEquals(4, undirectedGraph.inDegree(v2));
        assertEquals(4, undirectedGraph.inDegree(v3));
        assertEquals(4, undirectedGraph.inDegree(v4));
        assertEquals(4, undirectedGraph.inDegree(v5));
        assertEquals(0, undirectedGraph.inDegree(v6));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(0, directedGraph.inDegree(v1));
        assertEquals(1, directedGraph.inDegree(v2));
        assertEquals(2, directedGraph.inDegree(v3));
        assertEquals(3, directedGraph.inDegree(v4));
        assertEquals(4, directedGraph.inDegree(v5));
        assertEquals(1, directedGraph.inDegree(v6));
    }

    /**
     * Test the output of the outgoingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testOutgoingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        assertFalse(arrayContains(temp, e5));
        assertFalse(arrayContains(temp, e6));
        assertFalse(arrayContains(temp, e7));
        assertFalse(arrayContains(temp, e8));
        assertFalse(arrayContains(temp, e9));
        assertFalse(arrayContains(temp, e10));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        
        Edge<Integer>[] temp2 = (Edge<Integer>[])(new Edge[4]);
        int count2 = 0;
        Iterator<Edge<Integer>> it2 = directedGraph.outgoingEdges(v1).iterator();
        assertTrue(it2.hasNext());
        temp[count2] = it2.next();
        count2++;
        temp[count2] = it2.next();
        count2++;
        temp[count2] = it2.next();
        count2++;
        temp[count2] = it2.next();
        count2++;
        assertFalse(it2.hasNext());
        
        //Think these need to be true, could be an error
        assertFalse(arrayContains(temp2, e1));
        assertFalse(arrayContains(temp2, e2));
        assertFalse(arrayContains(temp2, e3));
        assertFalse(arrayContains(temp2, e4));
        
        assertFalse(arrayContains(temp2, e5));
        assertFalse(arrayContains(temp2, e6));
        assertFalse(arrayContains(temp2, e7));
        assertFalse(arrayContains(temp2, e8));
        assertFalse(arrayContains(temp2, e9));
        assertFalse(arrayContains(temp2, e10));
        assertFalse(arrayContains(temp2, e11));
    }
    
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
    private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
        for(Edge<Integer> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test the output of the incomingEdges(v) behavior
     */ 
    @Test
    public void testIncomingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Edge<Integer>> it1 = undirectedGraph.incomingEdges(v1).iterator();
        assertEquals(it1.next(), e1);
        assertEquals(it1.next(), e2);
        assertEquals(it1.next(), e3);
        assertEquals(it1.next(), e4);
        try {
        	it1.next();
        	fail();
        } catch(Exception e) {
        	//Pass
        }
        
        Iterator<Edge<Integer>> it2 = undirectedGraph.incomingEdges(v2).iterator();
        assertEquals(it2.next(), e1);
        assertEquals(it2.next(), e5);
        assertEquals(it2.next(), e6);
        assertEquals(it2.next(), e7);
        
        Iterator<Edge<Integer>> it3 = undirectedGraph.incomingEdges(v3).iterator();
        assertEquals(it3.next(), e2);
        assertEquals(it3.next(), e5);
        assertEquals(it3.next(), e8);
        assertEquals(it3.next(), e9);
        
        Iterator<Edge<Integer>> it4 = undirectedGraph.incomingEdges(v4).iterator();
        assertEquals(it4.next(), e3);
        assertEquals(it4.next(), e6);
        assertEquals(it4.next(), e8);
        assertEquals(it4.next(), e10);

        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Edge<Integer>> it5 = directedGraph.incomingEdges(v2).iterator();
        assertEquals(it5.next(), e1);
        
        Iterator<Edge<Integer>> it6 = directedGraph.incomingEdges(v3).iterator();
        assertEquals(it6.next(), e2);
        assertEquals(it6.next(), e5);
        
        Iterator<Edge<Integer>> it7 = directedGraph.incomingEdges(v6).iterator();
        assertEquals(it7.next(), e11);

    }

    /**
     * Test the output of the insertVertex(x) behavior
     */ 
    @Test
    public void testInsertVertex() {
        assertEquals(0, undirectedGraph.numVertices());
        Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
        assertEquals(1, undirectedGraph.numVertices());
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());      
        
        Vertex<String> v2 = undirectedGraph.insertVertex("Charaq");
        assertEquals(2, undirectedGraph.numVertices());
        assertTrue(it.hasNext());
        assertEquals(v2, it.next());
        assertFalse(it.hasNext());      
        
        Vertex<String> v3 = undirectedGraph.insertVertex("Fayettnam");
        assertEquals(3, undirectedGraph.numVertices());
        assertTrue(it.hasNext());
        assertEquals(v3, it.next());
        assertFalse(it.hasNext());      
        
        Vertex<String> v4 = undirectedGraph.insertVertex("Drillmington");
        assertEquals(4, undirectedGraph.numVertices());
        assertTrue(it.hasNext());
        assertEquals(v4, it.next());
        assertFalse(it.hasNext());      
    }

    /**
     * Test the output of the insertEdge(v1, v2, x) behavior
     */ 
    @Test
    public void testInsertEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        
        assertEquals(0, undirectedGraph.numEdges());
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
        assertEquals(1, undirectedGraph.numEdges());
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertFalse(it.hasNext());
        
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v1, 1);
        
        assertEquals(undirectedGraph.getEdge(v1, v1), e2);
        
        assertEquals(2, undirectedGraph.numEdges());
    }

    /**
     * Test the output of the removeVertex(v) behavior
     */ 
    @Test
    public void testRemoveVertex() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(5, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v5);
        assertEquals(4, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        
        undirectedGraph.removeVertex(v2);
        assertEquals(3, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        directedGraph.removeVertex(v6);
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());

        directedGraph.removeVertex(v3);
        assertEquals(4, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
    }

    /**
     * Test the output of the removeEdge(e) behavior
     */ 
    @Test
    public void testRemoveEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e1);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(9, undirectedGraph.numEdges());
        
        assertNull(undirectedGraph.getEdge(v1, v2));
        
        
        
        
        undirectedGraph.removeEdge(e2);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(8, undirectedGraph.numEdges());
        
        undirectedGraph.removeEdge(e3);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(7, undirectedGraph.numEdges());
        
        undirectedGraph.removeEdge(e4);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        
        undirectedGraph.removeEdge(e5);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(5, undirectedGraph.numEdges());
        
        undirectedGraph.removeEdge(e6);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(4, undirectedGraph.numEdges());
        
        undirectedGraph.removeEdge(e7);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        
        undirectedGraph.removeEdge(e8);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(2, undirectedGraph.numEdges());
        
        undirectedGraph.removeEdge(e9);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(1, undirectedGraph.numEdges());
        
        undirectedGraph.removeEdge(e10);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        
        assertNull(undirectedGraph.getEdge(v1, v6));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeEdge(e1);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(9, directedGraph.numEdges());
        
        directedGraph.removeEdge(e2);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(8, directedGraph.numEdges());
        
        directedGraph.removeEdge(e3);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(7, directedGraph.numEdges());
        
        directedGraph.removeEdge(e4);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        
        directedGraph.removeEdge(e5);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(5, directedGraph.numEdges());
        
        directedGraph.removeEdge(e6);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(4, directedGraph.numEdges());
        
        directedGraph.removeEdge(e7);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(3, directedGraph.numEdges());
        
        directedGraph.removeEdge(e8);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(2, directedGraph.numEdges());
        
        directedGraph.removeEdge(e9);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(1, directedGraph.numEdges());
        
        directedGraph.removeEdge(e10);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(0, directedGraph.numEdges());
    }
}