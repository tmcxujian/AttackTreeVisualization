package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import views.MainView;
import views.NodeView;
import models.MainState;
import models.Memento;
import models.Node;
/**
 * This Class is used Memento Design Pattern to help load state and store state
 * So we can keep track of the state information
 * @author xujian
 *
 */
public class LoadMementoController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6814031441634231401L;
	Memento memento;
	
	/**
	 * Constructor for LoadMementoController
	 * @param mainView
	 */
	public LoadMementoController(MainView mainView){
		this.memento = new Memento(mainView.getNodeViews(),mainView.getLineViews());
	}
	
	/**
	 * Load State from last state
	 * @param mainView
	 * @param mainState
	 */
	public void loadState(MainView mainView, MainState mainState){
		Collection<Node> nodes = new ArrayList<Node>();
		for(NodeView nodeView : memento.getNodeViews()){
			Node node = nodeView.getNode();
			nodes.add(node);
		}
		mainState.resetAttackNodes(nodes);
		for(NodeView nodeView : memento.getNodeViews()){
			mainView.addLabelOf(nodeView);
			mainView.addNodeView(nodeView);
		}
		if(memento.getLineViews() != null){
			mainView.resetLineViews(memento.getLineViews());
			mainView.repaint();
		}	
		mainView.refresh();
		mainView.repaint();
	}
}
