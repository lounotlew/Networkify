// ...

// Written by Lewis Kim.

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JFrame;


public class Networkify extends JFrame {

	public Networkify() {

		initUI();

		DirectedGraph g = new DirectedGraph();
	}

	private void initUI() {

		// Main JPanel.
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel);

		// Adding vertices to the graph.

		JButton addVertexButton = new JButton("Add a Vertex");
		addVertexButton.setBounds(35, 50, 120, 35);
		panel.add(addVertexButton);

		JTextField vertexNameField = new JTextField("Text 1", 10);
		vertexNameField.setBounds(35, 100, 120, 35);
		panel.add(vertexNameField);

        addVertexButton.addActionListener((ActionEvent event) -> {
            System.out.println(vertexNameField.getText());
        });




		setTitle("Welcome to Networkify");
		setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		// setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	public static void main(String[] args) {

		Networkify ui = new Networkify();

	}
}