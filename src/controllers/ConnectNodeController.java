package controllers;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;

import models.Line;
import models.MainState;
import views.LineView;
import views.MainView;
import views.NodeView;
/**
 * This is ConenctController used for connecting Two nodes
 * @author xujian
 *
 */
public class ConnectNodeController extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3155458937783379507L;
	private MainView mainView;
	private MainState mainState;
	private JFrame frame;
	
	/**
	 * Constructor for ConnectNodeController
	 * @param mainView
	 * @param mainState
	 */
	public ConnectNodeController(MainView mainView, MainState mainState){
		this.mainState = mainState;
		this.mainView = mainView;
	}
	
	/**
	 * Connect Two Node based on their ID (String Name)
	 */
	public void connect(){	
		NodeView parentNodeView = null;
		NodeView childNodeView = null;
		String parentNode = JOptionPane.showInputDialog("Please Enter Parent Node's Name");
		String childNode = JOptionPane.showInputDialog("Please Enter Child Node's Name");
		int count = 0;
		for(NodeView nodeView : this.mainView.getNodeViews()){
			//traverse the whole node in the mainView to find those two nodes
			if(nodeView.getNode().getAttackNodeName().equals(parentNode)){
				count++;
				parentNodeView = nodeView;
				this.mainView.setParentNodeView(nodeView);
			}
			else if(nodeView.getNode().getAttackNodeName().equals(childNode)){
				count++;
				childNodeView = nodeView;
				this.mainView.setChildNodeView(nodeView);
			}
			//have already found thaat pair
			else if(count == 2){
				break;
			}
			else{
				continue;
			}
		}
		if(parentNodeView != null && childNodeView != null){
			for(LineView lineView : this.mainView.getLineViews()){
				if(lineView.getParentNodeView() == parentNodeView && 
						lineView.getChildNodeView() == childNodeView){
					break;
				}
			}			
			this.mainView.addLineView(new LineView(parentNodeView, childNodeView));
			this.mainView.repaint();			
		}
		else{
			JOptionPane.showMessageDialog(frame, "No pair found!","Type error", JOptionPane.ERROR_MESSAGE);
		}
		this.mainView.refresh();
	}
	
	//add that lineView to mainView 
	public void connect(LineView lineView){
		this.mainView.addLineView(lineView);
		this.mainView.repaint();
	}

}
