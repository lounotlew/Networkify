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