// Undirected and Directed Graphs using an adjacency lists; used for Networkify.
//
// Written by Lewis Kim.

import java.util.HashTable;


// Class of a weighted undirected graph using adjacency list representation through HashTables.
static class UndirectedGraph {

	/** Adjacency list using a hashmap of hashmaps to represent a vertex and all of its edges/edge weights.

		Example:
		{"v1": {"v2":2, "v3" 2}, "v2": {"v1": }, ...} **/
	HashMap<String, HashMap<String, Double>> adjacencyList;


	public UndirectedGraph(T root) {
		this.adjacencyList = new HashMap<>();
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
		if (this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph already has that vertex.");
		}

		for (String vertex : this.adjacencyList.entrySet()) {
			vertex.remove(v)
		}

		this.adjacencyList.remove(v)
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


	/** Print the current graph. **/
	public void printGraph() {

	}



	// Various Algorithms.


}


static class DirectedGraph {



}




