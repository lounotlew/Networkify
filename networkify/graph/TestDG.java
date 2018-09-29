// Graph print test for DirectedGraph.java.
//
// Written by Lewis Kim.

import java.util.LinkedHashMap;
import java.util.LinkedHashMap.*;
import java.util.HashSet;


public class TestDG {

	public static void main(String[] args) {
		// DirectedGraph g = new DirectedGraph("v1");

		// g.addVertex("v2");
		// g.addVertex("v3");
		// g.addVertex("v4");
		// g.addVertex("v5");

		// g.printGraph();

		// System.out.println("---");

		// g.addEdge("v1", "v2", (double) 1);
		// g.addEdge("v2", "v3", (double) 2);
		// g.addEdge("v3", "v4", (double) 3);
		// g.addEdge("v4", "v5", (double) 4);
		// g.addEdge("v5", "v1", (double) 5);

		// g.printGraph();

		// System.out.println("---");

		// g.removeVertex("v1");
		// g.removeVertex("v2");
		// g.addEdge("v5", "v3", (double) 1);
		// g.replaceWeight("v3", "v4", (double) 1);

		// g.printGraph();

		// System.out.println("---");

		DirectedGraph g = new DirectedGraph();

		// g.addVertex("v2");
		// g.addVertex("v3");
		// g.addVertex("v4");
		// g.addVertex("v5");
		// g.addVertex("v6");
		// g.addVertex("v7");
		// g.addVertex("v8");
		// g.addVertex("v9");

		// g.addEdge("v1", "v2", (double) 1);
		// g.addEdge("v1", "v3", (double) 1);
		// g.addEdge("v2", "v5", (double) 1);
		// g.addEdge("v2", "v4", (double) 1);
		// g.addEdge("v4", "v1", (double) 1);
		// g.addEdge("v5", "v1", (double) 1);
		// g.addEdge("v5", "v6", (double) 1);
		// g.addEdge("v6", "v1", (double) 1);
		// g.addEdge("v3", "v7", (double) 1);
		// g.addEdge("v7", "v8", (double) 1);
		// g.addEdge("v8", "v7", (double) 1);
		// g.addEdge("v8", "v9", (double) 1);

		// // g.printGraph();

		// HashSet<String> visited = new HashSet<String>();

		// System.out.println("v1:" + g.vertexHasCycle("v1", "v1", new HashSet<String>()));
		// System.out.println("v2:" + g.vertexHasCycle("v2", "v2", new HashSet<String>()));
		// System.out.println("v3:" + g.vertexHasCycle("v3", "v3", new HashSet<String>()));
		// System.out.println("v4:" + g.vertexHasCycle("v4", "v4", new HashSet<String>()));
		// System.out.println("v5:" + g.vertexHasCycle("v5", "v5", new HashSet<String>()));
		// System.out.println("v6:" + g.vertexHasCycle("v6", "v6", new HashSet<String>()));
		// System.out.println("v7:" + g.vertexHasCycle("v7", "v7", new HashSet<String>()));
		// System.out.println("v8:" + g.vertexHasCycle("v8", "v8", new HashSet<String>()));
		// System.out.println("v9:" + g.vertexHasCycle("v9", "v9", new HashSet<String>()));

		g.addVertex("S");
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addVertex("G");
		g.addVertex("H");

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
		g.addEdge("H", "A", (double) 4);

		System.out.println(g.isDAG());
		// System.out.println(g.shortestPath("A", "A"));
		// System.out.println(g.shortestPath("A", "B"));
		// System.out.println(g.shortestPath("A", "C"));
		// System.out.println(g.shortestPath("A", "D"));
		// System.out.println(g.shortestPath("A", "E"));
		// System.out.println(g.shortestPath("A", "F"));
		// System.out.println(g.shortestPath("A", "G"));
		// System.out.println(g.shortestPath("A", "H"));

		// System.out.println(g.vertexHasCycle("C", "C", new HashSet<String>()));
		// System.out.println(g.vertexHasCycle("D", "D", new HashSet<String>()));
		// System.out.println(g.vertexHasCycle("E", "E", new HashSet<String>()));
		// System.out.println(g.vertexHasCycle("F", "F", new HashSet<String>()));
		// System.out.println(g.vertexHasCycle("G", "G", new HashSet<String>()));
		// System.out.println(g.vertexHasCycle("H", "H", new HashSet<String>()));



	}

}