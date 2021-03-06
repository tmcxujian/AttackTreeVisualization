package views;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import models.Node;
import models.NodeType;
import models.Position;
/**
 * This class indicates how one node looks like
 * @author xujian
 *
 */
public class NodeView extends AbstractNodeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3012251334321774805L;
	public JLabel label;
	Collection<LineView> lineViews;
	final private Font font;
	
	/**
	 * Construct for nodeView class
	 * @param node
	 * @param position
	 */
	public NodeView(Node node, Position position) {
		super(node, position);
		furthestRight = node.getAttackNodeName().length() * 8;
		lineViews = new ArrayList<LineView>();
		setSize(node.getAttackNodeName().length() * 8, 20);
		//setSize(node.getAttackNodeName().length() * 10, 20);
		label = new JLabel(node.getAttackNodeName(), SwingConstants.CENTER);
		updateView();	
		updateColor();
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label.setOpaque(true);
		this.font = label.getFont();
	}

	public Node getNode() {
		return this.node;
	}

	public Position getPosition() {
		return this.position;
	}

	public void updateView() {
		label.setBounds(this.position.getX(), this.position.getY(), width, height);
	}
	
	//update node color based on their role
	//leaf/Intermedia node with different logic relation
	public void updateColor(){
		if(node.getType() == NodeType.AND){
			label.setBackground(Color.RED);
		}
		else if(node.getType() == NodeType.OR){
			label.setBackground(Color.GREEN);
		}
		else if(node.getType() == NodeType.NOT){
			label.setBackground(Color.YELLOW);
		}
		else{
			label.setBackground(Color.LIGHT_GRAY);
		}
	}
	
	public void updateColor(Color c){
		label.setBackground(c);
	}

	public boolean moveTo(Position pos) {
		this.position = pos;
		updateView();
		return true;
	}

	/*public boolean isClicked(Position click) {
		boolean isClicked = false;
		System.out.println(node.getAttackNodeName() + " |x|" + click.getX() + " " + position.getX()
				+ " |x|" + (position.getX() + this.node.getAttackNodeName().length() * 10));
		System.out.println(node.getAttackNodeName() + " |y|" + click.getY() + " " + position.getY()
				+ " |y|" + (position.getY() + 20));
		if ((this.position.getX() < click.getX())
				&& (click.getX() < this.position.getX()
						+ this.node.getAttackNodeName().length() * 10)
				&& (this.position.getY() < click.getY())
				&& (click.getY() < this.position.getY() + 20)) {
			isClicked = true;
		}
		System.out.println(isClicked);
		return isClicked;
	}*/

	//set font for node
	public void setFont(){
		Font fon = this.label.getFont();
		Font boldFont = new Font(fon.getFontName(), Font.BOLD, fon.getSize());
		this.label.setFont(boldFont);
	}
	
	//reset font 
	public void resetFont(){
		this.label.setFont(this.font);
	}
}
