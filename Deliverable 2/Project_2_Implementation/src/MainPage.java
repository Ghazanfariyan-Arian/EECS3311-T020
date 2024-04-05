import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class MainPage extends JFrame {
    private BorrowingSystem borrowingSystem;
	private static Client client;
	private static Student student;

	private static final long serialVersionUID = 1L;
	private JPanel MainPane;
	private JPanel leftPanel;
    private JPanel rightPanel;
    
    private JList<String> borrowedItemsList;
    private DefaultListModel<String> borrowedItemsModel;
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					ClientManager clientManager = new ClientManager();
					BorrowingSystem borrowingSystem = new BorrowingSystem();
	                CourseTextbookManager courseTextbookManager = new CourseTextbookManager();
	                
	               
					
	                Client client = new Client(12, "email", "password", "Student");
	                Student student = new Student(1, "student@example.com", "password", "Student");
	                Item item1 = new Item(1, "Book 1", "Location 1", true);
	                Item item4 = new Item(4, "Book 4", "Location 4", true);
	                Item item6 = new Item(8, "Book 10", "Location 5", true);

	                
	                borrowingSystem.borrowItem(client, item1 );
	                borrowingSystem.borrowItem(client, new Item(2, "Book 2", "Location 2", true));
	                borrowingSystem.borrowItem(client, new Item(3, "Book 3", "Location 3", true));

	                // Assigning a course to the student
	                String assignedCourse = courseTextbookManager.assignCourse(student);

	             // Borrow an item for the default client
	                //borrowingSystem.borrowItem(this, item);
	                MainPage frame = new MainPage();

				    frame.setStudent(student);
				    frame.setClient(client);
				    frame.setBorrowingSystem(borrowingSystem);
				    // Borrow an item for the default client
	                Item defaultItem = new Item(4, "Book 8", "Location 5", true);
	                frame.borrowDefaultItem(defaultItem);
	                


					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	  public void borrowDefaultItem(Item item) {
	        borrowingSystem.borrowItem(client, item);
	    }

    
    
    public MainPage() {
   
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setSize(600, 400); 
		    setLocation(0, 0);
		    getContentPane().setLayout(null);
		
		    JLabel lblTitle = new JLabel("     YorkU Library Manager ");
		    lblTitle.setBounds(0, 0, 586, 62);
		    lblTitle.setFont(new Font("Papyrus", Font.BOLD, 20));
		    lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		    getContentPane().add(lblTitle);
		    
	       // Add tabs to the left panel
	        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	        tabbedPane.setBounds(21, 71, 371, 270);
	        tabbedPane.setPreferredSize(new Dimension(350, 250));

	        JPanel panel1 = new JPanel();
            panel1.setPreferredSize(new Dimension(600, 200)); 
            
            JPanel panel2 = new JPanel();
            panel2.setPreferredSize(new Dimension(600, 200)); 
            
          
            
            // Create a JList with the borrowed items
            DefaultListModel<String> borrowedItemsModel = new DefaultListModel<>();
            JList<String> borrowedItemsList = new JList<>(borrowedItemsModel);
            setMultiLineCellRenderer(borrowedItemsList);

            borrowedItemsModel.clear();

            List<Item> borrowedItems = borrowingSystem.getBorrowedItems(client);
            for (Item item : borrowedItems) {
            	Date borrowDate = item.getBorrowDates();
                item.setFormattedDueDate(borrowDate); // Set and format the due date
                String dueDate = item.getFormattedDueDate();
                String itemInfo = String.format("<html>Item ID: %d<br>Title: %s<br>Location: %s<br>Due Date: %s</html>", item.getItemId(), item.getTitle(), item.getLocations(), dueDate);

                borrowedItemsModel.addElement(itemInfo);
                borrowedItemsModel.addElement("------------------------------"); 

            }

            panel1.add(borrowedItemsList, BorderLayout.CENTER);
              
       
            //panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

	        tabbedPane.addTab("Borrowed Books",  panel1);
	        tabbedPane.addTab("Late Penalty",  panel2);
	        JPanel panel3;
			try {
				panel3 = createSubscriptionTab(client);
		        tabbedPane.addTab("Subscriptions", panel3);

			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        JPanel panel4 = createCourseManagementPanel(student);
	        tabbedPane.addTab("Courses", panel4);


	        getContentPane().add(tabbedPane, BorderLayout.CENTER);
	       
		    // Make the frame visible
	        setSize(600, 400); 
	        setResizable(false); 
		    setVisible(true);
		
}
	  public void setStudent(Student student) {
	        this.student = student;
	    }
	    public void setBorrowingSystem(BorrowingSystem borrowingSystem) {
	        this.borrowingSystem = borrowingSystem;
	    }

	    public void setClient(Client client) {
	        this.client = client;
	    }
	    
	private JPanel createCourseManagementPanel(Student student) {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());

	    JLabel titleLabel = new JLabel("Course Management");
	    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    panel.add(titleLabel, BorderLayout.NORTH);

	    // Display courses and corresponding textbooks
	    StringBuilder coursesText = new StringBuilder();
	    coursesText.append("Courses and corresponding textbooks:\n");
	    

	    CourseTextbookManager courseTextbookManager = new CourseTextbookManager();
	    System.out.println("Student client type: " + client.getType());
	  
	       
	    List<String> studentCourses = student.getCourses(); 
	        for (String course : studentCourses) {
	            coursesText.append("- ").append(course).append("\n");
	     }
	  


	    JTextArea coursesTextArea = new JTextArea(coursesText.toString());
	    coursesTextArea.setEditable(false);
	    panel.add(coursesTextArea, BorderLayout.CENTER);

	    return panel;
	}
	
	
	private JPanel createSubscriptionTab(Client client) throws IOException {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));	    
		ClientManager clientManager = new ClientManager();
	

		// Get the list of available newsletters
		List<String> newsletters = clientManager.getAvailableNewsletters();

	    
	    JPanel checkboxPanel = new JPanel();
	    checkboxPanel.setLayout(new GridLayout(0, 1)); // Vertical layout

	    // Add a checkbox for each newsletter
	    List<JCheckBox> checkboxes = new ArrayList<>();
	    for (String newsletter : newsletters) {
	        JCheckBox checkbox = new JCheckBox(newsletter);
	        checkboxes.add(checkbox);
	        checkboxPanel.add(checkbox);

	    }
	    JButton confirmButton = new JButton("Confirm");
	    confirmButton.addActionListener(e -> {

	        // Handle confirmation action
	        List<String> subscribedNewsletters = new ArrayList<>();
	        for (JCheckBox checkbox : checkboxes) {
	            String newsletter = checkbox.getText();
	            if (checkbox.isSelected()) {
	                clientManager.subscribeNewsletter(client, newsletter);
	                subscribedNewsletters.add(newsletter);

	                
	            } else {
	                clientManager.cancelSubscription(client, newsletter);

	            }
	        }
	     
	     // Show a pop-up notification to verify the subscription
	        StringBuilder message = new StringBuilder();
	        message.append("You are subscribed to the following newsletters:\n");
	        for (String newsletter : subscribedNewsletters) {
	            message.append("- ").append(newsletter).append("\n");
	        }
	        
	     // Set a custom message area size for the JOptionPane
	        JTextArea messageArea = new JTextArea(message.toString());
	        messageArea.setColumns(10); // Set the number of columns
	        messageArea.setRows(10); // Set the number of rows
	        messageArea.setLineWrap(true); 
	        messageArea.setWrapStyleWord(true); 

	        JScrollPane scrollPane = new JScrollPane(messageArea);
	        scrollPane.setPreferredSize(new Dimension(200, 100));
	        JOptionPane.showMessageDialog(panel, scrollPane, "Subscription Confirmation", JOptionPane.INFORMATION_MESSAGE);

	        //JOptionPane.showMessageDialog(panel, message.toString());
	        
	    });
	    panel.add(checkboxPanel, BorderLayout.CENTER);
	    panel.add(confirmButton);
	    return panel;
	}

	
    // Method for custom cell rendering
    private void setMultiLineCellRenderer(JList<?> list) {
        list.setCellRenderer((list1, value, index, isSelected, cellHasFocus) -> {
            Component c = new DefaultListCellRenderer().getListCellRendererComponent(list1, value, index, isSelected, cellHasFocus);
            if (c instanceof JLabel) {
                JLabel label = (JLabel) c;
                label.setText((String) value);
                label.setVerticalAlignment(JLabel.TOP); // Align text to the top
            }
            return c;
        });
    }
    
   
}


