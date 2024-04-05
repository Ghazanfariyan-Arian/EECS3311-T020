import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class BorrowPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel bookTab;
    private JPanel cdTab;
    private JPanel magazineTab;
    private LibraryItemFactory factory;
    private ItemManager itemManager;
    private JButton btnManage;

	/**
	 * Create the frame.
	 */
	public BorrowPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Borrow physical items");
		setContentPane(contentPane);
        contentPane.setLayout(null);
        
		// Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 10, 564, 340); 
        contentPane.add(tabbedPane);
        
     // Create the CD tab
        cdTab = new JPanel();
        tabbedPane.addTab("CD", null, cdTab, null);
        cdTab.setLayout(null);
        
        JLabel lblCd = new JLabel("CD content goes here");
        lblCd.setBounds(10, 11, 200, 14); // Adjusted bounds
        cdTab.add(lblCd);
        
        // Create the book tab
        bookTab = new JPanel();
        tabbedPane.addTab("Book", null, bookTab, null);
        bookTab.setLayout(null);
        bookTab.setLayout(new GridLayout(0, 1, 0, 0));

        
        // Create the magazine tab
        magazineTab = new JPanel();
        tabbedPane.addTab("Magazine", null, magazineTab, null);
        magazineTab.setLayout(null);
        
        JLabel lblMagazine = new JLabel("Magazine content goes here");
        lblMagazine.setBounds(10, 11, 200, 14); // Adjusted bounds
        magazineTab.add(lblMagazine);
        
        btnManage = new JButton("New button");
        tabbedPane.addTab("New tab", null, btnManage, null);
 
	}
	 
	  // Method to add book item with information
	   void addItem(String type, int ID, String title, String location, Boolean available) {
	    	 JLabel label = new JLabel("Title: " + title + ", Id " + ID + ", Location: " + location
		        + ", Availability: " + available);
		        
	    	 // Create a panel to contain the label
	    	 JPanel itemPanel = new JPanel();
	    	 itemPanel.add(label);

	    	 // Add the new item to the appropriate list based on the selected item type
            switch (type) {
                case "Book":
        	        bookTab.add(itemPanel);
                    break;
                case "CD":
                    cdTab.add(itemPanel);
                    break;
                case "Magazine":
                    magazineTab.add(itemPanel);
                    break;
                default:
                    // Handle invalid item type
                    break;
            }
            
         // Set the visibility of panels
            bookTab.setVisible(true);
            cdTab.setVisible(true);
            magazineTab.setVisible(true);
            
            bookTab.revalidate();
            bookTab.repaint();
            cdTab.revalidate();
            cdTab.repaint();
            magazineTab.revalidate();
            magazineTab.repaint();
	       
	    }   
	   
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						BorrowPage frame = new BorrowPage();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}


}
