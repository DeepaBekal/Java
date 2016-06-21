/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	
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
	    NameInput.addKeyListener(this);
	    DB = new NameSurferDataBase(NAMES_DATA_FILE);
	    Graph = new NameSurferGraph();
	    add(Graph);
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	private String line;
	private NameSurferEntry temp;
	public void actionPerformed(ActionEvent e) {		
		if(e.getActionCommand().equals("Clear")){
			Graph.clear();
			Graph.update();
		}
		else{
			line = NameInput.getText();
			temp = DB.findEntry(line);
			if(temp!=null){
				println(temp);
				Graph.addEntry(temp);
				Graph.update();
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		line = NameInput.getText();
		temp = DB.findEntry(line);
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(temp!=null){
				println(temp);
				Graph.addEntry(temp);
				Graph.update();
			}
		}
	}
	
	private JLabel NameLabel;
	private JTextField NameInput;
	private JButton ClickGraph;
	private JButton ClickClear;
	private NameSurferDataBase DB;
	private NameSurferGraph Graph;
	
	
	
}




