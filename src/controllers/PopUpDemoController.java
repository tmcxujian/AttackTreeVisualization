package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import models.CounterMeasure;
import views.MainView;
import views.NodeView;
/**
 * This one is responsible for constructing the pop-up menu
 * @author xujian
 *
 */
public class PopUpDemoController extends JPopupMenu{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3523363310996121025L;
	List<JMenu> menus;
	List<JMenuItem> menuItems;
	List<CounterMeasure> counterMeasures;
	Set<String> stringOfSubType;
	Set<String> stringOfValue;
    NodeView nodeView;
    CounterMeasure strCounterMeasure;
    MainView mainView;
    
    /**
     * Constructor for popupDemoController
     * @param nodeView
     * @param mainView
     */
    public PopUpDemoController(NodeView nodeView, MainView mainView){
    		this.mainView = mainView;
    		this.counterMeasures = mainView.getExploreView().getDictionaryParserController().
    				getCounterMeasures();
    		this.nodeView = nodeView;
    		menus = new ArrayList<JMenu>();
    		menuItems = new ArrayList<JMenuItem>();
    		stringOfSubType = new HashSet<String>();
    		stringOfValue = new HashSet<String>();
 	    List<String> ss = nodeView.getNode().getRelavantCounterMeasures();
 	    if(ss == null){
 	    		return;
 	    }
 	    //traverse the relevant countermeasure and construct the corresponding menu
    		for(String s : ss){
    			stringOfSubType.clear();
    			JMenu temp = new JMenu(s);
    			for(CounterMeasure cm: this.counterMeasures){
    				if(cm.getGeneralType().equals(s) && !stringOfSubType.contains(cm.getSubType())){    					
    					String str = cm.getSubType();					
    					stringOfSubType.add(str);
    					JMenu jMenu = new JMenu(cm.getSubType());
    					stringOfValue.clear();
    					for(CounterMeasure c : this.counterMeasures){
    						if(c.getSubType().equals(str) && !stringOfValue.contains(c.getValue())){
    							JMenuItem item = new JMenuItem(c.getValue());
    							this.strCounterMeasure = cm;
    							item.addActionListener(new ClickActionController(mainView, nodeView,c));
    							jMenu.add(item);
    						}
    						stringOfValue.add(c.getValue());
    					}   					
    					temp.add(jMenu);
    				}
    			}	
    			menus.add(temp);
    			add(temp);
    		}	        
    }	
}
