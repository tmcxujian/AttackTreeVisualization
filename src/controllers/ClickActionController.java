package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.CounterMeasure;
import views.MainView;
import views.NodeView;
/**
 * This one controller deals with click menu to add/remove specific countermeasure
 * @author xujian
 */
public class ClickActionController implements ActionListener{

	private MainView mainView;
	private NodeView nodeView;
	private CounterMeasure counterMeasure;
	
	/**
	 * Constructor for ClickActionController
	 * @param mainView
	 * @param nodeView
	 * @param counterMeasure
	 */
	public ClickActionController(MainView mainView, NodeView nodeView,CounterMeasure counterMeasure) {
		// TODO Auto-generated constructor stub
		this.mainView = mainView;
		this.nodeView = nodeView;
		this.counterMeasure = counterMeasure;
	}
	
	/**
	 * Make Sure when this contermeasure is not duplicated, if yes, remove it. 
	 * if no, then add it int node class
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.nodeView.getNode().getSpecificCounterMeasureMap() != null
			 && this.nodeView.getNode().getSpecificCounterMeasureMap().size() > 0
			 && this.nodeView.getNode().getSpecificCounterMeasureMap().containsKey(this.counterMeasure.getValue())){
			System.out.println(this.counterMeasure.getValue());
			this.nodeView.getNode().removeSpecificCounterMeasures(this.counterMeasure);
		}
		else{
			this.nodeView.getNode().addSpecificCounterMeasures(this.counterMeasure);
		}
		this.mainView.refresh();
	}

}
