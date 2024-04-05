import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Register extends JFrame {
	private static final String CSV_FILE_PATH = "user_database.csv";
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CSVWriter csvWriter;
	private CSVReader csvReader;
    private ClientManager clientManager;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("                                         Welcome to Registration!");
		lblTitle.setBounds(0, 0, 586, 70);
		lblTitle.setFont(new Font("Papyrus", Font.BOLD, 20));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		getContentPane().add(lblTitle);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
		lblEmail.setBounds(133, 102, 53, 25);
		contentPane.add(lblEmail);
		
		JTextField textEmail = new JTextField();
		textEmail.setBounds(302, 103, 133, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		JTextField textPassword = new JTextField();
		textPassword.setBounds(302, 143, 133, 19);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
		lblPassword.setBounds(133, 142, 89, 25);
		contentPane.add(lblPassword);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
		lblType.setBounds(133, 181, 89, 25);
		contentPane.add(lblType);

        
        JComboBox comboBoxType = new JComboBox();
        comboBoxType.setFont(new Font("Yu Gothic", Font.PLAIN, 10));
        comboBoxType.setBounds(302, 184, 133, 21);
        contentPane.add(comboBoxType);

        comboBoxType.addItem("Student");
        comboBoxType.addItem("Faculty Memember");
        comboBoxType.addItem("Non-faculty Staff");
        comboBoxType.addItem("Visitor");
        
        JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
		btnRegister.setBounds(201, 264, 189, 46);
		contentPane.add(btnRegister);
		
		try {
			clientManager = new ClientManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

        btnRegister.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = textEmail.getText();
				String password = textPassword.getText();
				String selectedType = (String) comboBoxType.getSelectedItem();
		        String type = null; 
		       
		       
	            BorrowingSystem borrowingSystem = new BorrowingSystem();
			    // Call the register method from ClientManager
			    boolean registered = clientManager.registerUser(email, password, type);
			    
			    Client client = null;
		        Student student = null;
			    if (registered) {
			    	    
			    	 if (selectedType.equals("Student")) {
				            type = "Student";
				            student = new Student(0, email, password,type);
				        } else if (selectedType.equals("Faculty Member")) {
				            type = "Faculty Member";
				            client = new Client(0,email, password, type);
				        } else if (selectedType.equals("Non-faculty Staff")) {
				            type = "Non-Faculty Staff";
				            client = new Client(0,email, password, type);
				        } else if (selectedType.equals("Visitor")) {
				            type = "Visitor";
				            client = new Client(0,email, password, type);
				        }
			    	    MainPage main = new MainPage(borrowingSystem, client, student);
				        main.setVisible(true);
				        dispose();
			        
			    } else {
			        // Show pop-up notification for invalid credentials
			        JOptionPane.showMessageDialog(Register.this, "Invalid email or password", "Registration Error", JOptionPane.ERROR_MESSAGE);
			    }
		   
			}
		});
    
        
		
		
	}
}
