package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import models.CounterMeasure;
import models.MainState;
import models.Node;
import models.Position;
/**
 * RelevantCounterMeasure Window informs which are relevant Types, and how many are implemented
 * @author xujian
 *
 */
public class RelevantCounterMeasureView extends AbstractView{
	
	private final MainView mainView;
	private final MainState mainState;
	
	private JPanel panel;
	private JButton btnSubmit;
	private JTextArea txtArea;
	private JLabel label;
	private ArrayList<JCheckBox> boxes;
	
	/**
	 * Construct for RelevantConterMeasureView
	 * @param position
	 * @param width
	 * @param height
	 * @param mainView
	 * @param mainState
	 */
	public RelevantCounterMeasureView(Position position, int width, int height, final MainView mainView, final MainState mainState){
		super(position,width,height);
		this.mainState = mainState;
		this.mainView = mainView;
		
		panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new LineBorder(Color.BLACK));
        panel.setBounds(position.getX(), position.getY(), width, height);
        
        JPanel jBoxPanel = new JPanel();
        boxes = new ArrayList<JCheckBox>();
        int i = 0;
        int total = this.mainView.getExploreView().getDictionaryParserController().getFirstType().size();
        for(String s : this.mainView.getExploreView().getDictionaryParserController().getFirstType()){
        		JCheckBox box = new JCheckBox(s);
        		//box.setSelected(false);
        		box.setLocation(position.getX() + i/total * width, position.getY() + 1/total*height);
        		jBoxPanel.add(box);
        		this.boxes.add(box);
        }
        panel.add(jBoxPanel,BorderLayout.CENTER);
        
        JPanel p = new JPanel();
        label = new JLabel("node");
        label.setLocation(position.getX(), position.getY());
        p.add(label);
        
        txtArea = new JTextArea();
        txtArea.setLocation(position.getX() + 5, position.getY());
        txtArea.setLineWrap(true);
        p.add(txtArea);
        
        panel.add(p,BorderLayout.PAGE_START);
        
        JPanel btnPanel = new JPanel();
        btnSubmit = new JButton("Set Relavant CounterMeasure");
        btnPanel.add(btnSubmit);
        panel.add(btnPanel,BorderLayout.PAGE_END);
        
        btnSubmit.addActionListener(new ActionListener() {
			//once submit, reset the node relevant countermeasure type
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> str = new ArrayList<String>();
				Node temp = mainView.getMouseInputController().getLastSelectedNodeView().getNode();
				Map<String, CounterMeasure> map = new HashMap<String,CounterMeasure>(temp.getSpecificCounterMeasureMap());
				Set<CounterMeasure> set = new HashSet<CounterMeasure>(temp.getSpecificCounterMeasures());
				for(JCheckBox box : boxes){
					if(box.isSelected()){
						str.add(box.getText());					
					}
					else{					
						for(Map.Entry<String, CounterMeasure> entry : map.entrySet()){
							CounterMeasure cc = entry.getValue();
							if(cc.getGeneralType().equals(box.getText())){
								temp.removeSpecificCounterMeasures(cc);
							}
						}
					}
				}
				temp.addRelevantCounterMeasures(str);
				temp.setLeaf(true);
			}
		});
        this.mainView.refresh();
	}

	public JPanel getPanel(){
		return this.panel;
	}
	
	public ArrayList<JCheckBox> getJCheckBoxs(){
		return this.boxes;
	}
	
	public JTextArea getTextArea(){
		return this.txtArea;
	}
	
	public void setTextArea(String s){
		this.txtArea.setText(s);
	}
	
}
