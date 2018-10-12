// ...

// Written by Lewis Kim.

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JFrame;
import java.text.DecimalFormat;


public class Networkify extends JFrame {

	DirectedGraph currDG;
	UndirectedGraph currUG;
	String graphType;

	public Networkify() {

		initUI();

	}

	private void initUI() {

		// Main JPanel.
		JPanel mainPanel = new JPanel(new GridLayout(0, 2));
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

		DefaultComboBoxModel<String> removeVertexModel = new DefaultComboBoxModel<String>(new String[0]);
		DefaultComboBoxModel<String> addEdgeVertexModel1 = new DefaultComboBoxModel<String>(new String[0]);
		DefaultComboBoxModel<String> addEdgeVertexModel2 = new DefaultComboBoxModel<String>(new String[0]);
		DefaultComboBoxModel<String> removeEdgeVertexModel1 = new DefaultComboBoxModel<String>(new String[0]);
		DefaultComboBoxModel<String> removeEdgeVertexModel2 = new DefaultComboBoxModel<String>(new String[0]);


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

		JComboBox removeVertexList = new JComboBox(removeVertexModel);
		removeVertexList.setBounds(220, 100, 125, 35);
		mainPanel.add(removeVertexList);

		JButton removeVertexButton = new JButton("Remove Vertex");
		removeVertexButton.setBounds(350, 100, 125, 35);
		mainPanel.add(removeVertexButton);


		/** Adding Edges. **/
		JLabel addEdgeLabel = new JLabel("Connect two vertices in this graph.");
		addEdgeLabel.setBounds(17, 140, 500, 35);
		mainPanel.add(addEdgeLabel);

		JComboBox addEdgeVertexList1 = new JComboBox(addEdgeVertexModel1);
		JComboBox addEdgeVertexList2 = new JComboBox(addEdgeVertexModel2);
		addEdgeVertexList1.setBounds(10, 160, 125, 35);
		addEdgeVertexList2.setBounds(150, 160, 125, 35);
		mainPanel.add(addEdgeVertexList1);
		mainPanel.add(addEdgeVertexList2);

		JTextField weightField = new JTextField();
		weightField.setBounds(10, 190, 125, 35);
		mainPanel.add(weightField);

		JButton addEdgeButton = new JButton("Add Edge");
		addEdgeButton.setBounds(150, 190, 125, 35);
		mainPanel.add(addEdgeButton);


		/** Removing Edges. **/
		JLabel removeEdgeLabel = new JLabel("Remove an edge from this graph.");
		removeEdgeLabel.setBounds(17, 220, 500, 35);
		mainPanel.add(removeEdgeLabel);

		JComboBox removeEdgeVertexList1 = new JComboBox(removeEdgeVertexModel1);
		JComboBox removeEdgeVertexList2 = new JComboBox(removeEdgeVertexModel2);
		removeEdgeVertexList1.setBounds(10, 250, 125, 35);
		removeEdgeVertexList2.setBounds(150, 250, 125, 35);
		mainPanel.add(removeEdgeVertexList1);
		mainPanel.add(removeEdgeVertexList2);

		JButton removeEdgeButton = new JButton("Remove Edge");
		removeEdgeButton.setBounds(10, 290, 125, 35);
		mainPanel.add(removeEdgeButton);


		// JTextField vertexNameField = new JTextField("Text 1", 10);
		// vertexNameField.setBounds(35, 100, 120, 35);
		// mainPanel.add(vertexNameField);


		// Loading a serialized graph.
        loadGraphButton.addActionListener((ActionEvent event) -> {
        	if (this.graphType == null) {
        		return;
        	} else {
        		if (this.graphType == "DG") {
        			this.currDG.printGraph();
        		}
        		else {
        			this.currUG.printGraph();
        		}
        	}
        	// analysisUI();
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
        				removeVertexModel.addElement(vertexNameField.getText());
        				addEdgeVertexModel1.addElement(vertexNameField.getText());
        				addEdgeVertexModel2.addElement(vertexNameField.getText());
        				removeEdgeVertexModel1.addElement(vertexNameField.getText());
        				removeEdgeVertexModel2.addElement(vertexNameField.getText());
        				JOptionPane.showMessageDialog(frame1, "Successfully added " + vertexNameField.getText() + " to " + this.currDG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currDG.name + " already has that vertex.");
        			}
        		}
        		else {
        			try {
        				this.currUG.addVertex(vertexNameField.getText());
        				removeVertexModel.addElement(vertexNameField.getText());
        				addEdgeVertexModel1.addElement(vertexNameField.getText());
        				addEdgeVertexModel2.addElement(vertexNameField.getText());
        				removeEdgeVertexModel1.addElement(vertexNameField.getText());
        				removeEdgeVertexModel2.addElement(vertexNameField.getText());
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
        				String name = (String)removeVertexModel.getSelectedItem();
        				this.currDG.removeVertex(name);
        				removeVertexModel.removeElement(name);
        				addEdgeVertexModel1.removeElement(name);
        				addEdgeVertexModel2.removeElement(name);
        				removeEdgeVertexModel1.removeElement(name);
        				removeEdgeVertexModel2.removeElement(name);
        				JOptionPane.showMessageDialog(frame1, "Successfully removed " + name + " to " + this.currDG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currDG.name + " does not have that vertex.");
        			}
        		}
        		else {
        			try {
        				String name = (String)removeVertexModel.getSelectedItem();
        				this.currUG.removeVertex(name);
        				removeVertexModel.removeElement(name);
        				addEdgeVertexModel1.removeElement(name);
        				addEdgeVertexModel2.removeElement(name);
        				removeEdgeVertexModel1.removeElement(name);
        				removeEdgeVertexModel2.removeElement(name);
        				JOptionPane.showMessageDialog(frame1, "Successfully removed " + name + " to " + this.currUG.name + ".");
        			} catch (IllegalArgumentException i) {
        				JOptionPane.showMessageDialog(frame1, this.currUG.name + " does not have that vertex.");
        			}
        		}
        	}
        });


        // Adding an edge.
        addEdgeButton.addActionListener((ActionEvent event) -> {
        	JFrame frame1 = new JFrame("Add Edge");
        	try {
        		double weight = Double.parseDouble(weightField.getText());
        		String v1 = (String)addEdgeVertexModel1.getSelectedItem();
        		String v2 = (String)addEdgeVertexModel2.getSelectedItem();

        		if (this.graphType == null) {
        			JOptionPane.showMessageDialog(frame1, "Please load or create a graph first.");
        		} else {
        			if (this.graphType == "DG") {
        				if (this.currDG.adjacencyList.get(v1).keySet().contains(v2)) {
        					int replaceWeight = JOptionPane.showConfirmDialog(frame1, "That edge already exists on this graph. Would you like to replace it with a new weight of " + Double.toString(weight) + "?");

        					if (replaceWeight == JOptionPane.YES_OPTION) {
        						this.currDG.replaceWeight(v1, v2, weight);
        						JOptionPane.showMessageDialog(frame1, "Successfully replaced edge weight between " + v1 + " and " + v2 + ".");
        					} else {
        						return;
        					}
        				} else {
        					this.currDG.addEdge(v1, v2, weight);
        					JOptionPane.showMessageDialog(frame1, "Successfully added edge between " + v1 + " and " + v2 + ".");
        				}
        			}
        			else {
        				if (this.currUG.adjacencyList.get(v1).keySet().contains(v2)) {
        					int replaceWeight = JOptionPane.showConfirmDialog(frame1, "That edge already exists on this graph. Would you like to replace it with a new weight of " + Double.toString(weight) + "?");

        					if (replaceWeight == JOptionPane.YES_OPTION) {
        						this.currUG.replaceWeight(v1, v2, weight);
        						JOptionPane.showMessageDialog(frame1, "Successfully replaced edge weight between " + v1 + " and " + v2 + ".");
        					} else {
        						return;
        					}
        				} else {
        					this.currUG.addEdge(v1, v2, weight);
        					JOptionPane.showMessageDialog(frame1, "Successfully added edge between " + v1 + " and " + v2 + ".");
        				}
        			}
        		}
        	} catch(NumberFormatException e) {
        		JOptionPane.showMessageDialog(frame1, "That is not a valid edge weight. Please make sure the weight is in proper decimal format.");
        	} catch(IllegalArgumentException e) {
        		JOptionPane.showMessageDialog(frame1, "some other error");
        	}
        });


        // Removing an edge.
        removeEdgeButton.addActionListener((ActionEvent event) -> {
        	JFrame frame1 = new JFrame("Remove Edge");

        	if (this.graphType == null) {
        		JOptionPane.showMessageDialog(frame1, "Please load or create a graph first.");
        	} else {
        		String v1 = (String)removeEdgeVertexModel1.getSelectedItem();
        		String v2 = (String)removeEdgeVertexModel2.getSelectedItem();

        		try {
        			if (this.graphType == "DG") {
        				this.currDG.removeEdge(v1, v2);
        				JOptionPane.showMessageDialog(frame1, "Successfully removed the edge between " + v1 + " and " + v2 + ".");
        			} else {
        				this.currUG.removeEdge(v1, v2);
        				JOptionPane.showMessageDialog(frame1, "Successfully removed the edge between " + v1 + " and " + v2 + ".");
        			}
        		} catch (IllegalArgumentException e) {
        			JOptionPane.showMessageDialog(frame1, "There is no edge between " + v1 + " and " + v2 + ".");
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

	private void analysisUI() {
		JFrame analysisFrame = new JFrame("Table");
		// Main JPanel.
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		analysisFrame.add(mainPanel);


		analysisFrame.setTitle("Detailed Graph Analysis");
		analysisFrame.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		analysisFrame.setSize(500, 300);
		// setResizable(false);
		analysisFrame.setLocationRelativeTo(null);

	}




	public static void main(String[] args) {

		Networkify ui = new Networkify();

	}
}