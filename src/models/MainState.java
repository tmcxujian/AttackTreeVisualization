package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import controllers.LoadMementoController;
/**
 * This MainState keeps track of all information of these models involved in the system
 * @author xujian
 *
 */
public class MainState implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5765837551012459330L;
	Collection<Node> Nodes;
	
	public MainState(LoadMementoController mementoController){
		if(mementoController == null){
			this.Nodes = new ArrayList<Node>();
			Node Root = new Node("Root",NodeType.LEAF);
			Node One = new Node("One",NodeType.LEAF);
			Node Two = new Node("Two",NodeType.LEAF);
			Node Three = new Node("Three",NodeType.LEAF);
			this.Nodes.add(Root);
			this.Nodes.add(One);
			this.Nodes.add(Two);
			this.Nodes.add(Three);
		}
		else{
			this.Nodes = new ArrayList<Node>();
		}
	}
	
	public MainState(Collection<Node> Nodes){
		this.Nodes = new ArrayList<Node>();
		this.Nodes.addAll(Nodes);
	}
	
	public Collection<Node> getAttackNodes(){
		return this.Nodes;
	}
	
	public void addAttackNode(Node node){
		System.out.println(this.Nodes + "test" + node);
		this.Nodes.add(node);
	}
	
	public void addAllAttackNode(Collection<Node> nodes){
		this.Nodes.addAll(nodes);
	}
	
	public void resetAttackNodes(Collection<Node> nodes){
		this.Nodes = new ArrayList<Node>();
		this.Nodes.addAll(nodes);
		return;
	}
}
