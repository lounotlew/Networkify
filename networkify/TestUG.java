// Graph print test for UndirectedGraph.java.
//
// Written by Lewis Kim.


public class TestUG {

	public static void main(String[] args) {
		// UndirectedGraph g = new UndirectedGraph("v1");

		// g.addVertex("v2");
		// g.addVertex("v3");
		// g.addVertex("v4");
		// g.addVertex("v5");

		// g.printGraph();

		// System.out.println("---");

		// g.addEdge("v1", "v2", (double) 1);
		// g.addEdge("v1", "v3", (double) 2);
		// g.addEdge("v1", "v4", (double) 3);
		// g.addEdge("v4", "v5", (double) 4);

		// g.printGraph();

		// System.out.println("---");

		// g.removeVertex("v5");
		// g.removeVertex("v1");
		// g.addEdge("v2", "v3", (double) 1);
		// g.replaceWeight("v2", "v3", (double) 2);

		// g.printGraph();

		// System.out.println("---");

		UndirectedGraph g = new UndirectedGraph("A");

		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addVertex("G");
		g.addVertex("H");
		g.addVertex("I");
		g.addVertex("J");

		g.addEdge("A", "B", (double) 3);
		g.addEdge("A", "C", (double) 4);
		g.addEdge("A", "D", (double) 7);
		g.addEdge("B", "C", (double) 1);
		g.addEdge("B", "F", (double) 5);
		g.addEdge("C", "D", (double) 2);
		g.addEdge("C", "F", (double) 6);
		g.addEdge("D", "E", (double) 3);
		g.addEdge("F", "E", (double) 1);
		g.addEdge("D", "G", (double) 6);
		g.addEdge("E", "G", (double) 3);
		g.addEdge("G", "H", (double) 2);
		g.addEdge("F", "H", (double) 8);
		g.addEdge("E", "H", (double) 4);


		System.out.println(g.bellmanFord("A"));

	}

}