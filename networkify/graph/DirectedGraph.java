// Directed weighted graph using an adjacency list; used for Networkify.
//
// Written by Lewis Kim.

import java.util.LinkedHashMap;
import java.util.LinkedHashMap.*;
import java.util.HashSet;


// Class of a weighted undirected graph using adjacency list representation through HashMaps.
public class DirectedGraph {

	/** Adjacency list using a hashmap of hashmaps to represent a vertex and all of its edges/edge weights.

		Example:
		{"v1": {"v2":2, "v3" 2}, "v2": {"v1": }, ...} 

		Vertices are represented as strings (for their labels) instead of integers (e.g. 1 for vertex 1) for
		clarity and better UX. **/
	LinkedHashMap<String, LinkedHashMap<String, Double>> adjacencyList;

	String id;


	public DirectedGraph(String root) {
		this.adjacencyList = new LinkedHashMap<>();
		this.adjacencyList.put(root, new LinkedHashMap<String, Double>());
		this.id = "Directed Weighted Graph";
	}


	/** Add a vertex V to this graph. **/
	public void addVertex(String v) {
		if (this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph already has that vertex.");
		}

		this.adjacencyList.put(v, new LinkedHashMap<String, Double>());
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
	}


	/** Remove an edge between vertex V and U. **/
	public void removeEdge(String v, String u, Double weight) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + v + ".");
		} else if (!this.adjacencyList.containsKey(u)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + u + ".");
		}

		this.adjacencyList.get(v).remove(u);
	}


	/** Replace the edge weight between vertex V and U with new weight, WEIGHT. **/
	public void replaceWeight(String v, String u, Double weight) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + v + ".");
		} else if (!this.adjacencyList.containsKey(u)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + u + ".");
		}

		this.adjacencyList.get(v).replace(u, weight);
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



	// Cycle detection.

	/** Given a vertex S, return True if there is a cycle from and to S. Otherwise,
	    return False.

	    PARAMS: V: Vertex currently being looked at. Initially unvisited.
	            S: "Source" vertex that we're checking to see if it has a cycle. Initially unvisited.
	            VISITED: Set of visited vertices. Initially empty.**/
	public boolean vertexHasCycle(String v, String s, HashSet<String> visited) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not have the vertex " + v + ".");
		}
		if (this.adjacencyList.get(v).keySet().size() == 0) {
			return false;
		}
		else if (visited.contains(v)) {
			return false;
		}
		else if (this.adjacencyList.get(v).keySet().contains(s)) {
			return true;
		}
		else {
			visited.add(v);

			for (String u : this.adjacencyList.get(v).keySet()) {
				if (vertexHasCycle(u, s, visited)) {
					return true;
				}
			}
		}

		return false;

	}


	/** Given a vertex V, return an array of vertices that form a cycle from and to V.
	    If there is no such cycle, return a single-element array of V. **/
	// public String[] getCycle(String v) {
	// 	if (!this.adjacencyList.containsKey(v)) {
	// 		throw new IllegalArgumentException("This graph does not have the vertex " + v + ".");
	// 	}

	// 	List<String> dfsTree = ArrayList<String>();







	// 	if 

	// }


}

