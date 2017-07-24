/**
 * File Name: GUI.java
 * Author: Rebecca Johnson
 * Date: 7/23/2017
 * Purpose: Creates a GUI and calculates the specified value in a sequence
 * 	using both iterative and recursive methods. 
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GUI extends JFrame implements ActionListener{

	//holds height and weight for JFrame
	private static final int WIDTH = 280;
	private static final int HEIGHT = 210;
	
	//compute button
	private static JButton computeBtn;
	
	//text fields
	private static JTextField enterNInputText;
	private static JTextField resultOutputText;
	private static JTextField efficiencyOutputText;
	
	//labels
	private static JLabel enterNJLabel;
	private static JLabel resultJLabel;
	private static JLabel efficiencyJLabel;
	
	//radio buttons
	private static JRadioButton iterativeRadioBtn;
	private static JRadioButton recursiveRadioBtn;
	
	//print writer and file for writing to file
	private static PrintWriter writer;
	private static File file = new File("EfficiencyValues.txt");

	//constructor
	public GUI() {
		super("Project 3");
		
		//lays out the basic specifications of the frame
		setFrame(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setBackground(Color.lightGray);	
			
		//creates new jPanel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		add(panel, BorderLayout.NORTH);
		
		//creates new button group
		ButtonGroup group = new ButtonGroup();
		
		//iterative radio button specifications
		iterativeRadioBtn = new JRadioButton("Iterative");
		group.add(iterativeRadioBtn);
		iterativeRadioBtn.setSelected(true);
		
		//recursive radio button specifications
		recursiveRadioBtn = new JRadioButton("Recursive");
		group.add(recursiveRadioBtn);
		
		//n label specifications
		enterNJLabel = new JLabel("Enter n: ");
		
		//n input text specifications
		enterNInputText = new JTextField(5);
		
		//compute button specifications
		computeBtn = new JButton("Compute");
		computeBtn.setToolTipText("Computes the nth term");
		computeBtn.addActionListener(this);
		
		//results label specifications
		resultJLabel = new JLabel("Result: ");
		
		//results output text specifications
		resultOutputText = new JTextField(5);
		resultOutputText.setEditable(false);
		
		//efficiency label specifications
		efficiencyJLabel = new JLabel("Efficiency: ");
		
		//efficiency output text specifications
		efficiencyOutputText = new JTextField(5);
		efficiencyOutputText.setEditable(false);
		
		//adds components to panel along with separators
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(iterativeRadioBtn);
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(recursiveRadioBtn);
		panel.add(enterNJLabel);
		panel.add(enterNInputText);
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(computeBtn);
		panel.add(resultJLabel);
		panel.add(resultOutputText);
		panel.add(efficiencyJLabel);
		panel.add(efficiencyOutputText);
		
	}
	
	//creates the frame and contains its specifications
	private void setFrame(int width, int height) {
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
			
	//displays the GUI
	private void display() {
		setVisible(true);
	}
	
	//main method
	public static void main(String[] args) {		
		GUI g = new GUI();
		
		//displays the frame
		g.display();
		
		//compute button action listener
		computeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				//tries to create file with specified file name
				try {
					writer = new PrintWriter(file);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Could not create file.");
				}
			
				//checks if iterative radio button is selected
				if(iterativeRadioBtn.isSelected()) {
					try {
						//sets the text for the results text box
						resultOutputText.setText(Integer.toString(
								Sequence.computeIterative(Integer.parseInt(enterNInputText.getText()))));
						
						//sets the text for the efficiency text box
						efficiencyOutputText.setText(Integer.toString(Sequence.getEfficiency()));	
						
					} catch (NumberFormatException f1) {
						JOptionPane.showMessageDialog(null, "Input is not an integer. \nPlease try again.");
					}
				}
				
				//checks if recursive radio button is selected
				else if(recursiveRadioBtn.isSelected()) {
					try {
						//sets the text for the results text box
						resultOutputText.setText(Integer.toString(
								Sequence.computeRecursive(Integer.parseInt(enterNInputText.getText()))));
						
						//sets the text for the efficiency text box
						efficiencyOutputText.setText(Integer.toString(Sequence.getEfficiency()));
						
					} catch (NumberFormatException f2) {
						JOptionPane.showMessageDialog(null, "Input is not an integer. \nPlease try again.");
					}
				}
			}
		});	
	}

	//action listener used for window
	public void actionPerformed(ActionEvent arg0) {
		
		//handles writing to file when window is closed
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent w) {
				int iterativeEfficiency = 0;
				int recursiveEfficiency = 0;
				
				//goes through each n value from 0 to 10
				for(int i = 0; i <= 10; i++) {
					
					//calls computeIterative and sends over current value of i
					Sequence.computeIterative(i);
					iterativeEfficiency = Sequence.getEfficiency();
					
					//calls computeRecursvie and sends over current value of i
					Sequence.computeRecursive(i);
					recursiveEfficiency = Sequence.getEfficiency();
					
					//writes values to file
					writer.println(i + ", " + iterativeEfficiency + ", " + recursiveEfficiency);
				}
				
				//closes the print writer
				writer.close();
			}
		});
	}
}
