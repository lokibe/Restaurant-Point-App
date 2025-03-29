package org.makerminds.internship.java.restaurantpoint.view.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.makerminds.internship.java.restaurantpoint.dataProvider.DataProvider;
import org.makerminds.internship.java.restaurantpoint.model.Restaurant;
import org.makerminds.internship.java.restaurantpoint.model.Table;
import org.makerminds.internship.java.restaurantpoint.view.AbstractContentPanel;

public class TableManager extends AbstractContentPanel{

	private JTextField tablesIdTextField;
	private JTextField tablesSeatsTextField;
	
	private JComboBox<Restaurant> restaurantSelector;
	private DefaultTableModel tablesTableModel;
	private JTable tablesTable;
	private String[] tablesDataArray;
	
	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableManager window = new TableManager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TableManager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 420);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(createTablesManagementPanel());
	}
	
	public JPanel createTablesManagementPanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 0, 700, 420);
		tablesInputDataPanel(contentPanel);
		createButtons(contentPanel);
		contentPanel.add(createComboBoxPanel());
		createTable(contentPanel);
		return contentPanel;
	}
	
	private void tablesInputDataPanel(JPanel contentPanel) {
		JLabel tablesIdTextFieldLabel = createLabel(20, "Table Id");
		JLabel tablesSeatsTextFieldLabel = createLabel(80, "Seats");
		contentPanel.add(tablesIdTextFieldLabel);
		contentPanel.add(tablesSeatsTextFieldLabel);
		
		tablesIdTextField = createTextField(40);
		tablesSeatsTextField = createTextField(100);
		contentPanel.add(tablesIdTextField);
		contentPanel.add(tablesSeatsTextField);
	}
	
	private JTextField createTextField(int verticalPosition) {
		JTextField textField = new JTextField();
		textField.setBounds(20, verticalPosition, 200, 30);
		return textField;
	}

	private JLabel createLabel(int verticalPosition, String message) {
		JLabel label = new JLabel(message);
		label.setBounds(20, verticalPosition, 200, 20);
		return label;
	}
	
	private void createButtons(JPanel contentPanel) {
		contentPanel.add(createAddButton());
		contentPanel.add(createUpdateButton());
		contentPanel.add(createDeleteButton());
	}
	
	private JButton createAddButton() {
		JButton addButton = new JButton("Add");
		addButton.setBackground(Color.blue);
		addButton.setForeground(Color.white);
		addButton.setBounds(30, 300, 100, 30);
		return addButton;
	}
	
	private JButton createUpdateButton() {
		JButton updateButton = new JButton("Update");
		updateButton.setBackground(Color.blue);
		updateButton.setForeground(Color.white);
		updateButton.setBounds(140, 300, 100, 30);
		return updateButton;
	}
	
	private JButton createDeleteButton() {
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.blue);
		deleteButton.setForeground(Color.white);
		deleteButton.setBounds(250, 300, 100, 30);
		return deleteButton;
	}
	
	private JPanel createComboBoxPanel() {
		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setLayout(null);
		comboBoxPanel.setBounds(350, 30, 200, 40);
		comboBoxPanel.add(createComboBox());
		return comboBoxPanel;
	}
	
	private JComboBox<Restaurant> createComboBox(){
		restaurantSelector = new JComboBox<Restaurant>();
		restaurantSelector.setBounds(0, 0, 200, 40);
		prepareComboBoxData();
		return restaurantSelector;
	} 
	
	private void prepareComboBoxData() {
		DataProvider dataProvider = new DataProvider();
		List<Restaurant> restaurantList = dataProvider.getRestaurantList();
		for(Restaurant restaurant : restaurantList) {
			restaurantSelector.addItem(restaurant);
		}
	}
	
	private void createTable(JPanel contentPanel) {
		tablesTable = new JTable();
		createTablesTableModel();
		JScrollPane scrollPane = new JScrollPane(tablesTable);
		scrollPane.setBounds(300, 120, 280, 150);
		contentPanel.add(scrollPane);
	}
	
	private void createTablesTableModel() {
		tablesTableModel = new DefaultTableModel();
		String[] columnName = {"Table id", "Seats"};
		tablesTableModel.setColumnIdentifiers(columnName);
		createTablesDataTable();
	}
	
	private void createTablesDataTable() {
		Restaurant selectedRestaurant = (Restaurant) restaurantSelector.getSelectedItem();
		List<Table> tableList = selectedRestaurant.getTableList();
		tablesDataArray = new String[2];
		for(Table table : tableList) {
			tablesDataArray[0] = String.valueOf(table.getId());
			tablesDataArray[1] = String.valueOf(table.getNumberOfSeats());
			tablesTableModel.addRow(tablesDataArray);
		}
		tablesTable.setModel(tablesTableModel);
	}

	@Override
	public JPanel prepareView() {
		// TODO Auto-generated method stub
		return null;
	}
}
