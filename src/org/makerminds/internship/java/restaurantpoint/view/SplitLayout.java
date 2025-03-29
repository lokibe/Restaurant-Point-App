package org.makerminds.internship.java.restaurantpoint.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.makerminds.internship.java.restaurantpoint.controller.LoginController;
import org.makerminds.internship.java.restaurantpoint.dataProvider.DataProvider;
import org.makerminds.internship.java.restaurantpoint.model.User;
import org.makerminds.internship.java.restaurantpoint.model.UserFeature;
import org.makerminds.internship.java.restaurantpoint.model.UserRole;
import org.makerminds.internship.java.restaurantpoint.utils.AuthorizationService;
import org.makerminds.internship.java.restaurantpoint.utils.UserFeatureContentPanelResolver;
import org.makerminds.internship.java.restaurantpoint.utils.UserFeatureLabelResolver;

public class SplitLayout {

	private JFrame frame;
	private JLayeredPane layeredPane;
	private DataProvider dataProvider = new DataProvider();


	/**
	 * Create the application.
	 */
	public SplitLayout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 984, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);

		layeredPane = new JLayeredPane();
		layeredPane.setBounds(266, 0, 704, 582);

		// user role = Admin
		// List<String> featuresForAdmin = Arrays.asList("Restaurant Manager", "Menu
		// Manager", "Menu Item Manager", "Table Manager", "Sign-out");
		LoginController loginController = LoginController.getInstance();
		User user = loginController.getLoggedInUser();
		UserRole userRole = user.getUserRole();

		AuthorizationService authorizationService = new AuthorizationService();
		List<UserFeature> userFeatures = authorizationService.getUserFeatureByUserRole(userRole);

		// 1
		JPanel navigationBarPanel = createNavigationBarPanel(userFeatures);
		frame.getContentPane().add(navigationBarPanel);

		// 2
		// add content panel into layered pane
		layeredPane.add(createContentPanel(UserFeature.WELCOME));
		frame.getContentPane().add(layeredPane);
	}

	private JPanel createNavigationBarPanel(List<UserFeature> userFeatures) {
		JPanel navigationBarPanel = new JPanel();
		navigationBarPanel.setLayout(null);
		navigationBarPanel.setBounds(0, 0, 266, 582);
		TitledBorder navigationBarTitleBorder = BorderFactory.createTitledBorder("Navigation Bar");
		navigationBarPanel.setBorder(navigationBarTitleBorder);

		JLabel navigationBarLabel = new JLabel("Navigation Bar Buttons here");
		navigationBarPanel.add(navigationBarLabel);
		List<JPanel> navigationButtonList = createNavigationBarComponents(userFeatures);
		for (JPanel navigationBarButton : navigationButtonList) {
			navigationBarPanel.add(navigationBarButton);
		}
		return navigationBarPanel;
	}

	private List<JPanel> createNavigationBarComponents(List<UserFeature> userFeaturesList) {
		List<JPanel> buttonList = new ArrayList<>();
		JPanel navigationBarButtonPanel = null;

		int navigationBarButtonVerticalPosition = 10;
		int navigationBarButtonSpacing = 50;

		for (UserFeature userRoleFeature : userFeaturesList) {
			// create navigation bar buttons
			navigationBarButtonPanel = new JPanel();
			navigationBarButtonVerticalPosition += navigationBarButtonSpacing;
			navigationBarButtonPanel.setBounds(10, navigationBarButtonVerticalPosition, 230, 48);
			String userFeatureLabelText = UserFeatureLabelResolver.getUserFeatureLabelText(userRoleFeature);
			JLabel navigationBarButtonLabel = new JLabel(userFeatureLabelText);
			navigationBarButtonPanel.add(navigationBarButtonLabel);

			// get IView for navigation bar buttons
			UserFeatureContentPanelResolver userFeatureContentPanelResolver = new UserFeatureContentPanelResolver();
			IView iView = userFeatureContentPanelResolver.getUserFeatureIView(userRoleFeature); 
			buttonList.add(navigationBarButtonPanel);

			// prepareNavigationBarBUttonMouseListener
			prepareNavigationBarButtonMouseListener(navigationBarButtonPanel, iView);
		}

		return buttonList;
	}

	private JPanel createContentPanel(UserFeature contentPanelLabelText) {
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(0, 0, 704, 582);
		TitledBorder contentPanelTitleBorder = BorderFactory.createTitledBorder("Content Panel");
		contentPanel.setBorder(contentPanelTitleBorder);

		JLabel contentPanelLabel = new JLabel("Content Space for " + contentPanelLabelText);
		contentPanel.add(contentPanelLabel);
		return contentPanel;
	}

	private void prepareNavigationBarButtonMouseListener(JPanel navigationBarButtonPanel, IView contentPanel) {
		navigationBarButtonPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeContentPanel(contentPanel);
			}
		});
	}

	private void changeContentPanel(IView iView) {
		JPanel contentPanel = iView.prepareView( );
		layeredPane.removeAll();
		layeredPane.add(contentPanel);
		layeredPane.repaint();
	}
}