package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * This is Node model represent each node in the attack tree
 * @author xujian
 *
 */
public class Node implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4040345206821817241L;
	String value;
	List<Node> childNodes;
	List<String> relavantCounterMeasures;
	Set<CounterMeasure> specificCounterMeasures;
	Map<String, CounterMeasure> specificCounterMeasureMap;
	//leaf to determine whether this node is leaf nor not for quantification purpose
	boolean leaf;
	long id;
	//with color to determine which relation, details could refer to updateColor()
	NodeType type;
	
	/**
	 * Construct for Node class
	 * @param name
	 * @param type
	 */
	public Node(String name,NodeType type){
		this.value = name;
		this.childNodes = new ArrayList<Node>();
		this.relavantCounterMeasures = new ArrayList<String>();
		this.specificCounterMeasures = new HashSet<CounterMeasure>();
		specificCounterMeasureMap = new HashMap<String, CounterMeasure>();
		this.type = type;
		this.leaf = this.type == NodeType.LEAF ? true : false;
	}
	
	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	//only allows parent to connect child
	public void connect(Node node){
		this.childNodes.add(node);
	}
	
	//remove the connetion
	public void remove(Node node){
		this.childNodes.remove(node);
	}
	
	public void modifyName(String name){
		this.value = name;
	}
	
	public NodeType getType(){
		return this.type;
	}
	
	public void setType(NodeType nType){
		this.type = nType;
		this.leaf = this.type == NodeType.LEAF ? true : false;
	}
	
	public String getAttackNodeName(){		
		return this.value;
		
	}
	
	public Collection<Node> getChildNodes(){
		return this.childNodes;
	}
	
	public void addRelevantCounterMeasures(String s){
		if(this.relavantCounterMeasures == null){
			this.relavantCounterMeasures = new ArrayList<String>();
		}
		this.relavantCounterMeasures.add(s);
	}
	
	public void addRelevantCounterMeasures(ArrayList<String> str){
		if(this.relavantCounterMeasures == null){
			this.relavantCounterMeasures = new ArrayList<String>();
		}
		this.relavantCounterMeasures.clear();
		this.relavantCounterMeasures.addAll(str);
	}
	
	public List<String> getRelavantCounterMeasures(){
		return this.relavantCounterMeasures;
	}
	
	public void addSpecificCounterMeasures(CounterMeasure cm){
		if(this.specificCounterMeasureMap == null){
			this.specificCounterMeasureMap = new HashMap<String, CounterMeasure>();
		}
		if(this.specificCounterMeasures == null){
			this.specificCounterMeasures = new HashSet<CounterMeasure>();
		}
		this.specificCounterMeasures.add(cm);
		this.specificCounterMeasureMap.put(cm.getValue(),cm);
	}
	
	public void removeSpecificCounterMeasures(CounterMeasure cm){
		this.specificCounterMeasureMap.remove(cm.getValue());
		this.specificCounterMeasures.remove(cm);
	}
	
	public Map<String, CounterMeasure> getSpecificCounterMeasureMap(){
		return this.specificCounterMeasureMap;
	}
	
	public Set<CounterMeasure> getSpecificCounterMeasures(){
		return this.specificCounterMeasures;
	}

}
