package controllers;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Node;
import models.MainState;
import models.NodeType;
import models.Position;
import views.MainView;
import views.NodeView;
/**
 * Create Node Controller is responsible for creating a new node
 * @author xujian
 *
 */
public class CreateNodeController extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -489547660323412667L;
	private MainView mainView;
	private MainState mainState;
	private JFrame frame;
	
	/**
	 * Constructor for CreateNodeController
	 * @param mainView
	 * @param mainState
	 */
	public CreateNodeController(MainView mainView, MainState mainState){
		this.mainState = mainState;
		this.mainView = mainView;
	}
	
	/**
	 * Create Node based on the information provided by user
	 */
	@SuppressWarnings("null")
	public void createNode() {
		JTextField xField = new JTextField(5);
	    JTextField yField = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("New Node's Name:"));
	    myPanel.add(xField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("New Node's Type:"));
	    myPanel.add(yField);
	    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	         String name = xField.getText();
	         NodeType type = processType(yField.getText());
		//String name = JOptionPane
				//.showInputDialog("Please Enter New Node's Name");
		if (name != null && name.length() > 0) {			
			Node node = new Node(name,type);
			//System.out.println(node == null);
			this.mainState.addAttackNode(node);
			NodeView nodeView = new NodeView(node, new Position(200, 200));
			nodeView.updateView();
			this.mainView.addNodeView(nodeView);
			this.mainView.addLabelOf(nodeView);
			this.mainView.refresh();
		}
		} else {
			JOptionPane.showMessageDialog(frame, "Invalid Node Name",
					"Type Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public NodeType processType(String s){
		if(s.toUpperCase().equals("AND")){
			return NodeType.AND;
		}
		else if(s.toUpperCase().equals("OR")){
			return NodeType.OR;
		}
		else if(s.toUpperCase().equals("NOT")){
			return NodeType.NOT;
		}
		else{
			return NodeType.LEAF;
		}
	}
}
