package controllers;

import models.MainState;
import models.Position;
import views.MainView;
import views.NodeView;
/**
 * Deal move functionality of nodes
 * @author xujian
 *
 */
public class MoveNodeController {
	
	private MainView mainView;
	private MainState mainState;
	
	/**
	 * Constructor for MoveNodeController
	 * @param mainView
	 * @param mainState
	 */
	public MoveNodeController(MainView mainView, MainState mainState){
		this.mainView = mainView;
		this.mainState = mainState;
	}
	
	/**
	 * Real implmentation of moveNode
	 * @param nodeView
	 * @param positionFrom
	 * @param positionTo
	 * @return
	 */
	public boolean moveNode(NodeView nodeView, Position positionFrom, Position positionTo){
		Position originalPosition = nodeView.getPosition();
		int positionDiffX = positionTo.getX() - positionFrom.getX();
		int positionDiffY = positionTo.getY() - positionFrom.getY();
		Position newPosition = new Position(originalPosition.getX() + positionDiffX,
				originalPosition.getY() + positionDiffY);
		nodeView.moveTo(newPosition);
		return true;
	}
}
