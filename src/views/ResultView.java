package views;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import models.MainState;
import models.Position;
/**
 * This one shows the final quantification result for the current model
 * @author xujian
 *
 */
public class ResultView extends AbstractView{

	private MainView mainView;
	private MainState mainState;
	private JPanel swapPanel;
	private JLabel label;
	
	/**
	 * Construct resultView
	 * @param position
	 * @param width
	 * @param height
	 * @param mainView
	 * @param mainState
	 */
	public ResultView(Position position, int width, int height, final MainView mainView,
			final MainState mainState){
		super(position,width,height);
		this.mainState = mainState;
		this.mainView = mainView;
		
		swapPanel = new JPanel();
        swapPanel.setLayout(new BorderLayout());
        swapPanel.setBorder(new LineBorder(Color.BLACK));
        swapPanel.setBounds(position.getX(), position.getY(), width, height);
        label = new JLabel();
        JLabel l = new JLabel();
        l.setText("Result View");
        label.setText("system's security condition = ");
        swapPanel.add(l);
        swapPanel.add(label);
	}
	
	public JPanel getPanel(){
		return this.swapPanel;
	}
	
	public void updateLabel(String s){
		this.label.setText(s);
	}
	
}
