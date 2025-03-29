package org.makerminds.internship.java.restaurantpoint.controller;

import java.util.Iterator;
import java.util.List;

import org.makerminds.internship.java.restaurantpoint.model.Restaurant;
import org.makerminds.internship.java.restaurantpoint.model.Table;

public class TableManagementController {

	private static Table createTable(int[] tableDataArray) {
		Table table = new Table(tableDataArray[0], tableDataArray[1]);
		return table;
	}
	
	public static void addTable(int[] tableDataArray, Restaurant selectedRestaurant) {
		Table table = createTable(tableDataArray);
		selectedRestaurant.getTableList().add(table);
	}
	
	public static void deleteTable(int[] tableDataArray, Restaurant selectedRestaurant) {
		Table tableToRemove = createTable(tableDataArray);
		List<Table> tableList = selectedRestaurant.getTableList();
		Iterator<Table> iterator = tableList.iterator();
		while(iterator.hasNext()) {
			Table table = iterator.next();
			if(table.getId() == tableToRemove.getId()) {
				iterator.remove();
			}
		}
	}
	
	public static void updateTable(int oldTableId, int[] newTableData, Restaurant selectedRestaurant) {
		List<Table> tableList = selectedRestaurant.getTableList();
		for(Table table : tableList) {
			if(table.getId() == oldTableId) {
				table.setId(newTableData[0]);
				table.setNumberOfSeats(newTableData[1]);
			}
		}
		selectedRestaurant.setTableList(tableList);
	}
}
