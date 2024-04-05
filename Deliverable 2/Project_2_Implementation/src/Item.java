import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;


public class Item extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int itemId;
	private String title;
	private String locations;
	private boolean available;
	private Date borrowDate;
	private String formattedDueDate;
	
	 // Constructor
    public Item(int itemId, String title, String locations, boolean available) {
        this.itemId = itemId;
        this.title = title;
        this.locations = locations;
        this.available = available;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item frame = new Item();
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
	public Item() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 JLabel lblSelectItem = new JLabel("Select Item:");
	     lblSelectItem.setBounds(23, 29, 109, 25);
	     lblSelectItem.setFont(new Font("Yu Gothic", Font.PLAIN, 17));
	     contentPane.add(lblSelectItem);
		
		// Options on what to borrow
		JComboBox comboBoxType = new JComboBox();
	    comboBoxType.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
	    comboBoxType.setBounds(23, 52, 133, 31);
	    contentPane.add(comboBoxType);
	    
	        comboBoxType.addItem("Book");
	        comboBoxType.addItem("Magazine");
	        comboBoxType.addItem("CD");
	        
	    JButton btnReturn = new JButton("Return");
	    btnReturn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
	    btnReturn.setBounds(220, 175, 138, 41);
	    contentPane.add(btnReturn);
	  
		
		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		btnBorrow.setBounds(220, 108, 138, 41);
		contentPane.add(btnBorrow);

		btnBorrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItemType = (String) comboBoxType.getSelectedItem();
				if ("Book".equals(selectedItemType)) {
					BorrowPage borrowBook = new BorrowPage();
			        borrowBook.setVisible(true);
	                }
	            }
		});
		setVisible(true);
		
		
		
	}
	
	 public void setItemId(int id) {
	        this.itemId = id;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public void setLocation(String location) {
	        this.locations = locations;
	    }

	    public void setAvailability(boolean available) {
	        this.available = available;
	    }

	    public int getItemId() {
	        return itemId;
	    }
	    public String getTitle() {
	        return title;
	    }
	    public String getLocations() {
	    	return locations;
	    }
	    public boolean getAvailability() {
	        return available;
	    }
	   
	    public String getItemDetails() {
	        return "Item ID: " + itemId + "\nTitle: " + title + "\nLocation: " + locations + "\nAvailable: " + (available ? "Yes" : "No");
	    }
	    

	    public void borrowItem(Client client) {
	        if (available) {
	            System.out.println(client.getId() + " has borrowed the item: " + title);
	            available = false;
	        } else {
	            System.out.println("Sorry, the item \"" + title + "\" is currently not available.");
	        }
	    }

	    public void returnItem(Client client) {
	        System.out.println(client.getId() + " has returned the item: " + title);
	        available = true;
	    }
	    
	    @Override
	    public String toString() {
	        return "ID: " + itemId + ", Title: " + title + ", Location: " + locations + ", Available: " + available;
	    }

		public void setBorrowDate(Date borrowDate) {
	        this.borrowDate = borrowDate;

		}
		public Date getBorrowDates() {
	         return borrowDate;

		}
		public void setFormattedDueDate(Date borrowDate) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(borrowDate);
	        calendar.add(Calendar.DAY_OF_MONTH, 30);
	        Date dueDate = calendar.getTime();

	        // Format the due date
	        SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd, yyyy");
	        this.formattedDueDate = dateFormat.format(dueDate);
	    }
		
		public String getFormattedDueDate() {
			return this.formattedDueDate;
		}

		public String getBorrowDate() {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd, yyyy");
		     return dateFormat.format(borrowDate);

			
		}

		
	
}

















