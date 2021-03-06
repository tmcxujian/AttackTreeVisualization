package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import common.Constants;
import controllers.DictionaryParserController;
import models.CounterMeasure;
import models.MainState;
/**
 * This is explore window to keep track more information of countermeasure
 * @author xujian
 *
 */
public class ExploreView extends JFrame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2249568077165903847L;

	public JPanel contentPane;
	private MainView mainView;
	private MainState mainState;
	private JTextField textField;
	private JTextArea textArea;
    private JTable table;
    private JScrollPane jScrollPane;
    private Object[][] cellData;
    private JLabel label;
    
    Collection<CounterMeasure> counterMeasures;
    private DictionaryParserController dictionaryParserController;
    
    /**
     * Construct for ExploreView
     * @param mainView
     * @param mainState
     */
    public ExploreView(MainView mainView, MainState mainState){
    		this.mainView = mainView;
    		this.mainState = mainState;
    		
    		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 278, 302);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 284, 253);
        
        label = new JLabel("All Detailed CounterMeasure");
        label.setBounds(22, 0, 284, 20);
        contentPane.add(label,BorderLayout.PAGE_START);
        
        textArea = new JTextArea(50,3);
        textArea.setBounds(22, 21, 225, 50);
        contentPane.add(textArea);
        textArea.setColumns(10);
        textArea.setLineWrap(true);

        //Initialize the JTable
        table = getTable();       
        table.setBorder(new LineBorder(Color.BLACK));
        table.setBackground(Color.LIGHT_GRAY);
        table.setBounds(14, 172, 376, 67);
        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(105);
        table.getColumnModel().getColumn(3).setPreferredWidth(105);

        jScrollPane = new JScrollPane();
        jScrollPane.setBounds(20, 75, 233, 268);
        contentPane.add(jScrollPane);
        jScrollPane.setViewportView(table);
        //Add listener for every column
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    table = (JTable) e.getSource();
                    int row = table.getSelectedRow();
                    if (row > -1) {
                        String s = (String) table.getValueAt(row, 3);
                        updateTextArea(s);
                    }
                } 
            }
        });
    }
    
    //Real JTable Initialization 
    private JTable getTable() {
    		this.counterMeasures = new ArrayList<CounterMeasure>();
    		this.dictionaryParserController = new DictionaryParserController(Constants.COUNTERMEASURE_STORAGE);
        dictionaryParserController.xmlParser();
        this.counterMeasures = this.dictionaryParserController.getCounterMeasures();
    		String[] columnNames = {"No.", "CounterMeasure Type", "Sub Type", "Value"};
        
        cellData = new String[this.counterMeasures.size() + 10][4];
        int i = 0;
        for (CounterMeasure cm : this.counterMeasures){
        		cellData[i][0] = String.valueOf(i + 1);
            cellData[i][1] = cm.getGeneralType();
            cellData[i][2] = cm.getSubType();
            cellData[i][3] = cm.getValue();
            i++;
        }
       
        DefaultTableModel model = new DefaultTableModel(cellData, columnNames) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }

        };
        return new JTable(model) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return getPreferredSize();
            }
        };
    }
    
    public void updateTextArea(String s){
    		this.textArea.setText(s);
    		this.refresh();
    }
    
    //refresh all the component
    public void refresh() {
        revalidate();
        repaint();
        table.updateUI();
    }   
    
    public DictionaryParserController getDictionaryParserController(){
    		return this.dictionaryParserController;
    }
    
    public JTable getJTable(){
    		return this.table;
    }
}
