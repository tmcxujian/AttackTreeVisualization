package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import views.LineView;
import views.NodeView;
/**
 * This is memento class used to store state
 * @author xujian
 *
 */
public class Memento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1375635235100375355L;

	private Collection<NodeView> nodeViews = new ArrayList<NodeView>();
	private Collection<LineView> lineViews = new ArrayList<LineView>();
	
	//go through current list of node and line and store those information
	public Memento(Collection<NodeView> nodeView, Collection<LineView> lineView){
		for(NodeView nv: nodeView){
			this.nodeViews.add(nv);
		}
		for(LineView lv: lineView){
			this.lineViews.add(lv);
		}
	}
	
	public Collection<NodeView> getNodeViews(){
		return this.nodeViews;
	}
	
	public Collection<LineView> getLineViews(){
		return this.lineViews;
	}
}
