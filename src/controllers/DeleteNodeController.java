package controllers;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import models.MainState;
import views.LineView;
import views.MainView;
import views.NodeView;
/**
 * Delete Node Controller is responsbible for deleting nodes
 * @author xujian
 *
 */
public class DeleteNodeController {
	
	private MainView mainView;
	private MainState mainState;
	Collection<NodeView> nodeViews;
	
	/**
	 * Constructor for DeleteNodeController
	 * @param mainView
	 * @param mainState
	 */
	public DeleteNodeController(MainView mainView, MainState mainState){
		this.mainView = mainView;
		this.mainState = mainState;
	}
	 
	//also can use
	//String parentNode = JOptionPane.showInputDialog("Please Enter Parent Node's Name");
	public void deleteNode(){
		this.nodeViews = this.mainView.getNodeViews();
		String name = JOptionPane.showInputDialog("Please Enter Delete Node's Name");
		for(NodeView nodeView: nodeViews){
			if(nodeView.getNode().getAttackNodeName().equals(name)){
				Collection<LineView> lv = new ArrayList<LineView>();
				lv.addAll(this.mainView.getLineViews());
				for(LineView lineView : lv){
					if(lineView.getChildNodeView() == nodeView || 
							lineView.getParentNodeView() == nodeView){
						this.mainView.removeLineView(lineView);
						//this.mainView.refresh();
					}
				}
				this.mainState.getAttackNodes().remove(nodeView.getNode());
				this.mainView.getNodeViews().remove(nodeView);
				this.mainView.removeLabelOf(nodeView);
				this.mainView.refresh();
				break;
			}
		}
	}
}
