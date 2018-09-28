// Graph print test for UndirectedGraph.java.
//
// Written by Lewis Kim.


public class TestUG {

	public static void main(String[] args) {
		UndirectedGraph g = new UndirectedGraph("v1");

		g.addVertex("v2");
		g.addVertex("v3");
		g.addVertex("v4");
		g.addVertex("v5");

		g.printGraph();

		System.out.println("---");

		g.addEdge("v1", "v2", (double) 1);
		g.addEdge("v1", "v3", (double) 2);
		g.addEdge("v1", "v4", (double) 3);
		g.addEdge("v4", "v5", (double) 4);

		g.printGraph();

		System.out.println("---");

		g.removeVertex("v5");
		g.removeVertex("v1");
		g.addEdge("v2", "v3", (double) 1);
		g.replaceWeight("v2", "v3", (double) 2);

		g.printGraph();

		System.out.println("---");

	}

}