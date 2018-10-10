// Directed weighted graph using an adjacency list; used for Networkify.
//
// Written by Lewis Kim.

import java.util.*;

// import java.util.LinkedHashMap;
// import java.util.LinkedHashMap.*;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Set;
// import java.util.ArrayList;
// import java.util.ArrayList.*;
// import java.util.PriorityQueue;
// import java.util.PriorityQueue.*;

// import java.lang.Double;
// import java.lang.Double.*;


// Class of a weighted undirected graph using adjacency list representation through HashMaps.
public class DirectedGraph {

	/** Implementation of a Map Entry used in Dijkstra's Algorithm. **/
	final class MyEntry<K, V> implements Map.Entry<K, V> {
    	private final K key;
    	private V value;

    	public MyEntry(K key, V value) {
        	this.key = key;
        	this.value = value;
    	}

    	@Override
    	public K getKey() {
        	return key;
    	}

    	@Override
    	public V getValue() {
        	return value;
    	}

    	@Override
    	public V setValue(V value) {
        	V old = this.value;
        	this.value = value;
        	return old;
    	}
	}


	/** Adjacency list using a hashmap of hashmaps to represent a vertex and all of its edges/edge weights.

		Example:
		{"v1": {"v2":2, "v3" 2}, "v2": {"v1": }, ...} 

		Vertices are represented as strings (for their labels) instead of integers (e.g. 1 for vertex 1) for
		clarity and better UX. **/
	LinkedHashMap<String, LinkedHashMap<String, Double>> adjacencyList;
	LinkedHashMap<String, Integer> inwardEdges;
	String id;
	Boolean isSimple;





	public DirectedGraph() {
		this.adjacencyList = new LinkedHashMap<>();
		this.inwardEdges = new LinkedHashMap<>();

		this.id = "Directed Weighted Graph";
		this.isSimple = true;
	}


	/** Add a vertex V to this graph. **/
	public void addVertex(String v) {
		if (this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph already has that vertex.");
		}

		this.adjacencyList.put(v, new LinkedHashMap<String, Double>());
		this.inwardEdges.put(v, 0);
	}


	/** Remove a vertex V and ALL of its associated edges. **/
	public void removeVertex(String v) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not have that vertex.");
		}

		// Remove V from all the edges in the adjacency list.
		for (String vertex : this.adjacencyList.keySet()) {
			this.adjacencyList.get(vertex).remove(v);
			int count = this.inwardEdges.get(vertex);
			this.inwardEdges.put(vertex, count - 1);
		}

		this.adjacencyList.remove(v);
		this.inwardEdges.remove(v);
	}


	/** Add an edge between vertices V and U, with weight WEIGHT. **/
	public void addEdge(String v, String u, Double weight) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + v + ".");
		} else if (!this.adjacencyList.containsKey(u)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + u + ".");
		}

		if (v == u) {
			this.isSimple = false;
		}

		int count = this.inwardEdges.get(u);

		this.adjacencyList.get(v).put(u, weight);
		this.inwardEdges.put(u, count + 1);
	}


	/** Remove an edge between vertex V and U. **/
	public void removeEdge(String v, String u, Double weight) {
		if (!this.adjacencyList.containsKey(v)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + v + ".");
		} else if (!this.adjacencyList.containsKey(u)) {
			throw new IllegalArgumentException("This graph does not contain the vertex " + u + ".");
		}

		if (v == u) {
			this.isSimple = true;
		}

		int count = this.inwardEdges.get(u);

		this.adjacencyList.get(v).remove(u);
		this.inwardEdges.put(u, count - 1);
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

	    VERTEX: {Set of edges in the format CONNECTED_VERTEX: WEIGHT}. **/
	public void printGraph() {
		for (String vertex : this.adjacencyList.keySet()) {
			System.out.println(vertex + ": " + this.adjacencyList.get(vertex));
		}
	}

	/** Print the inward edge numbers for each vertex in this directed graph. **/
	public void printInwardEdges() {
		System.out.println(this.inwardEdges);
	}


	/** Return true if this directed graph is a simple graph. Otherwise, return false. **/
	public boolean isSimple() {
		return this.isSimple;
	}


	/* Various Algorithms. */


	// Shortest paths.
	

	/** Run Dijkstra's Algorithm on this weighted directed graph from the root node S. 

	    Note: Dijkstra's may not work if there are any negative cycles.
	    Using a priority queue implementation will speed up this algorithm. **/
	public LinkedHashMap dijkstra(String s) {
		if (!this.adjacencyList.keySet().contains(s)) {
			throw new IllegalArgumentException(s + " is not a vertex of this graph.");
		}

		LinkedHashMap<String, Double> dist = new LinkedHashMap<>();
		LinkedHashMap<String, String> prev = new LinkedHashMap<>();
		PriorityQueue<MyEntry<String, Double>> queue = new PriorityQueue<>(Comparator.comparing(entry -> entry.getValue()));

		dist.put(s, (double) 0);

		for (String v : this.adjacencyList.keySet()) {
			if (v != s) {
				dist.put(v, Double.POSITIVE_INFINITY);
			}
			prev.put(v, null);
			queue.add(new MyEntry<>(v, dist.get(v)));
		}

		while (!queue.isEmpty()) {
			String u = queue.poll().getKey();
			Set<String> neighbors = this.adjacencyList.get(u).keySet();

			for (String v : neighbors) {
				double alt = dist.get(u) + this.adjacencyList.get(u).get(v);

				if (alt < dist.get(v)) {
					dist.put(v, alt);
					prev.put(v, u);

					queue.remove(v);
					queue.add(new MyEntry<>(v, dist.get(v)));
				}
			}
		}

		return dist;
	}


	/** Return an ArrayList that contains the vertices that form the shortest path from S to X in order. **/
	public ArrayList shortestPath(String s, String x) {
		if (!this.adjacencyList.keySet().contains(s)) {
			throw new IllegalArgumentException(s + " is not a vertex of this graph.");
		} else if (!this.adjacencyList.keySet().contains(x)) {
			throw new IllegalArgumentException(x + " is not a vertex of this graph.");
		}

		LinkedHashMap<String, Double> dist = new LinkedHashMap<>();
		LinkedHashMap<String, String> prev = new LinkedHashMap<>();
		PriorityQueue<MyEntry<String, Double>> queue = new PriorityQueue<>(Comparator.comparing(entry -> entry.getValue()));
		ArrayList<String> sequence = new ArrayList<>();

		dist.put(s, (double) 0);

		for (String v : this.adjacencyList.keySet()) {
			if (v != s) {
				dist.put(v, Double.POSITIVE_INFINITY);
			}
			prev.put(v, null);
			queue.add(new MyEntry<>(v, dist.get(v)));
		}

		while (!queue.isEmpty()) {
			String u = queue.poll().getKey();

			if (u == x) {
				if (prev.get(u) != null || u == s) {
					while (u != null) {
						sequence.add(u);
						u = prev.get(u);
					}
					Collections.reverse(sequence);
					return sequence;
				}
			}

			Set<String> neighbors = this.adjacencyList.get(u).keySet();

			for (String v : neighbors) {
				double alt = dist.get(u) + this.adjacencyList.get(u).get(v);

				if (alt < dist.get(v)) {
					dist.put(v, alt);
					prev.put(v, u);

					queue.remove(v);
					queue.add(new MyEntry<>(v, dist.get(v)));
				}
			}
		}

		Collections.reverse(sequence);
		return sequence;
	}


	/** **/
	public LinkedHashMap bellmanFord(String s) {
		if (!this.adjacencyList.keySet().contains(s)) {
			throw new IllegalArgumentException(s + " is not a vertex of this graph.");
		}

		// Distance and predecessors.
		LinkedHashMap<String, Double> dist = new LinkedHashMap<>();
		LinkedHashMap<String, String> prev = new LinkedHashMap<>();
		Set<String> vertices = this.adjacencyList.keySet();

		for (String v : vertices) {
			dist.put(v, Double.POSITIVE_INFINITY);
			prev.put(v, null);
		}

		dist.put(s, (double) 0);

		// Relax edges repeatedly.
		for (int i=1; i < vertices.size(); i++) {
			for (String u : vertices) {
				for (String v : this.adjacencyList.get(u).keySet()) {
					double weight = this.adjacencyList.get(u).get(v);
					if (dist.get(u) + weight < dist.get(v)) {
						dist.put(v, dist.get(u) + weight);
						prev.put(v, u);
					}
				}
			}
		}

		for (String u : vertices) {
			for (String v : this.adjacencyList.get(u).keySet()) {
				double weight = this.adjacencyList.get(u).get(v);
				if (dist.get(u) + weight < dist.get(v)) {
					throw new IllegalArgumentException("This graph contains negative cycles.");
				}
			}
		}

		return dist;
	}


	// Cycle detection.


	/** Given a vertex S, return True if there is a cycle from and to S. Otherwise,
	    return False.

	    PARAMS: V: Vertex currently being looked at. Initially unvisited.
	            S: "Source" vertex that we're checking to see if it has a cycle. Initially unvisited.
	            VISITED: Set of visited vertices. Initially empty.**/
	public boolean vertexHasCycle(String v) {
		return vertexHasCycleHelper(v, v, new HashSet<String>());
	}


	public boolean vertexHasCycleHelper(String v, String s, HashSet<String> visited) {
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
				if (vertexHasCycleHelper(u, s, visited)) {
					return true;
				}
			}
		}

		return false;

	}


	/** Kahn's Algorithm for Topological Sort. **/
	public List<String> topologicalSort() {
		// 
		Stack<String> noInwardEdgeVertices = new Stack<>();
		List<String> sortedElems = new ArrayList<>();
		LinkedHashMap<String, Integer> inwardEdgesCopy = new LinkedHashMap<>(this.inwardEdges);

		for (String vertex : inwardEdgesCopy.keySet()) {
			if (this.inwardEdges.get(vertex) == 0) {
				noInwardEdgeVertices.add(vertex);
			}
		}

		while (!noInwardEdgeVertices.isEmpty()) {
			String u = noInwardEdgeVertices.pop();

			sortedElems.add(u);

			for (String v : this.adjacencyList.get(u).keySet()) {
				int degree = inwardEdgesCopy.get(v);
				inwardEdgesCopy.remove(v);
				inwardEdgesCopy.put(v, degree-1);

				if (inwardEdgesCopy.get(v) == 0) {
					noInwardEdgeVertices.add(v);
				}
			}
		}

		for (String vertex : inwardEdgesCopy.keySet()) {
			if (inwardEdgesCopy.get(vertex) != 0) {
				return null;
			}
		}

		return sortedElems;
	}


	/** Modified Kahn's Algorithm for cycle detection. **/
	public boolean isDAG() {
		// 
		Stack<String> noInwardEdgeVertices = new Stack<>();
		List<String> sortedElems = new ArrayList<>();
		LinkedHashMap<String, Integer> inwardEdgesCopy = new LinkedHashMap<>(this.inwardEdges);

		for (String vertex : inwardEdgesCopy.keySet()) {
			if (this.inwardEdges.get(vertex) == 0) {
				noInwardEdgeVertices.add(vertex);
			}
		}

		while (!noInwardEdgeVertices.isEmpty()) {
			String u = noInwardEdgeVertices.pop();

			sortedElems.add(u);

			for (String v : this.adjacencyList.get(u).keySet()) {
				int degree = inwardEdgesCopy.get(v);
				inwardEdgesCopy.remove(v);
				inwardEdgesCopy.put(v, degree-1);

				if (inwardEdgesCopy.get(v) == 0) {
					noInwardEdgeVertices.add(v);
				}
			}
		}

		for (String vertex : inwardEdgesCopy.keySet()) {
			if (inwardEdgesCopy.get(vertex) != 0) {
				return true;
			}
		}

		return false;
	}


	// Strongly connected components.


	/** Tarjan's **/




}

