package controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;

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
	NodeView selectedNode;
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
	
	public void mousePressedHandler(Position position){		
		for (NodeView nodeView : this.mainView.getNodeViews()) {
			//System.out.println(nodeView.getNode().getAttackNodeName());
			if (nodeView.isClicked(position)) {
				selectedNode = nodeView;
				//System.out.println(selectedNode.getNode().getAttackNodeName()+"gg");
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
				for(JCheckBox b : box){
					b.setSelected(false);
				}
				//return;
			}
			else{
				for(JCheckBox b : box){
					b.setSelected(false);
				}
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

			for(Map.Entry<String, CounterMeasure> entry : lastSelectedNode.getNode()
					.getSpecificCounterMeasureMap().entrySet()){
				CounterMeasure cm = entry.getValue();
				str.append(i + " Type: " + cm.getGeneralType() + "\n" +
						"SubType: " + cm.getSubType() + "\n" + 
						"Value: " + cm.getValue() + "\n" + "\n") ;
				i++;
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
	
	@Override
	public void mouseReleased(MouseEvent e){
		Position mousePosition = new Position(e.getX(), e.getY());
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
	
	public void mouseReleasedHandler(Position position){
		mouseDownPosition = position;
		this.lastSelectedNode = selectedNode;
		this.selectedNode = null;
	}
	
	/**
	 * This is pop-out Menu
	 * @param e
	 * @param nodeView
	 */
	private void doPop(MouseEvent e,NodeView nodeView){
		PopUpDemoController menu = new PopUpDemoController(nodeView,this.mainView);
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	public NodeView getLastSelectedNodeView(){
		return this.lastSelectedNode;
	}
	
}
