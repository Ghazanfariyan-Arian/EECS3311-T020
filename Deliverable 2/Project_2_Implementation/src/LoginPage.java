import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPage extends JFrame {
	
	private static final String CSV_FILE_PATH = "user_database.csv";
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textEmail;
	private JTextField textPassword;
	JLabel lblInfo;
    private ClientManager clientManager;
    private JTextField txtDontHaveAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("                                             YorkU Library Manager ");
		lblTitle.setBounds(0, 0, 586, 62);
		lblTitle.setFont(new Font("Papyrus", Font.BOLD, 20));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		getContentPane().add(lblTitle);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
		lblEmail.setBounds(166, 82, 53, 25);
		contentPane.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(294, 88, 133, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(294, 132, 133, 19);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
		btnLogin.setBounds(207, 183, 133, 51);
		contentPane.add(btnLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
		lblPassword.setBounds(164, 131, 89, 25);
		contentPane.add(lblPassword);
		
		txtDontHaveAccount = new JTextField();
		txtDontHaveAccount.setText("Don't have account?");
		txtDontHaveAccount.setFont(new Font("Yu Gothic", Font.PLAIN, 10));
		txtDontHaveAccount.setBounds(221, 262, 106, 19);
		contentPane.add(txtDontHaveAccount);
		txtDontHaveAccount.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(231, 291, 85, 21);
		btnNewButton.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		contentPane.add(btnNewButton);
		
		try {
			clientManager = new ClientManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		btnNewButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
				dispose();
		}
	  });
		
		btnLogin.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textEmail.getText();
				String password = textPassword.getText();

			    // Call the login method from ClientManager
			    boolean loggedIn = clientManager.loginUser(email, password);
			    
			    if (loggedIn) {
			        // Open the main page if login is successful
			        MainPage main = new MainPage();
			        main.setVisible(true);
			        dispose();
			    } else {
			        // Show pop-up notification for invalid credentials
			        JOptionPane.showMessageDialog(LoginPage.this, "Invalid email or password", "Login Error", JOptionPane.ERROR_MESSAGE);
			    }
			}
		});
		
}
}
