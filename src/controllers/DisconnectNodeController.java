package controllers;

import javax.swing.JOptionPane;

import models.MainState;
import views.MainView;
import views.NodeView;
/**
 * Disconnect Controller is responsible for disconnecting two nodes
 * Needs to be implmented
 * @author xujian
 *
 */
public class DisconnectNodeController {

	
	private MainView mainView;
	private MainState mainState;
	NodeView superNode;
	NodeView subNode;
	
	/**
	 * Constructor for DisconnectNodeController
	 * @param mainView
	 * @param mainState
	 */
	public DisconnectNodeController(MainView mainView, MainState mainState){
		this.mainState = mainState;
		this.mainView = mainView;
	}
	
	/**
	 * disconnect Node function
	 */
	public void disconnect(){
		String parentNode = JOptionPane.showInputDialog("Please Enter Parent Node's Name");
		String childNode = JOptionPane.showInputDialog("Please Enter Child Node's Name");
		for(NodeView nodeView : this.mainView.getNodeViews()){
			if(nodeView.getNode().getAttackNodeName().equals(parentNode)){
				this.superNode = nodeView;
			}
			else if(nodeView.getNode().getAttackNodeName().equals(childNode)){
				this.subNode = nodeView;
			}
			else{
				continue;
			}
		}
	}
}
