package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import common.Constants;
import controllers.ButtonController;
import controllers.LoadMementoController;
import controllers.MouseInputController;
import models.Memento;
import models.Node;
import models.MainState;
import models.NodeType;
import models.Position;
/**
 * MainView contains the basic contentPane. It describes how the system looks like
 * @author xujian
 *
 */
public class MainView extends JFrame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8676650330363346184L;
	MainState mainState;
	private ExploreView exploreArea;
	private ResultView resultView;
	private RelevantCounterMeasureView relevantCounterMeasureView;
	private CounterMeasureView counterMeasureView;
	//List<CounterMeasureView> counterMeasureViews;
	public Container contentPane;
	private JPanel panel;
	private JButton btnCreate;
	private JButton btnDelete;
	private JButton btnConnect;
	private JButton btnCalculate;
	
	private NodeView parentNodeView;
	private NodeView childNodeView;
    
	Collection<NodeView> nodeViews;
	Collection<LineView> lineViews;
	
	ButtonController buttonController;
	MouseInputController mouseInputController;
	
	public MainView(MainState mainState, LoadMementoController loadMementoController){
		this.mainState = mainState;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setBounds(100, 100, 1000, 685);
		setBounds(100, 100, 1550, 685);
        contentPane = getContentPane();
        contentPane.setLayout(null);

        setTitle("AttackTreeVisualization");
        
        panel = new JPanel();
        panel.setBorder(new LineBorder(Color.BLACK));
        panel.setBounds(0, 0, Constants.AREA_WIDTH, Constants.AREA_HEIGHT);
        panel.setLayout(null);
        
        this.nodeViews = new ArrayList<NodeView>();
        this.lineViews = new ArrayList<LineView>();
        
		if (loadMementoController == null) {
			Random random = new Random();
			for (Node a : mainState.getAttackNodes()) {
				int x = random.nextInt(Constants.AREA_WIDTH - 100);
				int y = random.nextInt(Constants.AREA_HEIGHT);
				NodeView view = new NodeView(a, new Position(x, y));
				nodeViews.add(view);
				panel.add(view.label);
			}
		}
		else{
			loadMementoController.loadState(this, mainState);
		}
		
		contentPane.add(panel);
        
        btnCreate = new JButton("Create");
        btnCreate.setBounds(0, 0, 70, 20);
        btnCreate.setEnabled(true);
        panel.add(btnCreate);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(0, 20, 70, 20);
        btnDelete.setEnabled(true);
        panel.add(btnDelete);
        
        btnConnect = new JButton("Connect");
        btnConnect.setBounds(0, 40, 70, 20);
        btnConnect.setEnabled(true);
        panel.add(btnConnect);
        
        btnCalculate = new JButton("Calculate");
        btnCalculate.setBounds(0,60,70,20);
        btnCalculate.setEnabled(true);
        panel.add(btnCalculate);
        
        buttonController = new ButtonController(this, this.mainState);
        btnCreate.addActionListener(buttonController);
        btnDelete.addActionListener(buttonController);
        btnConnect.addActionListener(buttonController);
        btnCalculate.addActionListener(buttonController);
        
        exploreArea = new ExploreView(this,mainState);
        JPanel explorePanel = exploreArea.contentPane;
        explorePanel.setBorder(new LineBorder(Color.BLACK));
        //explorePanel.setBounds(716, 310, 284, 353);
        explorePanel.setBounds(1016, 310, 284, 353);
        contentPane.add(explorePanel);
        explorePanel.setLayout(null);
        
        resultView = new ResultView(new Position(1016, 0), 284, 155, this, this.mainState);
        contentPane.add(resultView.getPanel());
        
        relevantCounterMeasureView = new RelevantCounterMeasureView(new Position(1016, 152), 284, 160, this, this.mainState);
        contentPane.add(relevantCounterMeasureView.getPanel());
        
        counterMeasureView = new CounterMeasureView(this, mainState); 
        JPanel counterMeasurePanel = counterMeasureView.contentPane;
        counterMeasurePanel.setBorder(new LineBorder(Color.BLACK));
        counterMeasurePanel.setBounds(1300, 0, 170, 665);
        contentPane.add(counterMeasureView.contentPane);
        counterMeasurePanel.setLayout(null);
        
        mouseInputController = new MouseInputController(this, this.mainState);
        this.addMouseInputController(mouseInputController);
        this.refresh();
        
        //update();
        //System.out.println(this.lineViews.size());
	}
	
	public MainView(MainState mainState){
		this.mainState = mainState;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 685);
        contentPane = getContentPane();
        contentPane.setLayout(null);

        setTitle("AttackTreeVisualization");
        
        panel = new JPanel();
        panel.setBorder(new LineBorder(Color.BLACK));
        panel.setBounds(0, 0, Constants.AREA_WIDTH, Constants.AREA_HEIGHT);
        panel.setLayout(null);
              
        Random random = new Random();
        nodeViews = new ArrayList<NodeView>();
        lineViews = new ArrayList<LineView>();
        for(Node a : mainState.getAttackNodes()){
        		int x = random.nextInt(Constants.AREA_WIDTH - 100);
            int y = random.nextInt(Constants.AREA_HEIGHT);
            NodeView view = new NodeView(a, new Position(x, y));
            nodeViews.add(view);
            panel.add(view.label);
            //contentPane.add(view.label);
        }
        contentPane.add(panel);
        
        btnCreate = new JButton("Create");
        btnCreate.setBounds(0, 0, 70, 20);
        btnCreate.setEnabled(true);
        //contentPane.add(btnCreate);
        panel.add(btnCreate);
        
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(0, 20, 70, 20);
        btnDelete.setEnabled(true);
        panel.add(btnDelete);
        
        btnConnect = new JButton("Connect");
        btnConnect.setBounds(0, 40, 70, 20);
        btnConnect.setEnabled(true);
        panel.add(btnConnect);
        
        buttonController = new ButtonController(this, 
        		this.mainState);
        btnCreate.addActionListener(buttonController);
        btnDelete.addActionListener(buttonController);
        btnConnect.addActionListener(buttonController);
        
        mouseInputController = new MouseInputController(this, this.mainState);
        this.addMouseInputController(mouseInputController);
	}
	
	public void update(){
		for(NodeView nv: this.nodeViews){
			if(nv.getNode().getRelavantCounterMeasures() != null &&
					nv.getNode().getRelavantCounterMeasures().size() > 0){
				System.out.println(nv.getNode().getAttackNodeName() + "uu" + nv.getNode().getRelavantCounterMeasures().size());
				nv.getNode().setType(NodeType.LEAF);
			}
			else{
				System.out.println(nv.getNode().getAttackNodeName());
				nv.getNode().setType(NodeType.OR);
			}
			nv.updateColor();
		}
	}
	
	public RelevantCounterMeasureView getRelevantCounterMeasureView(){
		return this.relevantCounterMeasureView;
	}
	
	public ExploreView getExploreView(){
		return this.exploreArea;
	}
	
	public CounterMeasureView getCounterMeasureView(){
		return this.counterMeasureView;
	}
	
	public ResultView getResultView(){
		return this.resultView;
	}
	
	public JButton getCreateButton(){
		return this.btnCreate;
	}
	
	public JButton getDeleteButton(){
		return this.btnDelete;
	}
	
	public JButton getConnectButton(){
		return this.btnConnect;
	}
	
	public JButton getCalculateButton(){
		return this.btnCalculate;
	}
	
	public MouseInputController getMouseInputController(){
		return this.mouseInputController;
	}
	
	public void addLabelOf(NodeView view){
		this.panel.add(view.label);
		refresh();
	}
	
	public void removeLabelOf(NodeView view){
		this.panel.remove(view.label);
		refresh();
	}
	
	public Collection<NodeView> getNodeViews(){
		return this.nodeViews;
	}
	
	public void addNodeView(NodeView nodeView){
		this.nodeViews.add(nodeView);
	}
	
	public void removeNodeView(NodeView nodeView){
		this.nodeViews.remove(nodeView);
	}
	
	public Collection<LineView> getLineViews(){
		return this.lineViews;
	}
	
	public void addLineView(LineView lineView){
		this.lineViews.add(lineView);
	}
	
	public void removeLineView(LineView lineView){
		this.lineViews.remove(lineView);
	}
	
	public void resetLineViews(Collection<LineView> Views){
		this.lineViews = new ArrayList<LineView>();
		this.lineViews.addAll(Views);
		refresh();
	}
	
	public void refresh(){
		revalidate();
		repaint();
	}
	
	public void addMouseInputController(MouseAdapter controller){
		this.panel.addMouseListener(controller);
		this.panel.addMouseMotionListener(controller);
	}
	
	@Override
	public void paint(Graphics g){
		/*if(!(this.parentNodeView != null && this.childNodeView != null)){
			super.paint(g);
			g.drawLine(0,0,0,0);
		}*/
		if(this.lineViews == null || this.lineViews.size() == 0){
			super.paint(g);
			g.drawLine(0,0,0,0);
		}
		else{
			super.paint(g);
			for(LineView lineView: this.lineViews){
				g.drawLine(lineView.getParentNodeView().getPosition().getX() + lineView.getParentNodeView().width / 2,
						lineView.getParentNodeView().getPosition().getY() + 2 * lineView.getParentNodeView().height + 3,
						lineView.getChildNodeView().getPosition().getX() + lineView.getChildNodeView().width / 2, 
						lineView.getChildNodeView().getPosition().getY() + lineView.getChildNodeView().height + 3);
			}		
		}
	}
	
	public NodeView getParentNodeView(){
		return this.parentNodeView;
	}
	
	public NodeView getChildNodeView(){
		return this.childNodeView;
	}

	public void setParentNodeView(NodeView parentNodeView) {
		this.parentNodeView = parentNodeView;
	}

	public void setChildNodeView(NodeView childNodeView) {
		this.childNodeView = childNodeView;
	}
	
	public void restore(Memento memento){
		this.nodeViews = new ArrayList<NodeView>();
		//this.lineViews = new ArrayList<LineView>();
		for(NodeView nodeView : memento.getNodeViews()){
			nodeViews.add(nodeView);
		}
		//for(LineView lineView : memento.getLineViews()){
			//lineViews.add(lineView);
		//}
		this.refresh();
	}
}
