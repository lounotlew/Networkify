// ...

// Written by Lewis Kim.

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JFrame;


public class Networkify extends JFrame {

	DirectedGraph currDG;
	UndirectedGraph currUG;
	String graphType;

	public Networkify() {

		initUI();

	}

	private void initUI() {

		// Main JPanel.
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		add(mainPanel);


		/** Loading and creating graphs. **/

		// Current graph label.
		JLabel currGraphLabel = new JLabel("No graph currently selected.");
		currGraphLabel.setBounds(20, 35, 500, 35);
		mainPanel.add(currGraphLabel);

		// Loading and saving graphs.
		JButton loadGraphButton = new JButton("Load a Graph");
		loadGraphButton.setBounds(10, 5, 125, 35);
		mainPanel.add(loadGraphButton);

		// Creating a new graph.
		JButton createGraphButton = new JButton("Create a Graph");
		createGraphButton.setBounds(140, 5, 125, 35);
		mainPanel.add(createGraphButton);


		/** Adding and removing vertices. **/

		DefaultComboBoxModel<String> vertexModel = new DefaultComboBoxModel<String>(new String[0]);

		// Current graph label.
		JLabel addVertexLabel = new JLabel("Add vertices to this graph.");
		addVertexLabel.setBounds(17, 70, 500, 35);
		mainPanel.add(addVertexLabel);

		JTextField vertexNameField = new JTextField("Vertex Name");
		vertexNameField.setBounds(10, 100, 100, 35);
		mainPanel.add(vertexNameField);

		JButton addVertexButton = new JButton("Add Vertex");
		addVertexButton.setBounds(110, 100, 90, 35);
		mainPanel.add(addVertexButton);

		JLabel removeVertexLabel = new JLabel("Remove vertices from this graph.");
		removeVertexLabel.setBounds(220, 70, 500, 35);
		mainPanel.add(removeVertexLabel);

		JComboBox removeVertexList = new JComboBox(vertexModel);
		removeVertexList.setBounds(220, 100, 125, 35);
		mainPanel.add(removeVertexList);

		JButton removeVertexButton = new JButton("Remove Vertex");
		removeVertexButton.setBounds(350, 100, 125, 35);
		mainPanel.add(removeVertexButton);


		/** Adding Edges. **/
		JLabel addEdgeLabel = new JLabel("Connect two vertices in this graph.");
		addEdgeLabel.setBounds(17, 140, 500, 35);
		mainPanel.add(addEdgeLabel);

		JComboBox vertexList1 = new JComboBox(vertexModel);
		JComboBox vertexList2 = new JComboBox(vertexModel);
		vertexList1.setBounds(10, 160, 125, 35);
		vertexList2.setBounds(150, 160, 125, 35);
		mainPanel.add(vertexList1);
		mainPanel.add(vertexList2);


		// JTextField vertexNameField = new JTextField("Text 1", 10);
		// vertexNameField.setBounds(35, 100, 120, 35);
		// mainPanel.add(vertexNameField);


		// Loading a serialized graph.
        loadGraphButton.addActionListener((ActionEvent event) -> {
        	System.out.println("NYI");
        });


        // Creating a new graph.
        createGraphButton.addActionListener((ActionEvent event) -> {
        	JFrame frame1 = new JFrame("Graph Type");
        	JFrame frame2 = new JFrame("Graph Name");

        	Object[] graphOptions = {"Directed Graph", "Undirected Graph"};
        	String selectedGraph = (String) JOptionPane.showInputDialog(
                    frame1,
                    "What type of graph would you like to create?",
                    "Select Graph Type",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    graphOptions,
                    graphOptions[0]);

        	String graphName = "";
        	while (graphName.isEmpty()) {
        		graphName = JOptionPane.showInputDialog(frame2, "Please choose a name for this graph (cannot be empty).");
        	}


        	if (selectedGraph == "Directed Graph") {
        		this.graphType = "DG";
        		this.currDG = new DirectedGraph(graphName);
        		currGraphLabel.setText("Directed Graph: " + graphName);
        	}

        	else {
        		this.graphType = "UG";
        		this.currUG = new UndirectedGraph(graphName);
        		currGraphLabel.setText("Undirected Graph: " + graphName);
        	}

        });

        // Adding a node to this graph.
        addVertexButton.addActionListener((ActionEvent event) -> {
        	JFrame frame1 = new JFrame("Add Vertex");

        	if (this.graphType == null) {
        		JOptionPane.showMessageDialog(frame1, "Please load or create a graph first.");
        	}

        	else {
        		if (this.graphType == "DG") {
        			try {
        				this.currDG.addVertex(vertexNameField.getText());
        				vertexModel.addElement(vertexNameField.getText());
        				JOptionPane.showMessageDialog(frame1, "Successfully added " + vertexNameField.getText() + " to " + this.currDG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currDG.name + " already has that vertex.");
        			}
        		}
        		else {
        			try {
        				this.currUG.addVertex(vertexNameField.getText());
        				vertexModel.addElement(vertexNameField.getText());
        				JOptionPane.showMessageDialog(frame1, "Successfully added " + vertexNameField.getText() + " to " + this.currUG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currDG.name + " already has that vertex.");
        			}
        		}
        	}
        });


        // Adding a node to this graph.
        removeVertexButton.addActionListener((ActionEvent event) -> {
        	JFrame frame1 = new JFrame("Remove Vertex");

        	if (this.graphType == null) {
        		JOptionPane.showMessageDialog(frame1, "Please load or create a graph first.");
        	}

        	else {
        		if (this.graphType == "DG") {
        			try {
        				String name = (String)vertexModel.getSelectedItem();
        				this.currDG.removeVertex(name);
        				vertexModel.removeElement(name);
        				JOptionPane.showMessageDialog(frame1, "Successfully removed " + name + " to " + this.currDG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currDG.name + " does not have that vertex.");
        			}
        		}
        		else {
        			try {
        				String name = (String)vertexModel.getSelectedItem();
        				this.currDG.removeVertex(name);
        				vertexModel.removeElement(name);
        				JOptionPane.showMessageDialog(frame1, "Successfully removed " + name + " to " + this.currUG.name + ".");
        				JOptionPane.showMessageDialog(frame1, "Successfully added " + vertexNameField.getText() + " to " + this.currUG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currDG.name + " does not have that vertex.");
        			}
        		}
        	}
        });



        // addEdgeButton.addActionListener((ActionEvent event) -> {

        // });



		setTitle("Welcome to Networkify");
		setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(500, 500);
		// setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public String[] getVertexList() {
		String[] noGraph = {"Load or create a graph first."};
		String[] noVertices = {"Please add vertices to your graph first."};

		if (this.graphType == null) {
			return noGraph;
		}
		else if (this.graphType == "DG") {
			if (this.currDG.getAllVertices().size() == 0) {
				return noVertices;
			} else {
				Set<String> vertexSet = this.currDG.getAllVertices();
				String[] vertices = vertexSet.toArray(new String[vertexSet.size()]);
				return vertices;
			}
		} else {
			if (this.currUG.getAllVertices().size() == 0) {
				return noVertices;
			} else {
				Set<String> vertexSet = this.currUG.getAllVertices();
				String[] vertices = vertexSet.toArray(new String[vertexSet.size()]);
				return vertices;
			}
		}
	}



	public static void main(String[] args) {

		Networkify ui = new Networkify();

	}
}