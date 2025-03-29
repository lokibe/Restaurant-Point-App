package org.makerminds.internship.java.restaurantpoint.view.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.makerminds.internship.java.restaurantpoint.controller.MenuManagementController;
import org.makerminds.internship.java.restaurantpoint.dataProvider.DataProvider;
import org.makerminds.internship.java.restaurantpoint.model.Menu;
import org.makerminds.internship.java.restaurantpoint.model.Restaurant;
import org.makerminds.internship.java.restaurantpoint.view.AbstractContentPanel;

public class MenuManager extends AbstractContentPanel{

	private JTextField menuNameTextField;
	
	private JComboBox<Restaurant> restaurantSelector;
	private DefaultTableModel menuTableModel;
	private JTable menuTable;
	private String[] newMenuDataArray;
	
	private DataProvider dataProvider = new DataProvider();


	
	public JPanel prepareView() {
		JPanel contentPanel = createBaseContentPanel();
		menuInputDataPanel(contentPanel);
		createButtons(contentPanel);
		contentPanel.add(createComboBoxPanel());
		createMenuTable(contentPanel);
		return contentPanel;
	}

	private void menuInputDataPanel(JPanel contentPanel) {

		JLabel menuNameTextFieldLabel = createLabel(40, "Menu name");

		contentPanel.add(menuNameTextFieldLabel);

		menuNameTextField = createTextField(60);

		contentPanel.add(menuNameTextField);
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
				String[] menuDataArray = new String[1];
				menuDataArray[0] = menuNameTextField.getText();
				if (areDataValid(menuDataArray)) {
					menuTableModel.addRow(menuDataArray);
					MenuManagementController.addMenu(menuDataArray, (Restaurant) restaurantSelector.getSelectedItem());
					clearTextField();
				} else {
					JOptionPane.showMessageDialog(null, "Please provide the mandatory data!");
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
				int selectedRowIndex = menuTable.getSelectedRow();
				if (selectedRowIndex != -1) {
					String[] oldMenuDataArray = new String[1];
					oldMenuDataArray[0] = menuTable.getValueAt(selectedRowIndex, 0).toString();

					newMenuDataArray[0] = menuNameTextField.getText();
					if (areDataValid(newMenuDataArray)) {
						menuTableModel.removeRow(selectedRowIndex);
						menuTableModel.addRow(newMenuDataArray);
						MenuManagementController.updateMenu(oldMenuDataArray, newMenuDataArray, (Restaurant) restaurantSelector.getSelectedItem());
						clearTextField();
					} else {
						JOptionPane.showMessageDialog(null, "Please provide the mandatory data!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Please select any row to update!");
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
				int selectedRowIndex = menuTable.getSelectedRow();
				if (selectedRowIndex != -1) {
					String[] menuDataArray = new String[1];
					menuDataArray[0] = menuTable.getValueAt(selectedRowIndex, 0).toString();

					menuTableModel.removeRow(selectedRowIndex);
					MenuManagementController.deleteMenu(menuDataArray,
							(Restaurant) restaurantSelector.getSelectedItem());
					clearTextField();
				} else {
					JOptionPane.showMessageDialog(null, "Please select any row to delete!");
				}
			}
		});
		return deleteButton;
	}

	private JPanel createComboBoxPanel() {
		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setLayout(null);
		comboBoxPanel.setBounds(350, 30, 200, 40);
		comboBoxPanel.add(createComboBox());
		return comboBoxPanel;
	}

	private JComboBox<Restaurant> createComboBox() {
		restaurantSelector = new JComboBox<Restaurant>();
		restaurantSelector.setBounds(0, 0, 200, 40);
		createRestaurantMenuComboBoxData();
		restaurantSelector.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				createMenuTableModel();
			}
		});
		return restaurantSelector;
	}

	private void createRestaurantMenuComboBoxData() {
		List<Restaurant> restaurantList = dataProvider.getRestaurantList();
		for (Restaurant restaurant : restaurantList) {
			restaurantSelector.addItem(restaurant);
		}
	}

	private void createMenuTable(JPanel contentPanel) {
		menuTable = new JTable();
		createMenuTableModel();
		menuTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRowIndex = menuTable.getSelectedRow();
				menuNameTextField.setText(menuTable.getValueAt(selectedRowIndex, 0).toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane(menuTable);
		scrollPane.setBounds(300, 120, 280, 150);
		contentPanel.add(scrollPane);
	}

	private void createMenuTableModel() {
		menuTableModel = new DefaultTableModel();
		String[] columnName = { "Name" };
		menuTableModel.setColumnIdentifiers(columnName);
		createMenuDataTable();
	}

	private void createMenuDataTable() {
		Restaurant selectedRestaurant = (Restaurant) restaurantSelector.getSelectedItem();
		List<Menu> menuList = selectedRestaurant.getMenuList();
		newMenuDataArray = new String[1];
		for (Menu menu : menuList) {
			newMenuDataArray[0] = menu.getName();
			menuTableModel.addRow(newMenuDataArray);
		}
		menuTable.setModel(menuTableModel);
	}

	private boolean areDataValid(String[] menuDataArray) {
		return !menuDataArray[0].isBlank();
	}

	private void clearTextField() {
		menuNameTextField.setText("");
	}
}