package views;


import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.JPanel;

/**
 * General how a line looks like
 * @author xujian
 *
 */
public class LineView extends JPanel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4493848488605893641L;
	private NodeView parentNodeView;
	private NodeView childNodeView;
	long id;
	
	public LineView(NodeView superNodeView, NodeView childNodeView){
		this.childNodeView = childNodeView;
		this.parentNodeView = superNodeView;
	}
	
	public String toString(){
		return "{" + parentNodeView.getNode().getAttackNodeName() + "->" +
				childNodeView.getNode().getAttackNodeName() + "}";
	}
	
	public NodeView getParentNodeView(){
		return this.parentNodeView;
	}
	
	public NodeView getChildNodeView(){
		return this.childNodeView;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.drawLine(this.parentNodeView.getPosition().getX() + this.parentNodeView.width / 2,
				this.parentNodeView.getPosition().getY() + 2 * this.parentNodeView.height + 3,
				this.childNodeView.getPosition().getX() + this.childNodeView.width / 2, 
				this.childNodeView.getPosition().getY() + this.childNodeView.height + 3);
	}
	
}
