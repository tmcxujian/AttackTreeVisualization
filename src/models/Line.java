package models;
/**
 * The model for one line, connecting with two points
 * @author xujian
 *
 */
public class Line {

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	private Node parentNode;
	private Node childNode;
	
	public Line(int x1, int x2, int y1, int y2, Node pNode, Node cNode){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.parentNode = pNode;
		this.childNode = cNode;
	}
	
}
