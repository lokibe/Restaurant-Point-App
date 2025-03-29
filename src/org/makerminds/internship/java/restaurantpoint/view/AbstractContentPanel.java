package org.makerminds.internship.java.restaurantpoint.view;

import javax.swing.JPanel;

public abstract class AbstractContentPanel implements IView{
	
	public JPanel createBaseContentPanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 0, 700, 500);
		return contentPanel;
	}
	
	public abstract JPanel prepareView();
}
