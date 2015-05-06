package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import models.MainState;
import views.MainView;
/**
 * This ButtonController contains logic and operation depending on
 *  each specifc button gets clicked
 * @author xujian
 *
 */
public class ButtonController implements ActionListener{
	
	private MainView mainView;
	private MainState mainState;
	
	/**
	 * Constructor function of ButtonController
	 * @param mainView
	 * @param mainState
	 */
	public ButtonController(MainView mainView, MainState mainState){
		this.mainState = mainState;
		this.mainView = mainView;
		
	}
	
	/**
	 * Decide which one button gets clicked and how to respond to that button
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		//clear other font features
		this.mainView.resetFont();
		JButton clickButton = (JButton) e.getSource();
		//create button function
		if(clickButton.equals(mainView.getCreateButton())){	
			new CreateNodeController(this.mainView, this.mainState).createNode();
		}
		//delete button function
		if(clickButton.equals(mainView.getDeleteButton())){
			new DeleteNodeController(this.mainView, this.mainState).deleteNode();
		}
		//connect button function
		if(clickButton.equals(mainView.getConnectButton())){
			new ConnectNodeController(this.mainView, this.mainState).connect();;
		}
		//calculation button function
		if(clickButton.equals(mainView.getCalculateButton())){
			new CalculationController(this.mainView,this.mainState).calculateSecrityCondition();;
		}
	}
}
