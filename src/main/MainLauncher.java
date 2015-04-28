package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controllers.LoadMementoController;
import controllers.LoadStateController;
import views.MainView;
import models.Memento;
import models.Node;
import models.MainState;
/**
 * This is main function for this system
 * @author xujian
 *
 */
public class MainLauncher extends JFrame{

	/*public static void main(String[] args) {
		LoadStateController loadStateController = new LoadStateController();
		LoadMementoController memento = loadStateController.loadState();
		Node root = new Node("Root");
		Node One = new Node("One");
		Node Two = new Node("Two");
		Node Three = new Node("Three");
		
		Collection<Node> nodes = new ArrayList<Node>();
		nodes.add(root);
		nodes.add(One);
		nodes.add(Two);
		nodes.add(Three);
		
		final MainState mainState = new MainState(nodes);
		final MainView mainView = new MainView(mainState);
		
		mainView.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	                System.out.println("Program needs to close");
	            }
	        });
	        // Display the view
	     mainView.setVisible(true);
	}*/
	
	static private JFrame frame;
	static private String loadFileName = "";
	//static private String storageFileName = "";
	
	public static void main(String[] args) {
		String name = JOptionPane
				.showInputDialog("Please Enter System Storage File Name:");
		if(name != null && name.length() > 0){
			loadFileName = name;
		}
		/*else {
			JOptionPane.showMessageDialog(frame, "Invalid Node Name",
					"Type Error", JOptionPane.ERROR_MESSAGE);
		}*/
		LoadStateController loadStateController = new LoadStateController();
		LoadMementoController memento = loadStateController.loadState(loadFileName);
		
		final MainState mainState = new MainState(memento);
		final MainView mainView = new MainView(mainState,memento);
		
		mainView.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	            		//storageFileName = JOptionPane
	        				//.showInputDialog("Please Enter System Storage File Name:");
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
