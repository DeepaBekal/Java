/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.io.IODialog;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	
	public static void main(String[] args){
		new NameSurfer().start(args);
	}
	
	public void init() {
		NameLabel= new JLabel("Name");
		NameInput= new JTextField(21);
		ClickGraph = new JButton("Graph");
		ClickClear= new JButton("Clear");
		add(NameLabel,NORTH);
		add(NameInput, NORTH);
		add(ClickGraph, NORTH);
		add(ClickClear, NORTH);
	    addActionListeners();
	    NameInput.addKeyListener(this);					//to plot graph when ENTER is pressed
	    DB = new NameSurferDataBase(NAMES_DATA_FILE);	//text file which has all name rankings
	    Graph = new NameSurferGraph();
	    add(Graph);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so when buttons of Graph & Clear are pressed Graph is plotted or cleared
	 * respectively.
	 */
	private String line;
	private NameSurferEntry temp;
	public void actionPerformed(ActionEvent e) {
		
		/*Clears the plotted graph when Clear button is clicked*/
		if(e.getActionCommand().equals("Clear")){
			Graph.clear();									//clears plotted graphs
			Graph.update();									//updates with default view with x & y axis 
		}
		/*plots the graph when Graph button is clicked*/
		else{
			line = NameInput.getText();						//assigns the name entered
			temp = DB.findEntry(line);						//checks in the database for entered name
			
			/*displays a dialog if name is not in database i.e., text file*/
			if(temp==null){
				//println(temp);							//added for testing
				IODialog error = getDialog();
				error.println(line+" name does not exist in database");				
			}
			/*updates the entered name into the array & plots graph*/
			else{
				Graph.addEntry(temp);						//adds the name & corresponding data into ArrayList of type NameSurferEntry
				Graph.update();								//updates default view along with graph for entered names
			}
		}
	}
	
	/**
	 * This class is responsible for detecting when the keys are
	 * pressed, so when ENTER is pressed Graph will be plotted if the entered name exists in 
	 * database*/
	public void keyPressed(KeyEvent e){
		line = NameInput.getText();
		temp = DB.findEntry(line);
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(temp==null){
				println(temp);
				IODialog error = getDialog();
				error.println(line+" name does not exist in database!");				
			}
			else{
				Graph.addEntry(temp);
				Graph.update();
			}
		}
	}
	
	/*Private instance variables*/
	private JLabel NameLabel;								//to display "Name"
	private JTextField NameInput;							//text field to enter name
	private JButton ClickGraph;								//button for graph
	private JButton ClickClear;								//button for clear
	private NameSurferDataBase DB;							//to read in text file data into HashMap
	private NameSurferGraph Graph;							//to carry out graph activities	
}




