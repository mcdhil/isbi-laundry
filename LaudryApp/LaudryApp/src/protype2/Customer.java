package protype2;


import java.util.ArrayList;
import java.util.List;



public class Customer extends User {
	
	private Address address;
    private List<LaundryOrder> orderHistory;
    private String preferences;
    
    public Customer(String name, String phoneNumber, String email, Address address) {
    	super(name, phoneNumber, email);
        this.address = address;
        this.orderHistory = new ArrayList<>();
        this.preferences = "";
		
        
	}
 // Getters
    public Address getAddress() {
        return address;
    }

    public List<LaundryOrder> getOrderHistory() {
        return orderHistory;
    }

    public String getPreferences() {
        return preferences;
    }

    // Setters
    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    /**
     * Adds an order to the customer's order history.
     * @param order The LaundryOrder to add.
     */
    public void addOrderToHistory(LaundryOrder order) {
        this.orderHistory.add(order);
    }

    /**
     * Placeholder for placing a new laundry order.
     * In a real app, this would interact with an OrderService.
     * @param order The LaundryOrder to place.
     */
    public void placeOrder(LaundryOrder order) {
        System.out.println("Customer " + getName() + " is placing an order: " + order.getOrderId());
        addOrderToHistory(order);
        // Further logic to process the order (e.g., notify staff, schedule pickup)
    }

    /**
     * Placeholder for viewing the status of a specific order.
     * @param orderId The ID of the order to check.
     */
    public void viewOrderStatus(String orderId) {
        for (LaundryOrder order : orderHistory) {
            if (order.getOrderId().equals(orderId)) {
                System.out.println("Order " + orderId + " status: " + order.getOrderStatus());
                return;
            }
        }
        System.out.println("Order " + orderId + " not found in history.");
    }

    @Override
    public void viewProfile() {
        System.out.println("--- Customer Profile ---");
        System.out.println(super.toString());
        System.out.println("Address: " + (address != null ? address.getFullAddress() : "N/A"));
        System.out.println("Preferences: " + preferences);
        System.out.println("Order Count: " + orderHistory.size());
    }

    @Override
    public void updateProfile() {
        System.out.println("Updating profile for customer: " + getName());
        // In a real app, this would likely involve prompting for new details
        // and then calling setters. e.g., setName("New Name");
    }
	}

