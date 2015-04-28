package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import models.CounterMeasure;
import models.MainState;
import models.Position;
/**
 * This is CounterMeasureView, shows which specific countermeasures have already been deployed 
 * @author xujian
 *
 */
public class CounterMeasureView extends JFrame{

	private MainView mainView;
	private MainState mainState;
	private JTextArea textArea;
	//private List<CounterMeasure> counterMeasures;
	public JPanel contentPane;
	
	public CounterMeasureView(MainView mainView, MainState mainState){
		this.mainState = mainState;
		this.mainView = mainView;
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 278, 302);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 284, 253);
        
        textArea = new JTextArea(50,3);
        textArea.setBounds(5, 5, 160, 655);
        contentPane.add(textArea);
        textArea.setColumns(10);
        textArea.setLineWrap(true);
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
