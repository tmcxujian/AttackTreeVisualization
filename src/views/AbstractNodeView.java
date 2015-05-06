package views;

import java.io.Serializable;

import models.Node;
import models.Position;

/**
 * Virtual Node View
 * @author xujian
 *
 */
public abstract class AbstractNodeView extends AbstractView implements Serializable{

	Node node;
    int furthestRight;
    int furthestLeft;
    
    /**
     * Construct for AbstractNodeView
     * @param node
     * @param position
     */
    public AbstractNodeView(Node node, Position position){
    		super(position);
        this.node = node;
        furthestLeft = 0;
        furthestRight = width;
    }
    
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public abstract boolean moveTo(Position toPosition);

	public int getFurthestRight() {
		return furthestRight;
	}

	public void setFurthestRight(int furthestRight) {
		this.furthestRight = furthestRight;
	}

	public int getFurthestLeft() {
		return furthestLeft;
	}

	public void setFurthestLeft(int furthestLeft) {
		this.furthestLeft = furthestLeft;
	}
    
    
}
