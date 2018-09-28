// Undirected weighted graph using an adjacency list; used for Networkify.
//
// Written by Lewis Kim.

import java.util.HashMap;
import java.util.HashMap.*;

// Class of a weighted undirected graph using adjacency list representation through HashTables.
public class UndirectedGraph {

	/** Adjacency list using a hashmap of hashmaps to represent a vertex and all of its edges/edge weights.

		Example:
		{"v1": {"v2":2, "v3" 2}, "v2": {"v1": }, ...} 

		Vertices are represented as strings (for their labels) instead of integers (e.g. 1 for vertex 1) for
		clarity and better UX. **/
	HashMap<String, HashMap<String, Double>> adjacencyList;

	String id;


	public UndirectedGraph(String root) {
		this.adjacencyList = new HashMap<>();
		this.adjacencyList.put(root, new HashMap<String, Double>());
		this.id = "Undirected Weighted Graph";
	}


	/** Add a vertex V to this graph. **/
	public void addVertex(String v) {
		if (this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph already has that vertex.");
		}

		this.adjacencyList.put(v, new HashMap<String, Double>());
	}


	/** Remove a vertex V and ALL of its associated edges. **/
	public void removeVertex(String v) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not have that vertex.");
		}

		// Remove V from all the edges in the adjacency list.
		for (String vertex : this.adjacencyList.keySet()) {
			this.adjacencyList.get(vertex).remove(v);
		}

		this.adjacencyList.remove(v);
	}


	/** Add an edge between vertices V and U, with weight WEIGHT. **/
	public void addEdge(String v, String u, Double weight) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + v + ".");
		} else if (!this.adjacencyList.containsKey(u)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + u + ".");
		}

		this.adjacencyList.get(v).put(u, weight);
		this.adjacencyList.get(u).put(v, weight);
	}


	/** Remove an edge between vertex V and U. **/
	public void removeEdge(String v, String u, Double weight) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + v + ".");
		} else if (!this.adjacencyList.containsKey(u)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + u + ".");
		}

		this.adjacencyList.get(v).remove(u);
		this.adjacencyList.get(u).remove(v);
	}


	/** Replace the edge weight between vertex V and U with new weight, WEIGHT. **/
	public void replaceWeight(String v, String u, Double weight) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + v + ".");
		} else if (!this.adjacencyList.containsKey(u)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + u + ".");
		}

		this.adjacencyList.get(v).replace(u, weight);
		this.adjacencyList.get(u).replace(v, weight);
	}


	/** Print the current graph in the following format:

	    VERTEX: {Set of Edges in the format CONNECTED_VERTEX: WEIGHT}. **/
	public void printGraph() {
		for (String vertex : this.adjacencyList.keySet()) {
			System.out.println(vertex + ": " + this.adjacencyList.get(vertex));
		}
	}


	// Various Algorithms.



	// Djikstra's.



	// BF.


}

