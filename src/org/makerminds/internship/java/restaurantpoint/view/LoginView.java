package org.makerminds.internship.java.restaurantpoint.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.makerminds.internship.java.restaurantpoint.controller.LoginController;
import org.makerminds.internship.java.restaurantpoint.model.User;

public class LoginView {

	private final Font GENERAL_LABEL_FONT = new Font("Arial", Font.PLAIN, 15);
	private JTextField usernameTextField = new JTextField();
	private JPasswordField passwordTextField;
	private JLabel loginResultLabel;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView window = new LoginView();
					window.prepareView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void prepareView() {
		initializeLoginView();
		createUsernameComponents();
		createPasswordComponents();
		createLoginButton();
		createLoginResutlLabel();
	}

	private void initializeLoginView() {
		frame = new JFrame();
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	private void createLoginResutlLabel() {
		loginResultLabel = new JLabel("");
		loginResultLabel.setBounds(100, 220, 200, 40);
		loginResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(loginResultLabel);
	}

	private void createUsernameComponents() {
		JLabel usernameLabel = createUsernameTextfieldLabel();
		createUsernameTextField();
		frame.getContentPane().add(usernameLabel);
		frame.getContentPane().add(usernameTextField);
	}

	private JLabel createUsernameTextfieldLabel() {
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(GENERAL_LABEL_FONT);
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(0, 57, 90, 26);
		return usernameLabel;
	}

	private void createPasswordComponents() {
		JLabel passwordLabel = createPasswordTextfieldLabel();
		createPasswordTextField();
		frame.getContentPane().add(passwordLabel);
		frame.getContentPane().add(passwordTextField);
	}

	private void createUsernameTextField() {
		usernameTextField.setFont(GENERAL_LABEL_FONT);
		usernameTextField.setBounds(100, 57, 200, 26);
	}

	private JLabel createPasswordTextfieldLabel() {
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(GENERAL_LABEL_FONT);
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setBounds(0, 90, 90, 26);
		return passwordLabel;
	}

	private void createPasswordTextField() {
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(GENERAL_LABEL_FONT);
		passwordTextField.setBounds(100, 90, 200, 26);
		passwordTextField.setEchoChar('*');
	}

	private void createLoginButton() {
		JButton loginButton = new JButton("Login");
		loginButton.setFont(GENERAL_LABEL_FONT);
		loginButton.setBackground(Color.blue);
		loginButton.setForeground(Color.white);
		loginButton.setBounds(157, 170, 100, 35);
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loggedIn();

			}
		});
		frame.getContentPane().add(loginButton);
	}

	public void loggedIn() {
		String username = usernameTextField.getText();
		String password = String.valueOf(passwordTextField.getPassword());

		boolean credentialsProvided = areCredentialsProvided(username, password);
		if (credentialsProvided) {
			LoginController.getInstance().loginUser(username, password);
			User loggedInUser = LoginController.getInstance().getLoggedInUser();
			boolean isLoginSuccessful = isLoginSuccessful(loggedInUser);
			if (isLoginSuccessful) {
				frame.dispose();
				new SplitLayout();
			}
		}
	}

	private boolean isLoginSuccessful(User loggedInUser) {
		if (loggedInUser == null) {
			loginResultLabel.setText("Username or Password is wrong!");
			usernameTextField.setText("");
			passwordTextField.setText("");
			return false;
		}
		return true;
	}

	private boolean areCredentialsProvided(String username, String password) {
		LoginController loginController = LoginController.getInstance();
		if (loginController.isStringNullOrBlank(username)) {
			loginResultLabel.setText("Please provide your username!");
			return false;
		} else if (loginController.isStringNullOrBlank(password)) {
			loginResultLabel.setText("Please provide your password!");
			return false;
		}
		return true;
	}
}