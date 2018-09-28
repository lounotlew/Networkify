// Graph print test for DirectedGraph.java.
//
// Written by Lewis Kim.


public class TestDG {

	public static void main(String[] args) {
		DirectedGraph g = new DirectedGraph("v1");

		g.addVertex("v2");
		g.addVertex("v3");
		g.addVertex("v4");
		g.addVertex("v5");

		g.printGraph();

		System.out.println("---");

		g.addEdge("v1", "v2", (double) 1);
		g.addEdge("v2", "v3", (double) 2);
		g.addEdge("v3", "v4", (double) 3);
		g.addEdge("v4", "v5", (double) 4);
		g.addEdge("v5", "v1", (double) 5);

		g.printGraph();

		System.out.println("---");

		g.removeVertex("v1");
		g.removeVertex("v2");
		g.addEdge("v5", "v3", (double) 1);
		g.replaceWeight("v3", "v4", (double) 1);

		g.printGraph();

		System.out.println("---");

	}

}