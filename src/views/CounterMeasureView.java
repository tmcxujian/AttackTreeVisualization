package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import models.CounterMeasure;
import models.MainState;
import models.Position;
/**
 * This is CounterMeasureView
 * shows which specific countermeasures have already been implemented for specific node 
 * @author xujian
 *
 */
public class CounterMeasureView extends JFrame{

	private MainView mainView;
	private MainState mainState;
	private JTextArea textArea;
	private JScrollPane jScrollPane;
	public JPanel contentPane;
	private JLabel label;
	
	/**
	 * Construct for CounterMeasureView
	 * @param mainView
	 * @param mainState
	 */
	public CounterMeasureView(MainView mainView, MainState mainState){
		this.mainState = mainState;
		this.mainView = mainView;
		//set the window 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 278, 302);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 284, 253);
        //add a label
        label = new JLabel("Added CounterMeasures");
        label.setBounds(5, 5, 160, 20);
        contentPane.add(label);
        //add a textArea for context 
        textArea = new JTextArea(50,3);
        textArea.setBounds(5, 25, 160, 635);
        contentPane.add(textArea);
        textArea.setColumns(10);
        textArea.setLineWrap(true);
        
        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(5, 25, 160, 635);
        contentPane.add(jScrollPane);
        jScrollPane.setViewportView(textArea);
	}
	
	 public void updateTextArea(String s){
 		this.textArea.setText(s);
 		this.refresh();
	 }
	 
	 public void refresh() {
	        revalidate();
	        repaint();
	 }
}
