// Undirected weighted graph using an adjacency list; used for Networkify.
//
// Written by Lewis Kim.

import java.util.*;

// Class of a weighted undirected graph using adjacency list representation through HashTables.
public class UndirectedGraph {

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
	String id;


	public UndirectedGraph(String root) {
		this.adjacencyList = new LinkedHashMap<>();
		this.adjacencyList.put(root, new LinkedHashMap<String, Double>());
		this.id = "Undirected Weighted Graph";
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
	/** Run Dijkstra's Algorithm on this weighted undirected graph from the root node S. 

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


	// BF.
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


	// Prim's.


	// Kruskal's.


	// Union Find.









}

