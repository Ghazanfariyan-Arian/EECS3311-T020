import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class ItemManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JPanel panel;

	private List<Item> bookItems;
    private List<Item> cdItems;
    private List<Item> magazineItems;
    private LibraryItemFactory libraryItemFactory;
    private BorrowPage borrowPage;
  
    private static final String CSV_FILE_PATH = "item_database.csv";
    private CSVWriter csvWriter;
	private CSVReader csvReader;


    public ItemManager(LibraryItemFactory libraryItemFactory, BorrowPage borrowPage) {
        this.libraryItemFactory = libraryItemFactory;
        this.borrowPage = borrowPage;
        bookItems = new ArrayList<>();
        cdItems = new ArrayList<>();
        magazineItems = new ArrayList<>();
        csvWriter = new CSVWriter(CSV_FILE_PATH);

        //loadItems();
        
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 2, 10, 10));
        
        // Left panel for listing items
        panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(null);
        
        JButton btnCreate = new JButton("Create");
        btnCreate.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
        btnCreate.setBounds(10, 10, 136, 78);
        panel.add(btnCreate);
        
        JButton btnAvailable = new JButton("Availability ");
        btnAvailable.setFont(new Font("Yu Gothic", Font.PLAIN, 15));
        btnAvailable.setBounds(10, 98, 136, 78);
        panel.add(btnAvailable);
        panel.setVisible(true);
        
        
        // Right panel for creating items (initially hidden)
        JPanel createPanel = new JPanel();
        contentPane.add(createPanel);
        createPanel.setVisible(false);
        
        // Labels and text fields for item info
        JLabel lblItemType = new JLabel("Item Type:");
        lblItemType.setBounds(0, 1, 136, 50);
        JComboBox<String> cmbItemType = new JComboBox<>(new String[]{"Book", "CD", "Magazine"});
        cmbItemType.setBounds(146, 1, 136, 50);
        createPanel.setLayout(null);
        createPanel.add(lblItemType);
        createPanel.add(cmbItemType);
        
        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(0, 61, 136, 50);
        JTextField txtTitle = new JTextField();
        txtTitle.setBounds(146, 61, 136, 50);
        createPanel.add(lblTitle);
        createPanel.add(txtTitle);

        JLabel lblItemId = new JLabel("Item ID:");
        lblItemId.setBounds(0, 121, 136, 50);
        JTextField txtItemId = new JTextField();
        txtItemId.setBounds(146, 121, 136, 50);
        createPanel.add(lblItemId);
        createPanel.add(txtItemId);

        JLabel lblLocation = new JLabel("Location:");
        lblLocation.setBounds(0, 181, 136, 50);
        JTextField txtLocation = new JTextField();
        txtLocation.setBounds(146, 181, 136, 50);
        createPanel.add(lblLocation);
        createPanel.add(txtLocation);

        JLabel lblAvailable = new JLabel("Available:");
        lblAvailable.setBounds(0, 241, 136, 50);
        JComboBox<String> cmbAvailable = new JComboBox<>(new String[]{"Yes", "No"});
        cmbAvailable.setBounds(146, 241, 136, 50);
        createPanel.add(lblAvailable);
        createPanel.add(cmbAvailable);

        // Add button to submit item
        JButton btnAddItem = new JButton("Add");
        btnAddItem.setBounds(185, 310, 88, 33);
        createPanel.add(btnAddItem);
        
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	createPanel.setVisible(true);
            	
            }
        });
        btnAddItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	 // Retrieve input values
            String itemType = cmbItemType.getSelectedItem().toString();
            String title = txtTitle.getText();
            int itemId = Integer.parseInt(txtItemId.getText());
            String location = txtLocation.getText();
            boolean available = cmbAvailable.getSelectedItem().toString().equalsIgnoreCase("Yes");

            // Create new item with input values using LibraryItemFactory
            Item newItem = libraryItemFactory.createItem(itemType);
            newItem.setTitle(title);
            newItem.setItemId(itemId);
            newItem.setLocation(location);
            newItem.setAvailability(available);
            
            borrowPage.addItem(itemType, itemId, title, location, available);

            // Add the new item to the appropriate list based on the selected item type
            switch (itemType) {
                case "Book":
                    bookItems.add(newItem);
                    break;
                case "CD":
                    cdItems.add(newItem);
                    break;
                case "Magazine":
                    magazineItems.add(newItem);
                    break;
                default:
                    // Handle invalid item type
                    break;
            }
            saveItems();
            createPanel.add(btnAddItem);
    }
    });
    }

	
    // Load items from CSV file
    private void loadItems() {
    	 File file = new File(CSV_FILE_PATH);
  	    if (!file.exists()) {
  	        // If the file doesn't exist, create a new empty file
  	        try {
  	            file.createNewFile();
  	        } catch (IOException e) {
  	            e.printStackTrace();
  	            return; // Exit the method if file creation fails
  	        }
  	    }
  	     
    	        
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            boolean firstLine = true; // Flag to skip the first line

            while ((line = reader.readLine()) != null) {
            	
            	 if (firstLine) {
                     firstLine = false; // Skip the first line
                     continue;
                 }
            	 // Skip empty lines
                 if (line.trim().isEmpty()) {
                     continue;
                 }
                String[] parts = line.split(",");
                String itemType = parts[0];
                int itemId = Integer.parseInt(parts[1]);
                String title = parts[2];
                String location = parts[3];
                boolean available = Boolean.parseBoolean(parts[4]);

                Item newItem = libraryItemFactory.createItem(itemType);
                newItem.setTitle(title);
                newItem.setItemId(itemId);
                newItem.setLocation(location);
                newItem.setAvailability(available);

                switch (itemType) {
                    case "Book":
                        bookItems.add(newItem);
                        break;
                    case "CD":
                        cdItems.add(newItem);
                        break;
                    case "Magazine":
                        magazineItems.add(newItem);
                        break;
                    default:
                        // Handle invalid item type
                        break;
                }
             
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save items to the CSV file
    private void saveItems() {
        try {
            List<List<String[]>> sections = new ArrayList<>();
            sections.add(createItemSection(bookItems));
            sections.add(createItemSection(cdItems));
            sections.add(createItemSection(magazineItems));
            csvWriter.writeData1(sections);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private List<String[]> createItemSection(List<Item> items) {
        List<String[]> section = new ArrayList<>();
        for (Item item : items) {
            String[] rowData = {
                item.getClass().getSimpleName(),
                String.valueOf(item.getItemId()),
                item.getTitle(),
                item.getLocations(),
                String.valueOf(item.getAvailability())
            };
            section.add(rowData);
        }
        return section;
    }
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    LibraryItemFactory libraryItemFactory = new ConcreteLibraryItemFactory();
                    BorrowPage borrowPage = new BorrowPage();
					ItemManager frame = new ItemManager(libraryItemFactory, borrowPage);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  }


		    



