// ...

// Written by Lewis Kim.

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


		/** Adding vertices. **/

		// Current graph label.
		JLabel addVertexLabel = new JLabel("Add vertices to this graph.");
		addVertexLabel.setBounds(17, 70, 500, 35);
		mainPanel.add(addVertexLabel);

		JTextField vertexNameField = new JTextField("Vertex Name");
		vertexNameField.setBounds(10, 100, 90, 35);
		mainPanel.add(vertexNameField);

		JButton addVertexButton = new JButton("Add Vertex");
		addVertexButton.setBounds(100, 100, 90, 35);
		mainPanel.add(addVertexButton);


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
        				JOptionPane.showMessageDialog(frame1, "Successfully added " + vertexNameField.getText() + " to " + this.currDG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currDG.name + " already has that vertex.");
        			}
        		}
        		else {
        			try {
        				this.currUG.addVertex(vertexNameField.getText());
        				JOptionPane.showMessageDialog(frame1, "Successfully added " + vertexNameField.getText() + " to " + this.currUG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currDG.name + " already has that vertex.");
        			}
        		}
        	}
        });



		setTitle("Welcome to Networkify");
		setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(500, 500);
		// setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setGraphNameLabel(String name) {


	}



	public static void main(String[] args) {

		Networkify ui = new Networkify();

	}
}