package models;

import java.io.Serializable;
/**
 * Position model
 * @author xujian
 *
 */
public class Position implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3996430235567994412L;
	public int x;
	public int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
}
