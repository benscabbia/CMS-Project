import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

/**
 * CopierSystemGui is a class providing the full graphical implementation for the Copier system. 
 * The GUI allows the client to add a new plain copier which is then added to the main 
 * CopierSystemImplementation. Furthermore, the copier will also be dynamically displayed
 * in the Existing copier list, where the client will also be able to remove the selected copier.
 * 
 * @author Benjamin Scabbia
 * @version Task4
 */
public class CopierSystemGui extends JFrame {
	/**Create an instance of CopierSystemImplemenetation */
	CopierSystemImplementation c = new CopierSystemImplementation();
	//Setup a list model to contain Copier ID's
	DefaultListModel copiersModel = new DefaultListModel();
	/**
     * Default Constructor for CopierSystemGui class
     * The constructor generates an interface which allows the client to manage the list of copiers 
     * and allows plain copiers to be added to the system. 
     */
	public CopierSystemGui(){		
		super("Task 3");
		
		//create and set the Menu bar
		setJMenuBar(createMenuBar());
		
		//Main JPanel - All elements added to a panel to allow the use of borders
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		//Border setup for the mainPanel
		EmptyBorder emptyBorder = new EmptyBorder(4, 4, 4, 4);
        EtchedBorder etchedBorder = new EtchedBorder();
        Border innerBorder = new CompoundBorder(emptyBorder, etchedBorder);
        Border outerBorder = new CompoundBorder(innerBorder, emptyBorder);
        mainPanel.setBorder(outerBorder);
         
        //Adds a number of copiers to the system to demsotrate functionality
        addCopiersToSystem();
 		
		//Call the getCopierDisplayOuterPanel() which returns a JPanel and position it NORTH
		mainPanel.add(getCopierDisplayOuterPanel(), BorderLayout.NORTH);
				
		//create a label and set alignment and add it to the main panel position CENTER
		JLabel plainCLabel = new JLabel("New Plain Copier Parameters");
		plainCLabel.setHorizontalAlignment(JLabel.CENTER);
		mainPanel.add(plainCLabel, BorderLayout.CENTER);
			
		//Call the getPlainCopierPanel() which returns a JPanel and position it SOUTH
		mainPanel.add(getPlainCopierPanel(), BorderLayout.SOUTH);
		
		//add the main panel to the frame
		add(mainPanel);
				
		//methods providing system functionality and ensuring a sensible look
		setMinimumSize(new Dimension(200,390));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
     * Adds a number of copiers to the system to demonstrate functionality 
     */
	private void addCopiersToSystem(){
		c.addPlainCopier("PC01", 1, 50, 2000); 
 		c.addColourCopier("CC02", 1, 1300, 150);
 		c.addHQCopier("HC03", 10, 0.5, 2, 1.25, 150, 1200);
 		c.addHQCopier("HC04", 1, 1.5, 3, 1.25, 250, 1000);
	}
	
	/**
     * Create and returns a JMenuBar item 
     * @return returns a JMenuBar which can be then used by the main frame.  
     */
	private JMenuBar createMenuBar(){
		//create bar, menu and item
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		//Add action listener to the exit button
		exitItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		return menuBar;
	}
	
	/**
     * Create a JPanel to display the ID's of the current copiers. It also provides a button
     * which the client can use to remove a selected copier.
     * @return returns a JPanel which is used by the main frame.  
     */
	private JPanel getCopierDisplayOuterPanel(){
		JPanel copierDisplayPanel = new JPanel();
		//Get all copiers in the system
		String[] allCopiers = c.getAllCopiers();
		
		//Populate the model list with existing copiers
		for (int i = 0, n = allCopiers.length; i < n; i++) {
			 copiersModel.addElement(allCopiers[i]);
		}
		
		//A JList used to display the elements in copiersModel on the GUI
		JList displayList = new JList(copiersModel);
		//Set default sizes for the list
		displayList.setVisibleRowCount(6);
		displayList.setFixedCellWidth(380);
			
		//Create a Button to remove a copier
		JButton removeCopierButton = new JButton("Remove Copier");
		
		//Add an action listener to the button
		removeCopierButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//check that an element is selected from the list
				if(displayList.getSelectedValue() != null ){
					//get the values
					String id = displayList.getSelectedValue().toString();
					int index = displayList.getSelectedIndex();
					//Remove the copier from the model displayed by JList
				    copiersModel.removeElementAt(index); 
				    //Remove the copier from the actual allCopiers array
				    c.removeCopier(id);
				}
			}
		});
		
		//Add the components to the panel
		copierDisplayPanel.add(new JScrollPane(displayList));
		copierDisplayPanel.add(removeCopierButton);
			
		/*############### copierDisplayOuterPanel ###############*/
		JPanel copierDisplayOuterPanel = new JPanel();
		//set the layout 
		copierDisplayOuterPanel.setLayout(new BorderLayout());
		//create a label
		JLabel existingCopierLabel = new JLabel("Existing Copiers");
		//set the labels alignment 
		existingCopierLabel.setHorizontalAlignment(JLabel.CENTER);
			
		//Add the label to the NORTH
		copierDisplayOuterPanel.add(existingCopierLabel, BorderLayout.NORTH); 
		//Add the copierDisplayPanel in the CENTER (nesting)
		copierDisplayOuterPanel.add(copierDisplayPanel, BorderLayout.CENTER); 
		return copierDisplayOuterPanel;
	}
	
	/**
     * Create a JPanel which displays a simple input form to allow the client to add a new 
     * plain copier
     * @return returns a JPanel used by the main frame.  
     */
	private JPanel getPlainCopierPanel() {
		JPanel addPlainCopierPanel = new JPanel();
		//Set Layout to grid 
		addPlainCopierPanel.setLayout(new GridLayout(5,5,5,4));
		//create 4 text fields and a button
		JTextField idField, pageCostField, setupCostField, copySpeedField;
		JButton addButton;
		
		//add components to the panel
		addPlainCopierPanel.add(new JLabel("ID"));
		addPlainCopierPanel.add(idField = new JTextField());
		addPlainCopierPanel.add(new JLabel("Page Cost"));
		addPlainCopierPanel.add(pageCostField = new JTextField());
		addPlainCopierPanel.add(new JLabel("Setup Cost"));
		addPlainCopierPanel.add(setupCostField = new JTextField());
		addPlainCopierPanel.add(new JLabel("Copy Speed"));
		addPlainCopierPanel.add(copySpeedField = new JTextField());
		addPlainCopierPanel.add(new JLabel(""));
		addPlainCopierPanel.add(addButton = new JButton("Add"));				
		
		//add an action listener to the button
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//get data from textfields
				String id = idField.getText();
				String pageCostString = pageCostField.getText();
				String costSetupString = setupCostField.getText();
				String copySpeedString = copySpeedField.getText();
							
				try{
					//try and convert the data
					double pageCost = Double.parseDouble(pageCostString);
					double costSetup = Double.parseDouble(costSetupString);
					int copySpeed = Integer.parseInt(copySpeedString);
					
					//once data is parsed, add a new plain copier
					c.addPlainCopier(id, pageCost, costSetup, copySpeed);
					
					//add the id to the model, which will then display in the JList
					copiersModel.addElement(id);
					
					//clear the textfields
					idField.setText("");
					pageCostField.setText("");
					setupCostField.setText("");
					copySpeedField.setText("");
								
				}catch(NumberFormatException ex){
					//catch the exception and provide a message
					System.out.println("NumberFormatException - Please use valid values");
				}
			}
		});
		return addPlainCopierPanel;
	}
}

