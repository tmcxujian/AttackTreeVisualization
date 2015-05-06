package controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

import models.CounterMeasure;
import models.MainState;
import models.Position;
import views.MainView;
import views.NodeView;

/**
 * This Class is used to keep track of mouse information
 * And do some operation according to mouse input
 * @author xujian
 *
 */
public class MouseInputController extends MouseAdapter{
		
	private MainView mainView;
	private MainState mainState;
	
	Position mouseDownPosition;
	Position selectedNodePositionRelativeToMouse;
	//keep track of current selected node
	NodeView selectedNode;
	//to keep track all information of last selected node 
	NodeView lastSelectedNode;
	
	/**
	 * Constructor for MouseInputController
	 * @param mainView
	 * @param mainState
	 */
	public MouseInputController(MainView mainView, MainState mainState){
		this.mainState = mainState;
		this.mainView = mainView;
	}
	
	/**
	 * when mouse pressed
	 */
	@Override
	public void mousePressed(MouseEvent e){
		if(this.selectedNode != null){
			System.out.println(this.selectedNode.getNode().getAttackNodeName() + "jjj");
		}
		Position mousePosition = new Position(e.getX(), e.getY());
		mousePressedHandler(mousePosition);
		mainView.refresh();
	}
	
	//mousePressedHandler is trying to determine which node gets clicked
	public void mousePressedHandler(Position position){		
		for (NodeView nodeView : this.mainView.getNodeViews()) {
			if (nodeView.isClicked(position)) {
				selectedNode = nodeView;
				break;
			}
		}
		if (this.selectedNode != null) {
			this.selectedNodePositionRelativeToMouse = new Position(
					selectedNode.getPosition().getX() - position.getX(),
					selectedNode.getPosition().getY() - position.getY());
		}
		mouseDownPosition = position;
	}
	
	/**
	 * This is for mouse double click
	 */
	@Override
	public void mouseClicked(MouseEvent e){
		ArrayList<JCheckBox> box = this.mainView.getRelevantCounterMeasureView().getJCheckBoxs();		
		if(e.getClickCount() == 2 && lastSelectedNode != null){
			if(lastSelectedNode.getNode().getRelavantCounterMeasures() == null
					|| lastSelectedNode.getNode().getRelavantCounterMeasures().size() == 0){
				//this selected node has no relevant counterMeasures, set all to be false
				for(JCheckBox b : box){
					b.setSelected(false);
				}
			}
			else{
				//reset/clear all check boxes
				for(JCheckBox b : box){
					b.setSelected(false);
				}
				//based on the relevant countermeasure type of selected node
				//we mark that specific check box
				for(String s : lastSelectedNode.getNode().getRelavantCounterMeasures()){
					for(JCheckBox b : box){
						if(b.getText().equals(s)){	
							b.setSelected(true);
						}
					}
				}				
			}
			String s = lastSelectedNode.getNode().getAttackNodeName();
			this.mainView.getRelevantCounterMeasureView().setTextArea(s);
			StringBuilder str = new StringBuilder();
			int i = 1;
			JTable table = this.mainView.getExploreView().getJTable();
			editorTable(table, lastSelectedNode.getNode().getSpecificCounterMeasureMap());
			//generate a string to describe all implemented countermeasures and 
			//show in countermeasure view
			for(Map.Entry<String, CounterMeasure> entry : lastSelectedNode.getNode()
					.getSpecificCounterMeasureMap().entrySet()){
				CounterMeasure cm = entry.getValue();
				str.append(i + " Type: " + cm.getGeneralType() + "\n" + 
						"Value: " + cm.getValue() + "\n" + "\n");
				i++;
	
				//Based on node's implemented countermeasure, we will render the jtable in the explore view
				for(int row = 0; row < table.getRowCount(); row++){
					if(table.getValueAt(row, 3) == null){
						continue;
					}
					if(table.getValueAt(row, 3).equals(cm.getValue())){
						table.getCellRenderer(row, 3).getTableCellRendererComponent(table, cm.getValue(), 
								false, false, row, 3);
						continue;
					}
				}
				
			}
			this.mainView.getCounterMeasureView().updateTextArea(str.toString());
		}		
		mainView.refresh();
	}
	
	/**
	 * This is for mouse Move
	 */
	@Override
	public void mouseDragged(MouseEvent e){
		Position mousePosition = new Position(e.getX(), e.getY());
		mouseDraggedHandler(mousePosition);
		mainView.refresh();
	}
	
	//During the mouse dragging, the node is constantly moving
	public void mouseDraggedHandler(Position mousePosition){
		if(this.selectedNode != null){
			MoveNodeController moveNodeController = new MoveNodeController(
					this.mainView, this.mainState);
			moveNodeController.moveNode(selectedNode, selectedNode.getPosition(), 
					new Position(mousePosition.getX() + selectedNodePositionRelativeToMouse.getX(),
							mousePosition.getY() + selectedNodePositionRelativeToMouse.getY()));
		}
		mouseDownPosition = mousePosition;
	}
	
	/**
	 * This is for mouse release situation. There should have several cases
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e){
		Position mousePosition = new Position(e.getX(), e.getY());
		//if this release is right click, then popup a countermeasure menu 
		//for user to add/remove specific countermeasures
		if(SwingUtilities.isRightMouseButton(e)){
			if(this.selectedNode != null){
				doPop(e,this.selectedNode);
			}
			else{
				System.out.println(mousePosition.getX() + " The point is " 
			+ mousePosition.getY());
			}		
		}
		mouseReleasedHandler(mousePosition);		
		mainView.refresh();
	}
	
	//update both lastselectedNode and selectedNode
	public void mouseReleasedHandler(Position position){
		mouseDownPosition = position;
		this.lastSelectedNode = selectedNode;
		this.selectedNode = null;
	}
	
	/**
	 * This is showing the pop-out Menu
	 * @param e
	 * @param nodeView
	 */
	private void doPop(MouseEvent e,NodeView nodeView){
		PopUpDemoController menu = new PopUpDemoController(nodeView,this.mainView);
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	//return lastedSelectedNode
	public NodeView getLastSelectedNodeView(){
		return this.lastSelectedNode;
	}
	
	//Override JTable default methods so that JTable could render different columns with different colors
	public static void editorTable(JTable table,
			final Map<String, CounterMeasure> map) {
		try {
			@SuppressWarnings("serial")
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {

				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {

					if (table.getValueAt(row, 3) == null) {
						setBackground(Color.LIGHT_GRAY);
					} else if (map.containsKey(table.getValueAt(row, 3)) && row <= table.getRowCount()) {
						//This Green means this column(countermeasure) is added in the table
						setBackground(Color.GREEN); 
					} else {
						setBackground(Color.LIGHT_GRAY);
					}
					return super.getTableCellRendererComponent(table, value,
							isSelected, hasFocus, row, column);
				}
			};
			//set the render we construct for each column of table
			for (int i = 0; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
