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
	//JMenu oneMenu;
	//JMenu twoMenu;
	List<JMenu> menus;
	List<JMenuItem> menuItems;
	List<CounterMeasure> counterMeasures;
	Set<String> stringOfSubType;
	Set<String> stringOfValue;
    //JMenuItem oneItem;
    //JMenuItem twoItem;
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
    			/*for(CounterMeasure cm : this.counterMeasures){
    				if(cm.getGeneralType().equals(s)){
    					JMenuItem jMenuItem = new JMenuItem(cm.getValue());
    					jMenuItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								if(e.getActionCommand().equals("Use user ID and password to authenticate clinic staff")){
									System.out.println("Hello");
								}
							}
						});
    					temp.add(jMenuItem);
    				}
    			}*/	
    			menus.add(temp);
    			add(temp);
    		}	        
    }	
}
