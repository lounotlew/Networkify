// A Graph representation using an adjacency list, used for Networkify/.
//
// Written by Lewis Kim.


static class UndirectedGraph {

	int V;
	LinkedList<Integer> adjacencyList[];

	// Constructor.
	Graph(int V) {
		this.V = V;

		adjacencyList = new LinkedList[V];

		for(int i = 0; i < V; i++) {
			adjacencyList[i] = new LinkedList<>();
		}
	}

	// Add an edge in GRAPH from U to V.
	static void addEdge(Graph graph, int u, int v) {
		
		graph.adjacencyList[u].addFirst(v);
		graph.adjacencyList[v].addFirst(u);
	}
}