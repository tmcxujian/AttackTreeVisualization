package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.LoadMementoController;
import controllers.LoadStateController;
import views.MainView;
import models.MainState;

/**
 * This is main function for this system
 * @author xujian
 *
 */
public class MainLauncher extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5865250693472698987L;
	static public JFrame frame;
	static public String loadFileName = "";
	
	public static void main(String[] args) {
		JComboBox<String> box = new JComboBox<String>();
	    box.addItem("System Model I");
	    box.addItem("System Model II");
	    box.addItem("System Model III");
	    box.addItem("Others");
	    JPanel myPanel = new JPanel();
	    myPanel.add(box);
	    //Let user to input which mode is needed
	    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Please Choose you System Model, if new choose Others", 
	               JOptionPane.OK_CANCEL_OPTION);	    
	    String name = box.getSelectedItem().toString();
		if(name != null && name.length() > 0 && result == JOptionPane.OK_OPTION){
			switch (name) {
			case "System Model I":
				loadFileName = "System Model I.storage";
				break;
			case "System Model II":
				loadFileName = "System Model II.storage";
				break;
			case "System Model III":
				loadFileName = "System Model III.storage";
				break;
			default:
				loadFileName = "";
				break;
			}
		}
		//invalid input system will exit
		else {
			//JOptionPane.showMessageDialog(frame, "Invalid Node Name",
					//"Type Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		LoadStateController loadStateController = new LoadStateController();
		LoadMementoController memento = loadStateController.loadState(loadFileName);
		//Construc the mainState and mainView
		final MainState mainState = new MainState(memento);
		final MainView mainView = new MainView(mainState,memento);
		//When use close window store all the information for future use
		mainView.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            		LoadStateController loadStateController = new LoadStateController();
	            		loadStateController.storeState(mainView, mainState,loadFileName);
	            		System.out.println("Program needs to close");
	            		System.exit(0);
	            }
	        });
	        // Display the view
	     mainView.setVisible(true);
	}
}
