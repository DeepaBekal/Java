/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		NameData = new ArrayList<NameSurferEntry>();
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		NameData.clear();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		NameData.add(entry);
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		DrawGraphGrid();
		if(NameData.size()>=0){
			for(int i=0;i<NameData.size();i++){
				NameSurferEntry temp = NameData.get(i);
				DrawEntry(temp, i);
			}
		}
	}
	
	private void DrawGraphGrid(){
		DrawHorizontalLines();
		DrawVerticalLines();
		PutDecades();
	}
	
	private void DrawHorizontalLines(){
		int x1=0, x2, y1, y2;
		x2 = getWidth();
		y1 = GRAPH_MARGIN_SIZE;
		y2= getHeight()-GRAPH_MARGIN_SIZE;
		GLine hl1 = new GLine(x1,y1, x2, y1);
		GLine hl2 = new GLine(x1, y2, x2, y2);
		add(hl1);
		add(hl2);
	}
	
	private void DrawVerticalLines(){
		int x=0,y1=0, y2;
		y2= getHeight();
		for(int i=0;i<NDECADES;i++){
			x=i*(getWidth()/NDECADES);
			GLine vl = new GLine(x,y1,x,y2);
			add(vl);
		}		
	}
	
	private void PutDecades(){
		int x=0,y=0;
		y= getHeight()-GRAPH_MARGIN_SIZE+15;
		//int year = START_DECADE;
		String labelYear;
		for(int i=0;i<NDECADES;i++){
			int year = START_DECADE;
			x=i*(getWidth()/NDECADES);
			year= year+(i*10);
			labelYear = Integer.toString(year);
			GLabel decade = new GLabel(labelYear,x,y);
			decade.setFont("Impact-14");
			add(decade);
		}	
	}
	
	
	private void DrawEntry(NameSurferEntry NameEntry, int EntryCounter){
		for(int j=0;j<NDECADES-1;j++){
			int rank1 = NameEntry.getRank(j);
			int rank2 = NameEntry.getRank(j+1);
			double x1=j*(getWidth()/NDECADES);
			double x2=(j+1)*(getWidth()/NDECADES);
			int y1=0;
			int y2=0;
			if(rank1!=0 && rank2!=0){
				y1=GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank1/MAX_RANK;
				y2=GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank2/MAX_RANK;
			}
			else if(rank1==0 && rank2==0){
				y1=getHeight()-GRAPH_MARGIN_SIZE;
				y2=getHeight()-GRAPH_MARGIN_SIZE;
			}
			else if(rank1==0){
				y1=getHeight()-GRAPH_MARGIN_SIZE;
				y2=GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank2/MAX_RANK;
			}
			else if(rank2==0){
				y1=GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank1/MAX_RANK;
				y2=getHeight()-GRAPH_MARGIN_SIZE;
			}
			GLine plot = new GLine(x1,y1,x2,y2);
			if(EntryCounter%4==0){
				plot.setColor(Color.BLUE);
			}
			else if(EntryCounter%4==1){
				plot.setColor(Color.RED);
			}
			else if(EntryCounter%4==2){
				plot.setColor(Color.MAGENTA);
			}
			else if(EntryCounter%4==3){
				plot.setColor(Color.DARK_GRAY);
			}
			add(plot);
			
		}
		
		for(int h=0;h<NDECADES;h++){
			String name = NameEntry.getName();
			int rank = NameEntry.getRank(h);
			String rankString = Integer.toString(rank);
			String label = name+" "+rankString;
			int x = h*(getWidth()/NDECADES);
			int y=0;
			if(rank!=0){
				y = GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank/MAX_RANK;
			}
			else{
				label = name+" *";
				y = getHeight()-GRAPH_MARGIN_SIZE;
			}
			
			GLabel rankLabel = new GLabel(label,x,y);
			if(EntryCounter%4==0){
				rankLabel.setColor(Color.BLUE);
			}
			else if(EntryCounter%4==1){
				rankLabel.setColor(Color.RED);
			}
			else if(EntryCounter%4==2){
				rankLabel.setColor(Color.MAGENTA);
			}
			else if(EntryCounter%4==3){
				rankLabel.setColor(Color.DARK_GRAY);
			}
			add(rankLabel);			
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private ArrayList<NameSurferEntry> NameData;
}
