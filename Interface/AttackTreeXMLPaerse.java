import java.util.List;

import controllers.ConnectNodeController;
import views.LineView;
import views.MainView;
import views.NodeView;
import models.MainState;
import models.Node;

/**
 * 
 * @author xujian
 *
 */
public class AttackTreeXMLPaerse {

	MainView mainView;
	MainState mainState;
	String xmlFile = "";
	//Node model, in this case either Node or NodeView contains more information than it is right now
	//Node/NodeView have to know exact what the relavant type and specific counterMeasures implemented
	List<Node> nodes;
	List<NodeView> nodeViews;
	//Line model
	List<LineView> lines;
	
	/**
	 * This constructor functions takes into a xml file name 
	 * And parses that xml file to get a list of nodes and lines
	 * @param fileName
	 */
	public AttackTreeXMLPaerse(String fileName){
		this.xmlFile = fileName;
		//Initialize the list of nodes and lines based on the information from xml file
	}
	
	public void restore(){
		for(NodeView nv: this.nodeViews){
			this.mainState.addAttackNode(nv.getNode());
			this.mainView.addNodeView(nv);
		}
		for(LineView lv: lines){
			new ConnectNodeController(this.mainView, this.mainState).connect(lv);
		}
		//Then with the mainView, now you can start to render mainView/mainState
	}
}
