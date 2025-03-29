package org.makerminds.internship.java.restaurantpoint.view.admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.makerminds.internship.java.restaurantpoint.controller.RestaurantManagementController;
import org.makerminds.internship.java.restaurantpoint.dataProvider.DataProvider;
import org.makerminds.internship.java.restaurantpoint.model.Restaurant;
import org.makerminds.internship.java.restaurantpoint.view.AbstractContentPanel;

public class RestaurantManager extends AbstractContentPanel{

	private JTextField restaurantNameTextField;
	private JTextField restaurantAddressTextField;
	private DefaultTableModel restaurantTableModel;
	private JTable restaurantTable;
	private String[] newRestaurantDataArray;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestaurantManager window = new RestaurantManager();
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
	public RestaurantManager() {
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
		frame.add(createMenuManagementPanel());
	}

	public JPanel createMenuManagementPanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 0, 700, 420);
		menuInputDataPanel(contentPanel);
		createButtons(contentPanel);
		createTable(contentPanel);
		return contentPanel;
	}

	private void menuInputDataPanel(JPanel contentPanel) {

		JLabel restaurantNameTextFieldLabel = createLabel(40, "Restaurant name");
		JLabel restaurantAddressTextFieldLabel = createLabel(100, "Restaurant address");
		contentPanel.add(restaurantNameTextFieldLabel);
		contentPanel.add(restaurantAddressTextFieldLabel);

		restaurantNameTextField = createTextField(60);
		restaurantAddressTextField = createTextField(120);

		contentPanel.add(restaurantNameTextField);
		contentPanel.add(restaurantAddressTextField);
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
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] restaurantDataArray = new String[2];
				restaurantDataArray[0] = restaurantNameTextField.getText();
				restaurantDataArray[1] = restaurantAddressTextField.getText();
				if (areDataValid(restaurantDataArray)) {
					restaurantTableModel.addRow(restaurantDataArray);
					DataProvider restaurantProvider = new DataProvider();
					RestaurantManagementController.addRestaurant(restaurantDataArray,
							restaurantProvider.getRestaurantList());
					clearTextField();
				} else {
					JOptionPane.showMessageDialog(null, "Provide the mandatory data!");
				}
			}
		});
		return addButton;
	}

	private JButton createUpdateButton() {
		JButton updateButton = new JButton("Update");
		updateButton.setBackground(Color.blue);
		updateButton.setForeground(Color.white);
		updateButton.setBounds(140, 300, 100, 30);
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRowIndex = restaurantTable.getSelectedRow();
				if (selectedRowIndex != -1) {
					String[] oldRestaurantDataArray = new String[2];
					oldRestaurantDataArray[0] = restaurantTable.getValueAt(selectedRowIndex, 0).toString();
					oldRestaurantDataArray[1] = restaurantTable.getValueAt(selectedRowIndex, 1).toString();

					newRestaurantDataArray[0] = restaurantNameTextField.getText();
					newRestaurantDataArray[1] = restaurantAddressTextField.getText();
					if (areDataValid(newRestaurantDataArray)) {
						restaurantTableModel.removeRow(selectedRowIndex);
						restaurantTableModel.addRow(newRestaurantDataArray);
						DataProvider restaurantProvider = new DataProvider();
						RestaurantManagementController.updateRestaurant(oldRestaurantDataArray, newRestaurantDataArray,
								restaurantProvider.getRestaurantList());
						clearTextField();
					} else {
						JOptionPane.showMessageDialog(null, "Please provide the mandatory data!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to update!");
				}
			}
		});
		return updateButton;
	}

	private JButton createDeleteButton() {
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.blue);
		deleteButton.setForeground(Color.white);
		deleteButton.setBounds(250, 300, 100, 30);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = restaurantTable.getSelectedRow();
				if (selectedRowIndex != -1) {
					String[] restaurantDataArray = new String[2];
					restaurantDataArray[0] = restaurantTable.getValueAt(selectedRowIndex, 0).toString();
					restaurantDataArray[1] = restaurantTable.getValueAt(selectedRowIndex, 1).toString();

					restaurantTableModel.removeRow(selectedRowIndex);
					DataProvider restaurantProvider = new DataProvider();
					RestaurantManagementController.deleteRestaurant(restaurantDataArray,
							restaurantProvider.getRestaurantList());
					clearTextField();
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to delete!");
				}
			}
		});
		return deleteButton;
	}

	private void createTable(JPanel contentPanel) {
		restaurantTable = new JTable();
		createRestaurantTableModel();
		restaurantTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRowIndex = restaurantTable.getSelectedRow();
				restaurantNameTextField.setText(restaurantTable.getValueAt(selectedRowIndex, 0).toString());
				restaurantAddressTextField.setText(restaurantTable.getValueAt(selectedRowIndex, 1).toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane(restaurantTable);
		scrollPane.setBounds(380, 30, 280, 150);
		contentPanel.add(scrollPane);
	}

	private void createRestaurantTableModel() {
		restaurantTableModel = new DefaultTableModel();
		String[] columnName = { "Name", "Address" };
		restaurantTableModel.setColumnIdentifiers(columnName);
		createRestaurantDataTable();
	}

	private void createRestaurantDataTable() {
		DataProvider dataProvider = new DataProvider();
		List<Restaurant> restaurantList = dataProvider.getRestaurantList();
		newRestaurantDataArray = new String[2];
		for (Restaurant restaurant : restaurantList) {
			newRestaurantDataArray[0] = restaurant.getName();
			newRestaurantDataArray[1] = restaurant.getAddress();
			restaurantTableModel.addRow(newRestaurantDataArray);
		}
		restaurantTable.setModel(restaurantTableModel);
	}

	private boolean areDataValid(String[] restaurantDataArray) {
		return !restaurantDataArray[0].isBlank() && !restaurantDataArray[1].isBlank();
	}

	private void clearTextField() {
		restaurantNameTextField.setText("");
		restaurantAddressTextField.setText("");
	}

	@Override
	public JPanel prepareView() {
		// TODO Auto-generated method stub
		return null;
	}
}