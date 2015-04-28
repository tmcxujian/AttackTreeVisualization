package views;

import java.io.Serializable;

import models.Position;
/**
 * Virtual Node View
 * @author xujian
 *
 */
public abstract class AbstractView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8996055693871994813L;

	Position position;
	int width;
	int height;
	
	public AbstractView(Position position){
		this.position = position;
		this.width = 0;
		this.height = 0;
	}
	
	 public AbstractView(Position position, int width, int height) {
	        this.position = position;
	        this.width = width;
	        this.height = height;
	}
	 
	public boolean isClicked(Position click) {
		boolean isClicked = false;
		if (position.getX() < click.getX()
				&& click.getX() < position.getX() + width
				&& position.getY() < click.getY()
				&& click.getY() < position.getY() + height) {
			isClicked = true;
		}
		return isClicked;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
