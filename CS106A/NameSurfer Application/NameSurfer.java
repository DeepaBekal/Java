/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

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
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	private String line;
	public void actionPerformed(ActionEvent e) {
		String cmd ;
		line = NameInput.getText();
		cmd = e.getActionCommand();
		if(cmd.equals("Graph")) println("Graph : "+line);
		else if(cmd.equals("Clear")) println("Clear");
		
	}
	
	public void keyPressed(KeyEvent e){
		line = NameInput.getText();
		if(e.getKeyCode()==KeyEvent.VK_ENTER)println("Graph : "+line);
	}
	
	private JLabel NameLabel;
	private JTextField NameInput;
	private JButton ClickGraph;
	private JButton ClickClear;
}




