# Networkify - Networks and Systems Builder and Analyzer Through Applied Graph Theory
> Written by Lewis Kim

### Description

Networkify is a Java-based Mac & Windows desktop application that utilizes graph theory to represent any desired network or system as a directed or undirected weighted graph. No knowledge of graph theory is needed to fully utilize Networkify.

A common application of graph theory (and hence a use case for Networkify) is to build a neighborhood or a city's street networks as a directed graph (whose weights would be the average time it takes to travel a road or street on average speed), and finding the shortest delivery path from a restaurant/vendor to a customer. Another application is to build a recommendation system for a major path for college students.

This application utilizes Swing for its GUI, and the interactive graph drawer is currently being improved on. It is highly recommended you use the interactive graph drawer for demonstration purposes, as the output cannot be saved, and any bigger graph should be constructed using the V2V (Vertex-to-Vertex) Connector.

Currently Implemented Algorithms:

_Directed Graphs:_

1) Dijkstra's Algorithm for Shortest Path.

2) Bellman-Ford's Algorithm for Shortest Path and Negative Cycle Detection.

3) Kahn's Algorithm for Topological Sort.

4) Modified Kahn's Algorithm to Check if Graph is a DAG.

5) Tarjan's Algorithm for Finding Strongly Connected Components.

_Undirected Graphs:_

1) Dijkstra's Algorithm for Shortest Path.

2) Bellman-Ford's Algorithm for Shortest Path and Negative Cycle Detection.

3) Prim's Algorithm for MST.

4) Kruskal' Algorithm for MST.

To be implemented:

1) Cycle detection through DFS in Directed Graphs.

2)  Union-Find Algorithm for Cycle Detection in Undirected Graphs.

### Packages & Libraries Used in Networkify

Netwokify only utilizes the standard Java library (``utils`` ans ``Swing``) found in JDK 11.

### References

References to the libraries and packages used in Networkify:

1) Java Utils Data Structures: https://www.java-examples.com/java-collections-and-data-structures-java-util-package

2) Swing: https://docs.oracle.com/javase/tutorial/uiswing/


