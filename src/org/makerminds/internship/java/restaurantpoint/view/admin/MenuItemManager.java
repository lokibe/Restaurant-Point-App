package org.makerminds.internship.java.restaurantpoint.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.makerminds.internship.java.restaurantpoint.controller.MenuItemManagementController;
import org.makerminds.internship.java.restaurantpoint.controller.MenuManagementController;
import org.makerminds.internship.java.restaurantpoint.dataProvider.DataProvider;
import org.makerminds.internship.java.restaurantpoint.model.Item;
import org.makerminds.internship.java.restaurantpoint.model.Menu;
import org.makerminds.internship.java.restaurantpoint.model.Restaurant;
import org.makerminds.internship.java.restaurantpoint.view.AbstractContentPanel;

public class MenuItemManager extends AbstractContentPanel{

	private DataProvider dataProvider = new DataProvider();
	private JTextField menuItemIdTextField;
	private JTextField menuItemNameTextField;
	private JTextField menuItemPriceTextField;

	private JComboBox<Restaurant> restaurantSelector;
	private JComboBox<Menu> menuSelector;

	private JRadioButton mealRadioButton;
	private JRadioButton drinkRadioButton;

	private DefaultTableModel menuItemTableModel;
	private JTable menuItemTable;
	private String[] menuItemDataArray;
	public JPanel prepareView() {
		JPanel menuItemManagerContentPanel = createBaseContentPanel();
		createInputComponentsPanel(menuItemManagerContentPanel);
		menuItemManagerContentPanel.add(createRestaurantSelectorPanel());
		createMenuSelectorPanel(menuItemManagerContentPanel);
		prepareMenuItemTableScrollPane(menuItemManagerContentPanel);
		return menuItemManagerContentPanel;
	}

	private void createInputComponentsPanel(JPanel contentPanel) {
		createMenuItemIdTextField();
		createMenuItemNameTextField();
		createMenuItemPriceTextField();
		
		contentPanel.add(menuItemIdTextField);
		contentPanel.add(menuItemNameTextField);
		contentPanel.add(menuItemPriceTextField);

		contentPanel.add(createMenuItemIdJLabel());
		contentPanel.add(createMenuItemNameJLabel());
		contentPanel.add(createMenuItemPriceJLabel());
		
		createMenuItemCategoryRadioButtons(contentPanel);
		prepareMenuItemMangementButtons(contentPanel);
	}

	private void createMenuItemIdTextField() {
		menuItemIdTextField = new JTextField();
		menuItemIdTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		menuItemIdTextField.setBounds(10, 35, 200, 30);
	}

	private void createMenuItemNameTextField() {
		menuItemNameTextField = new JTextField();
		menuItemNameTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		menuItemNameTextField.setBounds(10, 95, 200, 30);
	}

	private void createMenuItemPriceTextField() {
		menuItemPriceTextField = new JTextField();
		menuItemPriceTextField.setFont(new Font("Arial", Font.PLAIN, 13));
		menuItemPriceTextField.setBounds(10, 155, 200, 30);
	}
	

	private JLabel createMenuItemIdJLabel() {
		JLabel menuItemIdLabel = new JLabel("Menu Item Id");
		menuItemIdLabel.setBounds(10, 10, 200, 21);
		return menuItemIdLabel;
	}

	private JLabel createMenuItemNameJLabel() {
		JLabel menuItemNameLabel = new JLabel("Menu Item name");
		menuItemNameLabel.setBounds(10, 70, 200, 21);
		return menuItemNameLabel;
	}

	private JLabel createMenuItemPriceJLabel() {
		JLabel menuItemPriceLabel = new JLabel("Menu Item price");
		menuItemPriceLabel.setBounds(10, 130, 200, 21);
		return menuItemPriceLabel;
	}
	
	private void createMenuItemCategoryRadioButtons(JPanel contentPanel) {
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(null);
		radioButtonPanel.setBounds(10, 195, 200, 120);
		
		mealRadioButton = new JRadioButton("Meal");
		mealRadioButton.setBounds(10, 10, 100, 30);
		mealRadioButton.setSelected(true);
		
		drinkRadioButton = new JRadioButton("Drink");
		drinkRadioButton.setBounds(10, 50, 100, 30);
		
		ButtonGroup createItemCategoryRadioButtonGroup = new ButtonGroup();
		createItemCategoryRadioButtonGroup.add(mealRadioButton);
		createItemCategoryRadioButtonGroup.add(drinkRadioButton);
		radioButtonPanel.add(mealRadioButton);
		radioButtonPanel.add(drinkRadioButton);
		
		contentPanel.add(radioButtonPanel);
	}
	
	private void prepareMenuItemMangementButtons(JPanel menuItemManagementPanel) {
		JButton addButton = createAddButton();
		JButton updateButton = createUpdateButton();
		JButton deleteButton = createDeleteButton();
		menuItemManagementPanel.add(addButton);
		menuItemManagementPanel.add(updateButton);
		menuItemManagementPanel.add(deleteButton);
	}
	
	private JButton createAddButton() {
		JButton addButton = new JButton("Add");
		addButton.setBackground(Color.blue);
		addButton.setForeground(Color.white);
		addButton.setBounds(10, 315, 100, 30);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] menuItemDataArray = new String[3];
				menuItemDataArray[0] = menuItemIdTextField.getText();
				menuItemDataArray[1] = menuItemNameTextField.getText();
				menuItemDataArray[2] = menuItemPriceTextField.getText();
				
				
				if (areDataValid(menuItemDataArray)) {
					menuItemTableModel.addRow(menuItemDataArray);
					MenuItemManagementController.addItem(menuItemDataArray, (Menu) menuSelector.getSelectedItem());
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
		updateButton.setBounds(120, 315, 100, 30);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = menuItemTable.getSelectedRow();
				if(selectedRow != -1) {
					int oldItemId = Integer.valueOf((String) menuItemTable.getValueAt(selectedRow, 0));
					String[] newItemData = new String[4];
					newItemData[0] = menuItemIdTextField.getText();
					newItemData[1] = menuItemNameTextField.getText();
					newItemData[2] = menuItemPriceTextField.getText();
					if(mealRadioButton.isSelected()) {
						newItemData[3] = "MEAL";
					}else {
						newItemData[3] = "DRINK";
					}
					MenuItemManagementController.updateItem(oldItemId, menuItemDataArray,(Menu) menuSelector.getSelectedItem());
					menuItemTableModel.setValueAt(newItemData[0], selectedRow, 0);
					menuItemTableModel.setValueAt(newItemData[1], selectedRow, 1);
					menuItemTableModel.setValueAt(newItemData[2], selectedRow, 2);

				}
			}
		});
		return updateButton;
	}	

	private JButton createDeleteButton() {
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.blue);
		deleteButton.setForeground(Color.white);
		deleteButton.setBounds(230, 315, 100, 30);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = menuItemTable.getSelectedRow();
				if (selectedRowIndex != -1) {
					String[] menuItemDataArray = new String[4];
					menuItemDataArray[0] = menuItemTable.getValueAt(selectedRowIndex, 0).toString();
					menuItemDataArray[1] = menuItemTable.getValueAt(selectedRowIndex, 1).toString();
					menuItemDataArray[2] = menuItemTable.getValueAt(selectedRowIndex, 2).toString();
					menuItemTableModel.removeRow(selectedRowIndex);
					MenuManagementController.deleteMenu(menuItemDataArray,
							(Restaurant) restaurantSelector.getSelectedItem());
					clearTextField();
				} else {
					JOptionPane.showMessageDialog(null, "Please select any row to delete!");
				}
			}
		});
		return deleteButton;
	}
	
	private JPanel createRestaurantSelectorPanel() {
		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setLayout(null);
		comboBoxPanel.setBounds(350, 10, 317, 40);
		createRestaurantComboBox();
		
		comboBoxPanel.add(restaurantSelector);
		return comboBoxPanel;
	}
	
	private void createRestaurantComboBox() {
		restaurantSelector = new JComboBox<Restaurant>();
		restaurantSelector.setBounds(0, 0, 315, 40);
		restaurantSelector.setBackground(Color.WHITE);
		prepareRestaurantComboBoxData();
		restaurantSelector.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent event) {
				menuItemTableModel.setRowCount(0);
				prepareMenuComboBoxData();
			}
		});
	}
	
	private void prepareRestaurantComboBoxData() {
		for(Restaurant restaurant: dataProvider.getRestaurantList()) {
			restaurantSelector.addItem(restaurant);
		}
	}
	
	private void createMenuSelectorPanel(JPanel containerPanel) {
		JPanel menuSelectorPanel = new JPanel();
		menuSelectorPanel.setBackground(Color.WHITE);
		menuSelectorPanel.setBounds(350, 89, 315, 40);
		menuSelectorPanel.setLayout(new BoxLayout(menuSelectorPanel, BoxLayout.Y_AXIS));
		
		createMenuComboBox();
		
		menuSelectorPanel.add(menuSelector);
		containerPanel.add(menuSelectorPanel);
	}
	
	private void createMenuComboBox() {
		menuSelector = new JComboBox<Menu>();
		menuSelector.setBackground(Color.WHITE);
		prepareMenuComboBoxData();
		menuSelector.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent event) {
						if(event.getStateChange() == ItemEvent.SELECTED) {
							menuItemTableModel.setRowCount(0);
							Menu selectedMenu = (Menu) menuSelector.getSelectedItem();
							prepareMenuItemTableData(selectedMenu);
						}
					}
				});
	}
	
	private void prepareMenuComboBoxData() {
		menuSelector.removeAllItems();
		Restaurant selectedRestaurant = (Restaurant) restaurantSelector.getSelectedItem();
		if(selectedRestaurant == null) {
			List<Restaurant> restaurantList = dataProvider.getRestaurantList();
			selectedRestaurant = restaurantList.get(0);
		}
		
		for(Menu menu : selectedRestaurant.getMenuList()) {
			menuSelector.addItem(menu);
		}
	}
	
	private void prepareMenuItemTableScrollPane(JPanel contentPanel) {
		prepareMenuItemTable();
		JScrollPane scrollPane = new JScrollPane(menuItemTable);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(350, 150, 315, 200);
		contentPanel.add(scrollPane);
	}
	
	private void prepareMenuItemTable() {
		menuItemTable = new JTable();
		prepareMenuItemTableDataModel();
		menuItemTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				int selectedRow = menuItemTable.getSelectedRow();
				menuItemIdTextField.setText(menuItemTable.getValueAt(selectedRow, 0).toString());
				menuItemNameTextField.setText(menuItemTable.getValueAt(selectedRow, 1).toString());
				menuItemPriceTextField.setText(menuItemTable.getValueAt(selectedRow, 2).toString());
			}
		});
	}
	
	private void prepareMenuItemTableDataModel() {
		menuItemTableModel = new DefaultTableModel();
		String[] columnName = {"Id", "Name", "Price", "Category"};
		menuItemTableModel.setColumnIdentifiers(columnName);
		Menu selectedMenu = (Menu) menuSelector.getSelectedItem();
		prepareMenuItemTableData(selectedMenu);
	}

	private void prepareMenuItemTableData(Menu selectedMenu) {
		menuItemDataArray = new String[4];
		List<Item> itemList = selectedMenu.getItemList();
		for(Item item : itemList) {
			menuItemDataArray[0] = String.valueOf(item.getId());
			menuItemDataArray[1] = item.getName();
			menuItemDataArray[2] = String.valueOf(item.getPrice());
			menuItemDataArray[3] = item.getItemCategory().toString();
			menuItemTableModel.addRow(menuItemDataArray);
		}
		menuItemTable.setModel(menuItemTableModel);
	}
	
	private boolean areDataValid(String[] menuItemDataArray) {
		return !menuItemDataArray[0].isBlank();
	}

	private void clearTextField() {
		menuItemIdTextField.setText("");
		menuItemNameTextField.setText("");
		menuItemPriceTextField.setText("");
	}
}
