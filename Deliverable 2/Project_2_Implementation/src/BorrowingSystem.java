import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowingSystem {
	 private float overduePenalty = 0.5f;
	 private int maxOverdueDays = 15;
	 private int maxBorrowedItems = 10;
	 private int maxBorrowedDays = 30;
	 private int maxOverdueItems = 3;
	 
	 private Map<Client, List<Item>> borrowedItems;
	 private Map<Client, List<Item>> overdueItems;

	 public BorrowingSystem() {
		 borrowedItems = new HashMap<>();
	     overdueItems = new HashMap<>();
	 
	 }


    public void setOverduePenalty(float penalty) {
        this.overduePenalty = penalty;
    }

    public float getOverduePenalty() {
        return overduePenalty;
    }

    public void notifyOverdue(Client client, Item item) {
    	 // Add notification logic for overdue items here
        System.out.println("Notification: Item " + item.getTitle() + " is overdue");
    }

    public void applyPenalty(Client client, Item item) {
    	 // Get the current date
        Date currentDate = new Date();
        
        // Get the borrow date of the item
        Date borrowDate = item.getBorrowDates();
        
        //Difference in milli
        Calendar dueDateCalendar = Calendar.getInstance();
        dueDateCalendar.setTime(borrowDate);
        dueDateCalendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dueDate = dueDateCalendar.getTime();
        
        // Check if the current date is after the due date
        if (currentDate.after(dueDate)) {
            // Calculate the difference in days between the due date and the current date
            long differenceInMillis = currentDate.getTime() - dueDate.getTime();
            long daysOverdue = differenceInMillis / (1000 * 60 * 60 * 24);
            
            float penaltyAmount = daysOverdue * overduePenalty;
         // Update or create the overdue items map for the client
            List<Item> clientOverdueItems = overdueItems.getOrDefault(client, new ArrayList<>());
            clientOverdueItems.add(item);
            overdueItems.put(client, clientOverdueItems);
            
            // Notify the client about the penalty
            System.out.println("Penalty of $" + penaltyAmount +
                               " for item " + item.getTitle() + " being " + daysOverdue + " days overdue.");
        }
    }
    public Date getDueDate(Item item) {
    	// Iterate over each entry in the borrowedItems map
        for (Map.Entry<Client, List<Item>> entry : borrowedItems.entrySet()) {
            // Check if the list of borrowed items for this client contains the specified item
            if (entry.getValue().contains(item)) {
                // Get the borrow date for the specified item
                Date borrowDate = item.getBorrowDates();
                
                // Calculate the due date based on the borrow date
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(borrowDate);
                calendar.add(Calendar.DAY_OF_MONTH, 30);
                
                item.setFormattedDueDate(calendar.getTime());
                System.out.println(calendar.getTime());
                return calendar.getTime();
                
            }
        }
        return null;
    }
  
    public void notifyManagementTeam(Item item) {
    }
    
    public void addItem(Item item) {
        
        Client defaultClient = new Client(); 
        borrowItem(defaultClient, item);
    }
    

    public void borrowItem(Client client, Item item) {
    	
    	// Add debug print statement to check if borrowItem is being called
        System.out.println("Borrowing item: " + item.getTitle());
        List<Item> clientItems = borrowedItems.getOrDefault(client, new ArrayList<>());
        clientItems.add(item);
        borrowedItems.put(client, clientItems);
        
        // Set the borrowing date
        item.setBorrowDate(new Date());
    }
    
    // Method to return an item
    public void returnItem(Client client, Item item) {
        List<Item> clientItems = borrowedItems.getOrDefault(client, new ArrayList<>());
        clientItems.remove(item);
        borrowedItems.put(client, clientItems);
    }
    
 // Method to get the list of borrowed items for a client
    public List<Item> getBorrowedItems(Client client) {
        return borrowedItems.getOrDefault(client, new ArrayList<>());
    }

    // Method to get the list of overdue items for a client
    public List<Item> getOverdueItems(Client client) {
        return overdueItems.getOrDefault(client, new ArrayList<>());
    }
}
